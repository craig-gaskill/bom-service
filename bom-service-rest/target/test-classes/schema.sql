DROP TABLE IF EXISTS dictionary_value;
DROP TABLE IF EXISTS dictionary_attribute;
DROP TABLE IF EXISTS dictionary;
DROP TABLE IF EXISTS users_access;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS tenant_feature;
DROP TABLE IF EXISTS tenant;
DRop TABLE IF EXISTS feature;

CREATE TABLE IF NOT EXISTS feature (
    feature_id                  SMALLINT AUTO_INCREMENT CONSTRAINT feature_pk PRIMARY KEY,
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

CREATE TABLE IF NOT EXISTS tenant (
    tenant_id                   INT AUTO_INCREMENT CONSTRAINT tenant_pk PRIMARY KEY,
    name                        VARCHAR(50) NOT NULL,
    name_key                    VARCHAR(50) NOT NULL,
    mnemonic                    VARCHAR(50) NOT NULL,
    locale_language             VARCHAR(5) NULL,
    locale_country              VARCHAR(5) NULL,
    subscription_type           SMALLINT DEFAULT 0,
    subscription_start_dt       DATE NULL,
    subscription_end_dt         DATE NULL,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  INT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  INT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS tenant_feature (
    tenant_feature_id           BIGINT AUTO_INCREMENT CONSTRAINT tenant_feature_pk PRIMARY KEY,
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

CREATE TABLE IF NOT EXISTS person (
    person_id                   BIGINT AUTO_INCREMENT CONSTRAINT person_pk PRIMARY KEY,
    tenant_id                   INT NOT NULL,
    given_name                  VARCHAR(50) NOT NULL,
    given_name_key              VARCHAR(50) NOT NULL,
    common_name                 VARCHAR(50) NULL,
    middle_name                 VARCHAR(50) NULL,
    family_name                 VARCHAR(50) NOT NULL,
    family_name_key             VARCHAR(50) NOT NULL,
    prefix                      VARCHAR(50) NULL,
    suffix                      VARCHAR(50) NULL,
    dob_dt                      DATE NULL,
    dod_dt                      DATE NULL,
    gender_cd                   BIGINT NULL,
    gender_identity_cd          BIGINT NULL,
    marital_status_cd           BIGINT NULL,
    ethnicity_cd                BIGINT NULL,
    race_cd                     BIGINT NULL,
    locale_language             VARCHAR(5) NULL,
    locale_country              VARCHAR(5) NULL,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  INT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  INT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS users (
    user_id                     BIGINT AUTO_INCREMENT CONSTRAINT users_pk PRIMARY KEY,
    username                    VARCHAR(256) NOT NULL,
    password                    CHAR(60) NOT NULL,
    temporary_password_ind      BOOLEAN DEFAULT true,
    login_attempts              INT DEFAULT 0,
    account_locked_dt_tm        TIMESTAMP WITH TIME ZONE NULL,
    account_locked_type         SMALLINT NULL,
    account_expired_dt_tm       TIMESTAMP WITH TIME ZONE NULL,
    password_changed_dt_tm      TIMESTAMP WITH TIME ZONE NULL,
    password_expired_dt_tm      TIMESTAMP WITH TIME ZONE NULL,
    admin_ind                   BOOLEAN DEFAULT false,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS users_access (
    user_access_id              BIGINT AUTO_INCREMENT CONSTRAINT users_access_pk PRIMARY KEY,
    user_id                     BIGINT NOT NULL,
    tenant_id                   INT NOT NULL,
    person_id                   BIGINT NOT NULL,
    default_ind                 BOOLEAN DEFAULT false,
    last_login_dt_tm            TIMESTAMP WITH TIME ZONE NULL,
    last_login_ip               VARCHAR(50) NULL,
    active_ind                  BOOLEAN DEFAULT true,
    created_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_id                  BIGINT NOT NULL,
    created_source              VARCHAR(50) NULL,
    updated_dt_tm               TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_id                  BIGINT NOT NULL,
    updated_source              VARCHAR(50) NULL,
    updated_cnt                 INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS dictionary (
    dictionary_id               BIGINT AUTO_INCREMENT CONSTRAINT dictionary_pk PRIMARY KEY,
    tenant_id                   INT NOT NULL,
    feature_id                  SMALLINT DEFAULT 0,
    display                     VARCHAR(50) NOT NULL,
    display_key                 VARCHAR(50) NOT NULL,
    meaning                     VARCHAR(50) NOT NULL,
    description                 VARCHAR(100) NULL,
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
    updated_cnt                 INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS dictionary_attribute (
    dictionary_attribute_id     BIGINT AUTO_INCREMENT CONSTRAINT dictionary_attribute_pk PRIMARY KEY,
    tenant_id                   BIGINT NOT NULL,
    dictionary_id               BIGINT NOT NULL,
    display                     VARCHAR(50) NOT NULL,
    display_key                 VARCHAR(50) NOT NULL,
    meaning                     VARCHAR(50) NOT NULL,
    description                 VARCHAR(100) NULL,
    attribute_type              SMALLINT NOT NULL,
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
    updated_cnt                 INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS dictionary_value (
    dictionary_value_id         BIGINT AUTO_INCREMENT CONSTRAINT dictionary_value_pk PRIMARY KEY,
    tenant_id                   BIGINT NOT NULL,
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
    updated_cnt                 INT DEFAULT 0
);
