CREATE TABLE IF NOT EXISTS activity_log (
    activity_log_id       BIGSERIAL CONSTRAINT activity_log_pk PRIMARY KEY,
    activity_dt_tm        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    tenant_id             INT NULL,
    activity_category     SMALLINT NOT NULL,
    activity_sub_category VARCHAR(50) NULL,
    activity_context      TEXT NULL,
    instigating_user_id   BIGINT NULL
);
