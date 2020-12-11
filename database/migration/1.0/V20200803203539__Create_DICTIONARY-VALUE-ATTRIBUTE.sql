CREATE TABLE IF NOT EXISTS dictionary_value_attribute (
    dictionary_value_attribute_id BIGSERIAL CONSTRAINT dictionary_value_attribute_pk PRIMARY KEY,
    tenant_id                     INT NOT NULL,
    dictionary_value_id           BIGINT NOT NULL,
    dictionary_attribute_id       BIGINT NOT NULL,
    attribute_value               VARCHAR(50) NOT NULL,
    active_ind                    BOOLEAN DEFAULT true,
    created_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                    BIGINT NOT NULL,
    created_source                VARCHAR(50) NULL,
    updated_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                    BIGINT NOT NULL,
    updated_source                VARCHAR(50) NULL,
    updated_cnt                   INT DEFAULT 0,
    CONSTRAINT dictionary_value_attribute_tenant_fk FOREIGN KEY (tenant_id) REFERENCES tenant (tenant_id),
    CONSTRAINT dictionary_value_attribute_value_fk FOREIGN KEY (dictionary_value_id) REFERENCES dictionary_value (dictionary_value_id)
);

CREATE INDEX IF NOT EXISTS dictionary_value_attribute_attr_idx ON dictionary_value_attribute (tenant_id, dictionary_attribute_id);
CREATE INDEX IF NOT EXISTS dictionary_value_attribute_value_idx ON dictionary_value_attribute (tenant_id, dictionary_value_id);

CREATE TABLE IF NOT EXISTS audit_dictionary_value_attribute (
    audit_id                      BIGSERIAL CONSTRAINT audit_dictionary_value_attribute_pk PRIMARY KEY,
    audit_reason                  CHAR(1) NOT NULL,
    audit_dt_tm                   TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    dictionary_value_attribute_id BIGINT NOT NULL,
    tenant_id                     INT,
    dictionary_value_id           BIGINT,
    dictionary_attribute_id       BIGINT,
    attribute_value               VARCHAR(50),
    active_ind                    BOOLEAN,
    created_dt_tm                 TIMESTAMP WITH TIME ZONE,
    created_id                    BIGINT,
    created_source                VARCHAR(50),
    updated_dt_tm                 TIMESTAMP WITH TIME ZONE,
    updated_id                    BIGINT,
    updated_source                VARCHAR(50),
    updated_cnt                   INT
);

CREATE INDEX IF NOT EXISTS audit_dictionary_value_attribute_idx ON audit_dictionary_value_attribute (dictionary_value_attribute_id);
