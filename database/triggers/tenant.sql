CREATE OR REPLACE FUNCTION tenant_trig_function() RETURNS TRIGGER AS $tenant_trig$
DECLARE
    audit_reason CHAR(1);

BEGIN
    IF     (TG_OP = 'DELETE') THEN audit_reason = 'D';
    ELSEIF (TG_OP = 'UPDATE') THEN audit_reason = 'U';
    ELSEIF (TG_OP = 'INSERT') THEN audit_reason = 'I';
    END IF;

    IF (TG_OP = 'DELETE') THEN
        INSERT INTO audit_tenant (
             audit_reason
            ,tenant_id
            ,name
            ,name_key
            ,mnemonic
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
            ,OLD.tenant_id
            ,OLD.name
            ,OLD.name_key
            ,OLD.mnemonic
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
        INSERT INTO audit_tenant (
            audit_reason
            ,tenant_id
            ,name
            ,name_key
            ,mnemonic
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
            ,NEW.tenant_id
            ,NEW.name
            ,NEW.name_key
            ,NEW.mnemonic
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
$tenant_trig$ LANGUAGE plpgsql;

CREATE TRIGGER tenant_trig AFTER INSERT OR UPDATE OR DELETE ON tenant
    FOR EACH ROW EXECUTE PROCEDURE tenant_trig_function();
