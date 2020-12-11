CREATE OR REPLACE FUNCTION feature_trig_function() RETURNS TRIGGER AS $feature_trig$
DECLARE
    audit_reason CHAR(1);

BEGIN
    IF     (TG_OP = 'DELETE') THEN audit_reason = 'D';
    ELSEIF (TG_OP = 'UPDATE') THEN audit_reason = 'U';
    ELSEIF (TG_OP = 'INSERT') THEN audit_reason = 'I';
    END IF;

    IF (TG_OP = 'DELETE') THEN
        INSERT INTO audit_feature (
             audit_reason
            ,feature_id
            ,display
            ,meaning
            ,description
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
            ,OLD.feature_id
            ,OLD.display
            ,OLD.meaning
            ,OLD.description
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
        INSERT INTO audit_feature (
             audit_reason
            ,feature_id
            ,display
            ,meaning
            ,description
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
            ,NEW.feature_id
            ,NEW.display
            ,NEW.meaning
            ,NEW.description
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
$feature_trig$ LANGUAGE plpgsql;

CREATE TRIGGER feature_trig AFTER INSERT OR UPDATE OR DELETE ON feature
   FOR EACH ROW EXECUTE PROCEDURE feature_trig_function();
