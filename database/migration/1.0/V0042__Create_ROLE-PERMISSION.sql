CREATE TABLE IF NOT EXISTS role_permission (
    role_permission_id          BIGSERIAL CONSTRAINT role_permission_pk PRIMARY KEY,
    tenant_id                   INT NOT NULL,
    role_id                     BIGINT NOT NULL,
    permission_id               SMALLINT NOT NULL,
    granted_ind                 BOOLEAN DEFAULT false,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0,
    CONSTRAINT role_permission_tenant_fk FOREIGN KEY (tenant_id) REFERENCES tenant (tenant_id),
    CONSTRAINT role_permission_role_fk FOREIGN KEY (role_id) REFERENCES role (role_id),
    CONSTRAINT role_permission_permission_fk FOREIGN KEY (permission_id) REFERENCES permission (permission_id)
);

CREATE INDEX IF NOT EXISTS role_permission_role_idx ON role_permission (tenant_id, role_id);

CREATE TABLE IF NOT EXISTS audit_role_permission (
    audit_id                    BIGSERIAL CONSTRAINT audit_role_permission_pk PRIMARY KEY,
    audit_reason                CHAR(1) NOT NULL,
    audit_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    role_permission_id          BIGINT,
    tenant_id                   INT,
    role_id                     BIGINT,
    permission_id               SMALLINT,
    granted_ind                 BOOLEAN,
    active_ind                  BOOLEAN,
    created_dt_tm               TIMESTAMP WITH TIME ZONE,
    created_id                  BIGINT,
    created_source              VARCHAR(50),
    updated_dt_tm               TIMESTAMP WITH TIME ZONE,
    updated_id                  BIGINT,
    updated_source              VARCHAR(50),
    updated_cnt                 INT
);

CREATE INDEX IF NOT EXISTS audit_role_permission_idx ON audit_role_permission (role_permission_id);
