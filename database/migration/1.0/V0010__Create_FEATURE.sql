CREATE TABLE IF NOT EXISTS feature (
    feature_id                  SMALLSERIAL CONSTRAINT feature_pk PRIMARY KEY,
    display                     VARCHAR(50) NOT NULL,
    meaning                     VARCHAR(50) NOT NULL,
    description                 VARCHAR(100) NULL,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0
);

CREATE INDEX IF NOT EXISTS feature_meaning_idx ON feature (meaning);

CREATE TABLE IF NOT EXISTS audit_feature (
    audit_id                    BIGSERIAL CONSTRAINT audit_feature_pk PRIMARY KEY,
    audit_reason                CHAR(1) NOT NULL,
    audit_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    feature_id                  SMALLINT,
    display                     VARCHAR(50),
    meaning                     VARCHAR(50),
    description                 VARCHAR(100),
    active_ind                  BOOLEAN,
    created_dt_tm               TIMESTAMP WITH TIME ZONE,
    created_id                  BIGINT,
    created_source              VARCHAR(50),
    updated_dt_tm               TIMESTAMP WITH TIME ZONE,
    updated_id                  BIGINT,
    updated_source              VARCHAR(50),
    updated_cnt                 INT
);

CREATE INDEX IF NOT EXISTS audit_feature_idx ON audit_feature (feature_id);
