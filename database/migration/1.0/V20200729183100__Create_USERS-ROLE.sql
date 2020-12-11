CREATE TABLE IF NOT EXISTS users_role (
    user_role_id                BIGSERIAL CONSTRAINT users_role_pk PRIMARY KEY,
    user_id                     BIGINT NOT NULL,
    tenant_id                   BIGINT NOT NULL,
    role_id                     BIGINT NOT NULL,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0,
    CONSTRAINT users_role_udx1 UNIQUE (tenant_id, user_id, role_id),
    CONSTRAINT users_role_users_fk FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT users_role_tenant_fk FOREIGN KEY (tenant_id) REFERENCES tenant (tenant_id),
    CONSTRAINT users_role_role_fk FOREIGN KEY (role_id) REFERENCES role (role_id)
);

CREATE INDEX IF NOT EXISTS users_role_user_idx ON users_role (tenant_id, user_id);
CREATE INDEX IF NOT EXISTS users_role_role_idx ON users_role (tenant_id, role_id);

CREATE TABLE IF NOT EXISTS audit_users_role (
    audit_id                    BIGSERIAL CONSTRAINT audit_users_role_pk PRIMARY KEY,
    audit_reason                CHAR(1) NOT NULL,
    audit_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    user_role_id                BIGINT NOT NULL,
    user_id                     BIGINT,
    tenant_id                   BIGINT,
    role_id                     BIGINT,
    active_ind                  BOOLEAN,
    created_dt_tm               TIMESTAMP WITH TIME ZONE,
    created_id                  BIGINT,
    created_source              VARCHAR(50),
    updated_dt_tm               TIMESTAMP WITH TIME ZONE,
    updated_id                  BIGINT,
    updated_source              VARCHAR(50),
    updated_cnt                 INT
);

CREATE INDEX IF NOT EXISTS audit_users_role_idx ON audit_users_role (user_role_id);
