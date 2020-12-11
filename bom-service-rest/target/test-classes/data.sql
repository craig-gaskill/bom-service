-- Insert Features
INSERT INTO feature (feature_id, display, meaning, created_id, updated_id)
VALUES (1, 'Core', 'CORE', 1, 1);

INSERT INTO feature (feature_id, display, meaning, created_id, updated_id)
VALUES (2, 'Scheduling', 'SCHEDULING', 1, 1);

INSERT INTO feature (feature_id, display, meaning, created_id, updated_id)
VALUES (3, 'Billing', 'BILLING', 1, 1);

-- Insert Tenants
INSERT INTO tenant (tenant_id, name, name_key, mnemonic, subscription_start_dt, created_id, updated_id)
VALUES (1, 'Sandbox', 'SANDBOX', 'SANDBOX', '2020-03-01', 1, 1);

INSERT INTO tenant (tenant_id, name, name_key, mnemonic, created_id, updated_id)
VALUES (2, 'Demo', 'DEMO', 'DEMO', 1, 1);

INSERT INTO tenant (tenant_id, name, name_key, mnemonic, created_id, updated_id)
VALUES (3, 'CAGST', 'CAGST', 'CAGST', 1, 1);

INSERT INTO tenant (tenant_id, name, name_key, mnemonic, created_id, updated_id)
VALUES (4, 'Santa', 'SANTA', 'SANTA', 1, 1);

INSERT INTO tenant (tenant_id, name, name_key, mnemonic, created_id, updated_id)
VALUES (5, 'Nemo', 'NEMO', 'NEMO', 1, 1);

INSERT INTO tenant_feature (tenant_id, feature_id, created_id, updated_id)
VALUES (1, 1, 1, 1);

INSERT INTO tenant_feature (tenant_id, feature_id, created_id, updated_id)
VALUES (2, 1, 1, 1);

INSERT INTO tenant_feature (tenant_id, feature_id, created_id, updated_id)
VALUES (3, 1, 1, 1);

INSERT INTO tenant_feature (tenant_id, feature_id, created_id, updated_id)
VALUES (4, 1, 1, 1);

INSERT INTO tenant_feature (tenant_id, feature_id, created_id, updated_id)
VALUES (5, 1, 1, 1);

-- Insert People
INSERT INTO person (person_id, tenant_id, given_name, given_name_key, family_name, family_name_key, dob_dt, created_id, updated_id)
VALUES (1, 1, 'Adam', 'ADAM',  'West', 'WEST', '2000-01-23', 1, 1);

INSERT INTO person (person_id, tenant_id, given_name, given_name_key, common_name, family_name, family_name_key, created_id, updated_id)
VALUES (2, 1, 'Willy', 'WILLY', 'Bill', 'Wonka', 'WONKA', 1, 1);

INSERT INTO person (person_id, tenant_id, given_name, given_name_key, common_name, family_name, family_name_key, created_id, updated_id)
VALUES (3, 1, 'Willie', 'WILLIE', 'Will', 'Nelson', 'NELSON', 1, 1);

INSERT INTO person (person_id, tenant_id, given_name, given_name_key, family_name, family_name_key, created_id, updated_id)
VALUES (4, 1, 'Ansel', 'ANSEL', 'Adam', 'ADAM', 1, 1);

INSERT INTO person (person_id, tenant_id, given_name, given_name_key, family_name, family_name_key, created_id, updated_id)
VALUES (5, 2, 'Fred', 'FRED', 'Flintstone', 'FLINTSTONE', 1, 1);

INSERT INTO person (person_id, tenant_id, given_name, given_name_key, family_name, family_name_key, created_id, updated_id)
VALUES (6, 2, 'Billy Bob', 'BILLY_BOB', 'Macoy', 'MACOY', 1, 1);

INSERT INTO person (person_id, tenant_id, given_name, given_name_key, family_name, family_name_key, created_id, updated_id)
VALUES (7, 2, 'Billy Jean', 'BILLY_JEAN', 'Hatfield', 'HATFIELD', 1, 1);

-- Insert Users
INSERT INTO users (user_id, username, password, created_id, updated_id)
VALUES (1, 'adamwest@test.com', 'pwd1', 1, 1);

INSERT INTO users (user_id, username, password, created_id, updated_id)
VALUES (2, 'willywonka@test.com', 'pwd2', 1, 1);

INSERT INTO users (user_id, username, password, created_id, updated_id)
VALUES (3, 'willienelson@test.com', 'pwd3', 1, 1);

INSERT INTO users (user_id, username, password, created_id, updated_id)
VALUES (4, 'anseladam@test.com', 'pwd3', 1, 1);

INSERT INTO users_access(user_access_id, user_id, person_id, tenant_id, created_id, updated_id)
VALUES (1, 1, 1, 1, 1, 1);

INSERT INTO users_access(user_access_id, user_id, person_id, tenant_id, created_id, updated_id)
VALUES (2, 2, 2, 1, 1, 1);

INSERT INTO users_access(user_access_id, user_id, person_id, tenant_id, created_id, updated_id)
VALUES (3, 3, 3, 1, 1, 1);

INSERT INTO users_access(user_access_id, user_id, person_id, tenant_id, created_id, updated_id)
VALUES (4, 4, 4, 1, 1, 1);

