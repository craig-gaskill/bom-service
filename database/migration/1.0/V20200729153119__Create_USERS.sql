CREATE TABLE IF NOT EXISTS users (
    user_id                     BIGSERIAL CONSTRAINT users_pk PRIMARY KEY,
    username                    VARCHAR(256) NOT NULL,
    password                    CHAR(60) NOT NULL,
    temporary_password_ind      BOOLEAN DEFAULT true,
    login_attempts              INT DEFAULT 0,
    account_locked_dt_tm        TIMESTAMP WITH TIME ZONE NULL,
    account_locked_type         SMALLINT NULL,
    account_expired_dt_tm       TIMESTAMP WITH TIME ZONE NULL,
    password_changed_dt_tm      TIMESTAMP WITH TIME ZONE NULL,
    password_expired_dt_tm      TIMESTAMP WITH TIME ZONE NULL,
    admin_ind                   BOOLEAN DEFAULT false,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0
);

CREATE INDEX IF NOT EXISTS users_username_idx ON users (username);

CREATE TABLE IF NOT EXISTS audit_users (
    audit_id                    BIGSERIAL CONSTRAINT audit_users_pk PRIMARY KEY,
    audit_reason                CHAR(1) NOT NULL,
    audit_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    user_id                     BIGINT NOT NULL,
    username                    VARCHAR(256),
    temporary_password_ind      BOOLEAN,
    login_attempts              INT,
    account_locked_dt_tm        TIMESTAMP WITH TIME ZONE,
    account_locked_type         SMALLINT,
    account_expired_dt_tm       TIMESTAMP WITH TIME ZONE,
    password_changed_dt_tm      TIMESTAMP WITH TIME ZONE,
    password_expired_dt_tm      TIMESTAMP WITH TIME ZONE,
    admin_ind                   BOOLEAN,
    active_ind                  BOOLEAN,
    created_dt_tm               TIMESTAMP WITH TIME ZONE,
    created_id                  BIGINT,
    created_source              VARCHAR(50),
    updated_dt_tm               TIMESTAMP WITH TIME ZONE,
    updated_id                  BIGINT,
    updated_source              VARCHAR(50),
    updated_cnt                 INT
);

CREATE INDEX IF NOT EXISTS audit_users_idx ON audit_users (user_id);
