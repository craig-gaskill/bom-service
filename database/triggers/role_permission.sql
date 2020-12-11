CREATE OR REPLACE FUNCTION role_permission_trig_function() RETURNS TRIGGER AS $role_permission_trig$
DECLARE
    audit_reason CHAR(1);

BEGIN
    IF     (TG_OP = 'DELETE') THEN audit_reason = 'D';
    ELSEIF (TG_OP = 'UPDATE') THEN audit_reason = 'U';
    ELSEIF (TG_OP = 'INSERT') THEN audit_reason = 'I';
    END IF;

    IF (TG_OP = 'DELETE') THEN
        INSERT INTO audit_role_permission (
             audit_reason
            ,role_permission_id
            ,tenant_id
            ,role_id
            ,permission_id
            ,granted_ind
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
            ,OLD.role_permission_id
            ,OLD.tenant_id
            ,OLD.role_id
            ,OLD.permission_id
            ,OLD.granted_ind
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
        INSERT INTO audit_role_permission (
             audit_reason
            ,role_permission_id
            ,tenant_id
            ,role_id
            ,permission_id
            ,granted_ind
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
            ,NEW.role_permission_id
            ,NEW.tenant_id
            ,NEW.role_id
            ,NEW.permission_id
            ,NEW.granted_ind
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
$role_permission_trig$ LANGUAGE plpgsql;

CREATE TRIGGER role_trig AFTER INSERT OR UPDATE OR DELETE ON role_permission
   FOR EACH ROW EXECUTE PROCEDURE role_permission_trig_function();
