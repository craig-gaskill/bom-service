CREATE TABLE IF NOT EXISTS tenant_feature (
    tenant_feature_id           BIGSERIAL CONSTRAINT tenant_feature_pk PRIMARY KEY,
    tenant_id                   INT NOT NULL,
    feature_id                  SMALLINT NOT NULL,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL DEFAULT 'MANUAL',
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL DEFAULT 'MANUAL',
    updated_cnt                 INT DEFAULT 0,
    CONSTRAINT tenant_feature_tenant_fk FOREIGN KEY (tenant_id) REFERENCES tenant (tenant_id),
    CONSTRAINT tenant_feature_feature_fk FOREIGN KEY (feature_id) REFERENCES feature (feature_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS tenant_feature_udx ON tenant_feature (tenant_id, feature_id);

CREATE TABLE IF NOT EXISTS audit_tenant_feature (
    audit_id                    BIGSERIAL CONSTRAINT audit_tenant_feature_pk PRIMARY KEY,
    audit_reason                CHAR(1) NOT NULL,
    audit_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    tenant_feature_id           BIGINT,
    tenant_id                   INT,
    feature_id                  SMALLINT,
    active_ind                  BOOLEAN,
    created_dt_tm               TIMESTAMP WITH TIME ZONE,
    created_id                  BIGINT,
    created_source              VARCHAR(50),
    updated_dt_tm               TIMESTAMP WITH TIME ZONE,
    updated_id                  BIGINT,
    updated_source              VARCHAR(50),
    updated_cnt                 INT
);

CREATE INDEX IF NOT EXISTS audit_tenant_feature_idx ON audit_tenant_feature (tenant_feature_id);
