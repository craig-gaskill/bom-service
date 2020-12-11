CREATE OR REPLACE FUNCTION person_trig_function() RETURNS TRIGGER AS $person_trig$
DECLARE
    audit_reason CHAR(1);

BEGIN
    IF     (TG_OP = 'DELETE') THEN audit_reason = 'D';
    ELSEIF (TG_OP = 'UPDATE') THEN audit_reason = 'U';
    ELSEIF (TG_OP = 'INSERT') THEN audit_reason = 'I';
    END IF;

    IF (TG_OP = 'DELETE') THEN
        INSERT INTO audit_person (
             audit_reason
            ,person_id
            ,tenant_id
            ,given_name
            ,given_name_key
            ,common_name
            ,middle_name
            ,family_name
            ,family_name_key
            ,prefix
            ,suffix
            ,dob_dt
            ,gender_cd
            ,gender_identity_cd
            ,marital_status_cd
            ,ethnicity_cd
            ,race_cd
            ,locale_language
            ,locale_country
            ,active_ind
            ,created_dt_tm
            ,created_id
            ,created_source
            ,updated_dt_tm
            ,updated_id
            ,updated_source
            ,updated_cnt
        ) VALUES (
             audit_reason
            ,OLD.person_id
            ,OLD.tenant_id
            ,OLD.given_name
            ,OLD.given_name_key
            ,OLD.common_name
            ,OLD.middle_name
            ,OLD.family_name
            ,OLD.family_name_key
            ,OLD.prefix
            ,OLD.suffix
            ,OLD.dob_dt
            ,OLD.gender_cd
            ,OLD.gender_identity_cd
            ,OLD.marital_status_cd
            ,OLD.ethnicity_cd
            ,OLD.race_cd
            ,OLD.locale_language
            ,OLD.locale_country
            ,OLD.active_ind
            ,OLD.created_dt_tm
            ,OLD.created_id
            ,OLD.created_source
            ,OLD.updated_dt_tm
            ,OLD.updated_id
            ,OLD.updated_source
            ,OLD.updated_cnt
        );

        RETURN OLD;
    ELSE
        INSERT INTO audit_person (
             audit_reason
            ,person_id
            ,tenant_id
            ,given_name
            ,given_name_key
            ,common_name
            ,middle_name
            ,family_name
            ,family_name_key
            ,prefix
            ,suffix
            ,dob_dt
            ,gender_cd
            ,gender_identity_cd
            ,marital_status_cd
            ,ethnicity_cd
            ,race_cd
            ,locale_language
            ,locale_country
            ,active_ind
            ,created_dt_tm
            ,created_id
            ,created_source
            ,updated_dt_tm
            ,updated_id
            ,updated_source
            ,updated_cnt
        ) VALUES (
             audit_reason
            ,NEW.person_id
            ,NEW.tenant_id
            ,NEW.given_name
            ,NEW.given_name_key
            ,NEW.common_name
            ,NEW.middle_name
            ,NEW.family_name
            ,NEW.family_name_key
            ,NEW.prefix
            ,NEW.suffix
            ,NEW.dob_dt
            ,NEW.gender_cd
            ,NEW.gender_identity_cd
            ,NEW.marital_status_cd
            ,NEW.ethnicity_cd
            ,NEW.race_cd
            ,NEW.locale_language
            ,NEW.locale_country
            ,NEW.active_ind
            ,NEW.created_dt_tm
            ,NEW.created_id
            ,NEW.created_source
            ,NEW.updated_dt_tm
            ,NEW.updated_id
            ,NEW.updated_source
            ,NEW.updated_cnt
        );

        RETURN NEW;
    END IF;
END;
$person_trig$ LANGUAGE plpgsql;

CREATE TRIGGER person_trig AFTER INSERT OR UPDATE OR DELETE ON person
    FOR EACH ROW EXECUTE PROCEDURE person_trig_function();
