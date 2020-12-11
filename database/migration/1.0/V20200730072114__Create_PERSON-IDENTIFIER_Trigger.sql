CREATE OR REPLACE FUNCTION person_identifier_trig_function() RETURNS TRIGGER AS $person_identifier_trig$
DECLARE
    audit_reason CHAR(1);

BEGIN
    IF     (TG_OP = 'DELETE') THEN audit_reason = 'D';
    ELSEIF (TG_OP = 'UPDATE') THEN audit_reason = 'U';
    ELSEIF (TG_OP = 'INSERT') THEN audit_reason = 'I';
    END IF;

    IF (TG_OP = 'DELETE') THEN
        INSERT INTO audit_person_identifier (
             audit_reason
            ,person_identifier_id
            ,tenant_id
            ,person_id
            ,identifier_type_cd
            ,identifier_value
            ,sensitive_ind
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
            ,OLD.person_identifier_id
            ,OLD.tenant_id
            ,OLD.person_id
            ,OLD.identifier_type_cd
            ,OLD.identifier_value
            ,OLD.sensitive_ind
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
        INSERT INTO audit_person_identifier (
             audit_reason
            ,person_identifier_id
            ,tenant_id
            ,person_id
            ,identifier_type_cd
            ,identifier_value
            ,sensitive_ind
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
            ,NEW.person_identifier_id
            ,NEW.tenant_id
            ,NEW.person_id
            ,NEW.identifier_type_cd
            ,NEW.identifier_value
            ,NEW.sensitive_ind
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
$person_identifier_trig$ LANGUAGE plpgsql;

CREATE TRIGGER person_identifier_trig AFTER INSERT OR UPDATE OR DELETE ON person_identifier
    FOR EACH ROW EXECUTE PROCEDURE person_identifier_trig_function();
