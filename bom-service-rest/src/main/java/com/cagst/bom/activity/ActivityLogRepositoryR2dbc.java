package com.cagst.bom.activity;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.r2dbc.SqlParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

/**
 * An R2DBC implementation of the {@link ActivityLogRepository} interface.
 *
 * @author Craig Gaskill
 */
@Repository
/* package */ class ActivityLogRepositoryR2dbc implements ActivityLogRepository {
    private final DatabaseClient databaseClient;

    private final String INSERT_ACTIVITY_LOG =
        "INSERT INTO activity_log(" +
        "  tenant_id" +
        " ,activity_category" +
        " ,activity_sub_category" +
        " ,activity_context" +
        " ,instigating_user_id" +
        ") VALUES (" +
        "  :tenant_id" +
        " ,:activity_category" +
        " ,:activity_sub_category" +
        " ,:activity_context" +
        " ,:instigating_user_id" +
        ") RETURNING activity_log_id";

    @Autowired
    public ActivityLogRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @NonNull
    @Override
    public Mono<ActivityLog> insert(@NonNull SecurityInfo securityInfo, @NonNull ActivityLog activityLog) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(activityLog, "Argument [activityLog] cannot be null");

        var spec = databaseClient.sql(INSERT_ACTIVITY_LOG);
        spec = new SqlParameterMap()
            .addValue("tenant_id", activityLog.tenantId() != null ? activityLog.tenantId() : securityInfo.tenantId())
            .addValue("activity_category", activityLog.activityCategory().ordinal())
            .addValue("activity_sub_category", activityLog.activitySubCategory(), String.class)
            .addValue("activity_context", activityLog.activityContext(), String.class)
            .addValue("instigating_user_id", activityLog.instigatingUserId(), Long.class)
            .bind(spec);

        return spec.fetch().one()
            .map(result -> (Long)result.get("activity_log_id"))
            .map(activityLogId -> new ActivityLog.Builder()
                .from(activityLog)
                .activityLogId(activityLogId)
                .build()
            );
    }
}
