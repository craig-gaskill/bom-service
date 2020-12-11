CREATE OR REPLACE FUNCTION users_access_trig_function() RETURNS TRIGGER AS $users_access_trig$
DECLARE
    audit_reason CHAR(1);

BEGIN
    IF     (TG_OP = 'DELETE') THEN audit_reason = 'D';
    ELSEIF (TG_OP = 'UPDATE') THEN audit_reason = 'U';
    ELSEIF (TG_OP = 'INSERT') THEN audit_reason = 'I';
    END IF;

    IF (TG_OP = 'DELETE') THEN
        INSERT INTO audit_users_access (
             audit_reason
            ,user_access_id
            ,user_id
            ,tenant_id
            ,person_id
            ,default_ind
            ,last_login_dt_tm
            ,last_login_ip
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
            ,OLD.user_access_id
            ,OLD.user_id
            ,OLD.tenant_id
            ,OLD.person_id
            ,OLD.default_ind
            ,OLD.last_login_dt_tm
            ,OLD.last_login_ip
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
        INSERT INTO audit_users_access (
             audit_reason
            ,user_access_id
            ,user_id
            ,tenant_id
            ,person_id
            ,default_ind
            ,last_login_dt_tm
            ,last_login_ip
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
            ,NEW.user_access_id
            ,NEW.user_id
            ,NEW.tenant_id
            ,NEW.person_id
            ,NEW.default_ind
            ,NEW.last_login_dt_tm
            ,NEW.last_login_ip
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
$users_access_trig$ LANGUAGE plpgsql;

CREATE TRIGGER users_access_trig AFTER INSERT OR UPDATE OR DELETE ON users_access
    FOR EACH ROW EXECUTE PROCEDURE users_access_trig_function();
