CREATE TABLE IF NOT EXISTS dictionary_value_mapping (
    dictionary_value_mapping_id BIGSERIAL CONSTRAINT dictionary_value_mapping_pk PRIMARY KEY,
    tenant_id                   INT NOT NULL,
    dictionary_value_id         BIGINT NOT NULL,
    display                     VARCHAR(50) NOT NULL,
    meaning                     VARCHAR(50) NOT NULL,
    containing_system           VARCHAR(50) NOt NULL,
    viewable_ind                BOOLEAN DEFAULT true,
    editable_ind                BOOLEAN DEFAULT true,
    deletable_ind               BOOLEAN DEFAULT true,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0,
    CONSTRAINT dictionary_value_mapping_tenant_fk FOREIGN KEY (tenant_id) REFERENCES tenant (tenant_id),
    CONSTRAINT dictionary_value_mapping_value_fk FOREIGN KEY (dictionary_value_id) REFERENCES dictionary_value (dictionary_value_id)
);

CREATE INDEX IF NOT EXISTS dictionary_value_mapping_meaning_idx ON dictionary_value_mapping (tenant_id, dictionary_value_id, meaning);
CREATE INDEX IF NOT EXISTS dictionary_value_mapping_system_idx ON dictionary_value_mapping (tenant_id, dictionary_value_id, containing_system);

CREATE TABLE IF NOT EXISTS audit_dictionary_value_mapping (
    audit_id                    BIGSERIAL CONSTRAINT audit_dictionary_value_mapping_pk PRIMARY KEY,
    audit_reason                CHAR(1) NOT NULL,
    audit_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    dictionary_value_mapping_id BIGINT NOT NULL,
    tenant_id                   INT,
    dictionary_value_id         BIGINT,
    display                     VARCHAR(50),
    meaning                     VARCHAR(50),
    containing_system           VARCHAR(50),
    viewable_ind                BOOLEAN,
    editable_ind                BOOLEAN,
    deletable_ind               BOOLEAN,
    active_ind                  BOOLEAN,
    created_dt_tm               TIMESTAMP WITH TIME ZONE,
    created_id                  BIGINT,
    created_source              VARCHAR(50),
    updated_dt_tm               TIMESTAMP WITH TIME ZONE,
    updated_id                  BIGINT,
    updated_source              VARCHAR(50),
    updated_cnt                 INT
);

CREATE INDEX IF NOT EXISTS audit_dictionary_value_mapping_idx ON audit_dictionary_value_mapping (dictionary_value_mapping_id);
