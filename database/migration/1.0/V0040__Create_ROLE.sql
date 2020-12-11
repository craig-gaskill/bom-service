CREATE TABLE IF NOT EXISTS role (
    role_id                     BIGSERIAL CONSTRAINT role_pk PRIMARY KEY,
    tenant_id                   INT NOT NULL,
    name                        VARCHAR(50) NOT NULL,
    full_access_ind             BOOLEAN DEFAULT false,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0,
    CONSTRAINT role_tenant_fk FOREIGN KEY (tenant_id) REFERENCES tenant (tenant_id)
);

CREATE INDEX IF NOT EXISTS role_tenant_idx ON role (tenant_id);

CREATE TABLE IF NOT EXISTS audit_role (
    audit_id                    BIGSERIAL CONSTRAINT audit_role_pk PRIMARY KEY,
    audit_reason                CHAR(1) NOT NULL,
    audit_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    role_id                     BIGINT,
    tenant_id                   INT,
    name                        VARCHAR(50),
    full_access_ind             BOOLEAN,
    active_ind                  BOOLEAN,
    created_dt_tm               TIMESTAMP WITH TIME ZONE,
    created_id                  BIGINT,
    created_source              VARCHAR(50),
    updated_dt_tm               TIMESTAMP WITH TIME ZONE,
    updated_id                  BIGINT,
    updated_source              VARCHAR(50),
    updated_cnt                 INT
);

CREATE INDEX IF NOT EXISTS audit_role_idx ON audit_role (role_id);
