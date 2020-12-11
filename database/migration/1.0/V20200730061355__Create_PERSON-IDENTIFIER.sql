CREATE TABLE IF NOT EXISTS person_identifier (
    person_identifier_id        BIGSERIAL CONSTRAINT person_identifier_pk PRIMARY KEY,
    tenant_id                   INT NOT NULL,
    person_id                   BIGINT NOT NULL,
    identifier_type_cd          BIGINT NOT NULL,
    identifier_value            VARCHAR(50) NOT NULL,
    sensitive_ind               BOOLEAN DEFAULT false,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0,
    CONSTRAINT person_identifier_tenant_fk FOREIGN KEY (tenant_id) REFERENCES tenant (tenant_id),
    CONSTRAINT person_identifier_person_fk FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE INDEX IF NOT EXISTS person_identifier_person_idx ON person_identifier (tenant_id, person_id);

CREATE TABLE IF NOT EXISTS audit_person_identifier (
    audit_id                    BIGSERIAL CONSTRAINT audit_person_identifier_pk PRIMARY KEY,
    audit_reason                CHAR(1) NOT NULL,
    audit_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    person_identifier_id        BIGINT NOT NULL,
    tenant_id                   INT,
    person_id                   BIGINT,
    identifier_type_cd          BIGINT,
    identifier_value            VARCHAR(50),
    sensitive_ind               BOOLEAN,
    active_ind                  BOOLEAN,
    created_dt_tm               TIMESTAMP WITH TIME ZONE,
    created_id                  BIGINT,
    created_source              VARCHAR(50),
    updated_dt_tm               TIMESTAMP WITH TIME ZONE,
    updated_id                  BIGINT,
    updated_source              VARCHAR(50),
    updated_cnt                 INT
);

CREATE INDEX IF NOT EXISTS audit_person_identifier_idx ON audit_person_identifier (person_identifier_id);
