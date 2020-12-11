CREATE TABLE IF NOT EXISTS permission (
    permission_id               SMALLSERIAL CONSTRAINT permission_pk PRIMARY KEY,
    feature_id                  SMALLINT DEFAULT 1,
    display                     VARCHAR(50) NOT NULL,
    code                        VARCHAR(100) NOT NULL UNIQUE,
    description                 VARCHAR(100) NULL,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0,
    CONSTRAINT permission_feature_fk FOREIGN KEY (feature_id) REFERENCES feature (feature_id)
);

CREATE INDEX IF NOT EXISTS permission_feature_idx ON permission (feature_id);
CREATE INDEX IF NOT EXISTS permission_code_idx ON permission (code);

CREATE TABLE IF NOT EXISTS audit_permission (
    audit_id                    BIGSERIAL CONSTRAINT audit_permission_pk PRIMARY KEY,
    audit_reason                CHAR(1) NOT NULL,
    audit_dt_tm                 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    permission_id               SMALLINT,
    feature_id                  SMALLINT,
    display                     VARCHAR(50),
    code                        VARCHAR(100),
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
