CREATE OR REPLACE FUNCTION dictionary_value_trig_function() RETURNS TRIGGER AS $dictionary_value_trig$
DECLARE
    audit_reason CHAR(1);

BEGIN
    IF     (TG_OP = 'DELETE') THEN audit_reason = 'D';
    ELSEIF (TG_OP = 'UPDATE') THEN audit_reason = 'U';
    ELSEIF (TG_OP = 'INSERT') THEN audit_reason = 'I';
    END IF;

    IF (TG_OP = 'DELETE') THEN
        INSERT INTO audit_dictionary_value (
             audit_reason
            ,dictionary_value_id
            ,tenant_id
            ,dictionary_id
            ,display
            ,display_key
            ,meaning
            ,description
            ,viewable_ind
            ,editable_ind
            ,deletable_ind
            ,sort_order
            ,created_dt_tm
            ,created_id
            ,created_source
            ,updated_dt_tm
            ,updated_id
            ,updated_source
            ,updated_cnt
        ) VALUES (
             audit_reason
            ,OLD.dictionary_value_id
            ,OLD.tenant_id
            ,OLD.dictionary_id
            ,OLD.display
            ,OLD.display_key
            ,OLD.meaning
            ,OLD.description
            ,OLD.viewable_ind
            ,OLD.editable_ind
            ,OLD.deletable_ind
            ,OLD.sort_order
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
        INSERT INTO audit_dictionary_value (
             audit_reason
            ,dictionary_value_id
            ,tenant_id
            ,dictionary_id
            ,display
            ,display_key
            ,meaning
            ,description
            ,viewable_ind
            ,editable_ind
            ,deletable_ind
            ,sort_order
            ,created_dt_tm
            ,created_id
            ,created_source
            ,updated_dt_tm
            ,updated_id
            ,updated_source
            ,updated_cnt
        ) VALUES (
             audit_reason
            ,NEW.dictionary_value_id
            ,NEW.tenant_id
            ,NEW.dictionary_id
            ,NEW.display
            ,NEW.display_key
            ,NEW.meaning
            ,NEW.description
            ,NEW.viewable_ind
            ,NEW.editable_ind
            ,NEW.deletable_ind
            ,NEW.sort_order
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
$dictionary_value_trig$ LANGUAGE plpgsql;

CREATE TRIGGER dictionary_trig AFTER INSERT OR UPDATE OR DELETE ON dictionary_value
    FOR EACH ROW EXECUTE PROCEDURE dictionary_value_trig_function();
