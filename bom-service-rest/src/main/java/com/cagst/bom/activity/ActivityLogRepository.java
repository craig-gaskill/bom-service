package com.cagst.bom.activity;

import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

/**
 * Defines methods for retrieving / persisting {@link ActivityLog} records.
 *
 * @author Craig Gaskill
 */
public interface ActivityLogRepository {
    /**
     * Inserts a new {@link ActivityLog} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param activityLog
     *      The {@link ActivityLog} to persist.
     *
     * @return A {@link Mono} that emits the ActivityLog after it has been inserted.
     */
    @NonNull
    Mono<ActivityLog> insert(@NonNull SecurityInfo securityInfo, @NonNull ActivityLog activityLog);
}
