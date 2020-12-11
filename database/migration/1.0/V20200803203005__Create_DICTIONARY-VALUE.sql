CREATE TABLE IF NOT EXISTS dictionary_value (
    dictionary_value_id         BIGSERIAL CONSTRAINT dictionary_value_pk PRIMARY KEY,
    tenant_id                   INT NOT NULL,
    dictionary_id               BIGINT NOT NULL,
    display                     VARCHAR(50) NOT NULL,
    display_key                 VARCHAR(50) NOT NULL,
    meaning                     VARCHAR(50) NOT NULL,
    description                 VARCHAR(100) NULL,
    viewable_ind                BOOLEAN DEFAULT true,
    editable_ind                BOOLEAN DEFAULT true,
    deletable_ind               BOOLEAN DEFAULT true,
    sort_order                  INT NULL,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0,
    CONSTRAINT dictionary_value_tenant_fk FOREIGN KEY (tenant_id) REFERENCES tenant (tenant_id),
    CONSTRAINT dictionary_value_dictionary_fk FOREIGN KEY (dictionary_id) REFERENCES dictionary (dictionary_id)
);

CREATE INDEX IF NOT EXISTS dictionary_value_meaning_idx ON dictionary_value (tenant_id, dictionary_id, meaning);
CREATE INDEX IF NOT EXISTS dictionary_value_display_idx ON dictionary_value (tenant_id, dictionary_id, display_key);

CREATE TABLE IF NOT EXISTS audit_dictionary_value (
    audit_id                    BIGSERIAL CONSTRAINT audit_dictionary_value_pk PRIMARY KEY,
    audit_reason                CHAR(1) NOT NULL,
    audit_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    dictionary_value_id         BIGINT NOT NULL,
    tenant_id                   INT,
    dictionary_id               BIGINT,
    display                     VARCHAR(50),
    display_key                 VARCHAR(50),
    meaning                     VARCHAR(50),
    description                 VARCHAR(100),
    type                        SMALLINT,
    viewable_ind                BOOLEAN,
    editable_ind                BOOLEAN,
    deletable_ind               BOOLEAN,
    sort_order                  INT,
    active_ind                  BOOLEAN,
    created_dt_tm               TIMESTAMP WITH TIME ZONE,
    created_id                  BIGINT,
    created_source              VARCHAR(50),
    updated_dt_tm               TIMESTAMP WITH TIME ZONE,
    updated_id                  BIGINT,
    updated_source              VARCHAR(50),
    updated_cnt                 INT
);

CREATE INDEX IF NOT EXISTS audit_dictionary_value_idx ON audit_dictionary_value (dictionary_value_id);
