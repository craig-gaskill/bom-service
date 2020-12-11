CREATE TABLE IF NOT EXISTS users_access (
    user_access_id              BIGSERIAL CONSTRAINT users_access_pk PRIMARY KEY,
    user_id                     BIGINT NOT NULL,
    tenant_id                   INT NOT NULL,
    person_id                   BIGINT NOT NULL,
    default_ind                 BOOLEAN DEFAULT false,
    last_login_dt_tm            TIMESTAMP WITH TIME ZONE NULL,
    last_login_ip               VARCHAR(50) NULL,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0,
    CONSTRAINT users_access_udx1 UNIQUE (tenant_id, user_id),
    CONSTRAINT users_access_tenant_fk FOREIGN KEY (tenant_id) REFERENCES tenant (tenant_id),
    CONSTRAINT users_access_users_fk FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT users_access_person_fk FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE INDEX IF NOT EXISTS users_access_nk ON users_access (tenant_id, user_id);

CREATE TABLE IF NOT EXISTS audit_users_access (
    audit_id                    BIGSERIAL CONSTRAINT audit_users_access_pk PRIMARY KEY,
    audit_reason                CHAR(1) NOT NULL,
    audit_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    user_access_id              BIGINT NOT NULL,
    user_id                     BIGINT,
    tenant_id                   INT,
    person_id                   BIGINT,
    default_ind                 BOOLEAN,
    last_login_dt_tm            TIMESTAMP WITH TIME ZONE,
    last_login_ip               VARCHAR(50),
    active_ind                  BOOLEAN,
    created_dt_tm               TIMESTAMP WITH TIME ZONE,
    created_id                  BIGINT,
    created_source              VARCHAR(50),
    updated_dt_tm               TIMESTAMP WITH TIME ZONE,
    updated_id                  BIGINT,
    updated_source              VARCHAR(50),
    updated_cnt                 INT
);

CREATE INDEX IF NOT EXISTS audit_users_access_idx ON audit_users_access (user_access_id);