-- Insert Dictionaries
INSERT INTO dictionary (dictionary_id, tenant_id, feature_id, display, display_key, meaning, viewable_ind, created_id, updated_id)
SELECT 1, 1, f.feature_id, 'Gender', 'GENDER', 'GENDER', true, 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO dictionary (dictionary_id, tenant_id, feature_id, display, display_key, meaning, viewable_ind, created_id, updated_id)
SELECT 2, 1, f.feature_id, 'Address Type', 'ADDRESS_TYPE', 'ADDRESS_TYPE', true, 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO dictionary (dictionary_id, tenant_id, feature_id, display, display_key, meaning, viewable_ind, created_id, updated_id)
SELECT 3, 1, f.feature_id, 'Phone Type', 'PHONE_TYPE', 'PHONE_TYPE', true, 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO dictionary (dictionary_id, tenant_id, feature_id, display, display_key, meaning, viewable_ind, created_id, updated_id)
SELECT 4, 1, f.feature_id, 'Email Type', 'EMAIL_TYPE', 'EMAIL_TYPE', true, 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO dictionary (dictionary_id, tenant_id, feature_id, display, display_key, meaning, viewable_ind, created_id, updated_id)
SELECT 5, 1, f.feature_id, 'Sensitivity Level', 'SENSITIVITY_LEVEL', 'SENSITIVITY_LEVEL', false, 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO dictionary (dictionary_id, tenant_id, feature_id, display, display_key, meaning, viewable_ind, created_id, updated_id)
SELECT 6, 2, f.feature_id, 'Gender', 'GENDER', 'GENDER', true, 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO dictionary (dictionary_id, tenant_id, feature_id, display, display_key, meaning, viewable_ind, created_id, updated_id)
SELECT 7, 2, f.feature_id, 'Address Type', 'ADDRESS_TYPE', 'ADDRESS_TYPE', true, 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

-- Insert Dictionary Attributes
INSERT INTO dictionary_attribute (dictionary_attribute_id, tenant_id, dictionary_id, display, display_key, meaning, attribute_type, created_id, updated_id)
VALUES (1, 1, 3, 'Mobile', 'MOBILE', 'MOBILE', 0, 1, 1);

INSERT INTO dictionary_attribute (dictionary_attribute_id, tenant_id, dictionary_id, display, display_key, meaning, attribute_type, created_id, updated_id)
VALUES (2, 1, 5, 'Low', 'LOW', 'LOW', 1, 1, 1);

INSERT INTO dictionary_attribute (dictionary_attribute_id, tenant_id, dictionary_id, display, display_key, meaning, attribute_type, created_id, updated_id)
VALUES (3, 1, 5, 'Low/Medium', 'LOW_MEDIUM', 'LOW_MEDIUM', 1, 1, 1);

INSERT INTO dictionary_attribute (dictionary_attribute_id, tenant_id, dictionary_id, display, display_key, meaning, attribute_type, created_id, updated_id)
VALUES (4, 1, 5, 'Medium', 'MEDIUM', 'MEDIUM', 1, 1, 1);

INSERT INTO dictionary_attribute (dictionary_attribute_id, tenant_id, dictionary_id, display, display_key, meaning, attribute_type, created_id, updated_id)
VALUES (5, 1, 5, 'Medium/High', 'MEDIUM_HIGH', 'MEDIUM_HIGH', 1, 1, 1);

INSERT INTO dictionary_attribute (dictionary_attribute_id, tenant_id, dictionary_id, display, display_key, meaning, attribute_type, created_id, updated_id)
VALUES (6, 1, 5, 'High', 'HIGH', 'HIGH', 1, 1, 1);

-- Insert Dictionary Values
INSERT INTO dictionary_value (dictionary_value_id, tenant_id, dictionary_id, display, display_key, meaning, created_id, updated_id)
VALUES (1, 1, 1, 'Female', 'FEMALE', 'FEMALE', 1, 1);

INSERT INTO dictionary_value (dictionary_value_id, tenant_id, dictionary_id, display, display_key, meaning, created_id, updated_id)
VALUES (2, 1, 1, 'Male', 'MALE', 'MALE', 1, 1);

INSERT INTO dictionary_value (dictionary_value_id, tenant_id, dictionary_id, display, display_key, meaning, created_id, updated_id)
VALUES (3, 1, 2, 'Home/Residence', 'HOME', 'HOME', 1, 1);

INSERT INTO dictionary_value (dictionary_value_id, tenant_id, dictionary_id, display, display_key, meaning, created_id, updated_id)
VALUES (4, 1, 2, 'Business/Work', 'BUSINESS', 'BUSINESS', 1, 1);

INSERT INTO dictionary_value (dictionary_value_id, tenant_id, dictionary_id, display, display_key, meaning, created_id, updated_id)
VALUES (5, 1, 2, 'Vacation Home', 'VACATION', 'VACATION', 1, 1);

INSERT INTO dictionary_value (dictionary_value_id, tenant_id, dictionary_id, display, display_key, meaning, created_id, updated_id, sort_order)
VALUES (6, 2, 1, 'Female', 'FEMALE', 'FEMALE', 1, 1, 2);

INSERT INTO dictionary_value (dictionary_value_id, tenant_id, dictionary_id, display, display_key, meaning, created_id, updated_id, sort_order)
VALUES (7, 2, 1, 'Male', 'MALE', 'MALE', 1, 1, 1);

INSERT INTO dictionary_value (dictionary_value_id, tenant_id, dictionary_id, display, display_key, meaning, created_id, updated_id)
VALUES (8, 2, 1, 'Unknown', 'UNKNOWN', 'UNKNOWN', 1, 1);
