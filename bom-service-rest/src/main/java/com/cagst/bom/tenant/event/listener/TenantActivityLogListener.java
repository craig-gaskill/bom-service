package com.cagst.bom.tenant.event.listener;

import com.cagst.bom.activity.ActivityCategory;
import com.cagst.bom.activity.ActivityLog;
import com.cagst.bom.activity.ActivityLogRepository;
import com.cagst.bom.tenant.Tenant;
import com.cagst.bom.tenant.event.TenantCreatedEvent;
import com.cagst.bom.tenant.event.TenantDeletedEvent;
import com.cagst.bom.tenant.event.TenantEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Listens for {@link TenantEvent}s and writes out to the activity-log.
 *
 * @author Craig Gaskill
 */
@Component
/* package */ class TenantActivityLogListener implements ApplicationListener<TenantEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TenantActivityLogListener.class);

    private final ActivityCategory ACTIVITY_CATEGORY = ActivityCategory.Tenant;

    private static final String ACTIVITY_SUB_CATEGORY_CREATED  = "TenantCreated";
    private static final String ACTIVITY_SUB_CATEGORY_DELETED  = "TenantDeleted";

    private final ActivityLogRepository activityLogRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public TenantActivityLogListener(ActivityLogRepository activityLogRepository, ObjectMapper objectMapper) {
        this.activityLogRepository = activityLogRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onApplicationEvent(TenantEvent event) {
        var tenant = (Tenant)event.getSource();

        String context;
        try {
            context = objectMapper.writeValueAsString(tenant);
        } catch (JsonProcessingException ex) {
            LOGGER.warn("Failed serializing ApplicationContext for AnalyticAccount", ex);
            context = null;
        }
        if (event instanceof TenantCreatedEvent) {
            activityLogRepository.insert(event.getSecurityInfo(),
                new ActivityLog.Builder()
                    .tenantId(tenant.tenantId())
                    .activityCategory(ACTIVITY_CATEGORY)
                    .activitySubCategory(ACTIVITY_SUB_CATEGORY_CREATED)
                    .activityContext(context)
                    .instigatingUserId(event.getSecurityInfo().userId())
                    .build()
            ).subscribe();
        } else if (event instanceof TenantDeletedEvent) {
            activityLogRepository.insert(event.getSecurityInfo(),
                new ActivityLog.Builder()
                    .tenantId(tenant.tenantId())
                    .activityCategory(ACTIVITY_CATEGORY)
                    .activitySubCategory(ACTIVITY_SUB_CATEGORY_DELETED)
                    .activityContext(context)
                    .instigatingUserId(event.getSecurityInfo().userId())
                    .build()
            ).subscribe();
        } else {
            LOGGER.info("Unsupported event [{}]", event.getClass().getSimpleName());
        }
    }
}
