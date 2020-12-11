CREATE TABLE IF NOT EXISTS tenant (
    tenant_id                   SERIAL CONSTRAINT tenant_pk PRIMARY KEY,
    name                        VARCHAR(50) NOT NULL,
    name_key                    VARCHAR(50) NOT NULL,
    mnemonic                    VARCHAR(50) NOT NULL,
    locale_language             VARCHAR(5) DEFAULT 'en',
    locale_country              VARCHAR(5) DEFAULT 'US',
    subscription_type           SMALLINT DEFAULT 0,
    subscription_start_dt       DATE NULL,
    subscription_end_dt         DATE NULL,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0
);

CREATE INDEX IF NOT EXISTS tenant_mnemonic_idx ON tenant (mnemonic);

CREATE TABLE IF NOT EXISTS audit_tenant (
    audit_id                    BIGSERIAL CONSTRAINT audit_tenant_pk PRIMARY KEY,
    audit_reason                CHAR(1) NOT NULL,
    audit_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    tenant_id                   INT,
    name                        VARCHAR(50),
    name_key                    VARCHAR(50),
    mnemonic                    VARCHAR(50),
    locale_language             VARCHAR(5),
    locale_country              VARCHAR(5),
    subscription_type           SMALLINT,
    subscription_start_dt       DATE,
    subscription_end_dt         DATE,
    active_ind                  BOOLEAN,
    created_dt_tm               TIMESTAMP WITH TIME ZONE,
    created_id                  BIGINT,
    created_source              VARCHAR(50),
    updated_dt_tm               TIMESTAMP WITH TIME ZONE,
    updated_id                  BIGINT,
    updated_source              VARCHAR(50),
    updated_cnt                 INT
);

CREATE INDEX IF NOT EXISTS audit_tenant_idx ON audit_tenant (tenant_id);
