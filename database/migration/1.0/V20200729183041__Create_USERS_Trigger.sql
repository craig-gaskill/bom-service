CREATE OR REPLACE FUNCTION users_trig_function() RETURNS TRIGGER AS $users_trig$
DECLARE
    audit_reason CHAR(1);

BEGIN
    IF     (TG_OP = 'DELETE') THEN audit_reason = 'D';
    ELSEIF (TG_OP = 'UPDATE') THEN audit_reason = 'U';
    ELSEIF (TG_OP = 'INSERT') THEN audit_reason = 'I';
    END IF;

    IF (TG_OP = 'DELETE') THEN
        INSERT INTO audit_users (
             audit_reason
            ,user_id
            ,username
            ,temporary_password_ind
            ,login_attempts
            ,account_locked_dt_tm
            ,account_locked_type
            ,account_expired_dt_tm
            ,password_changed_dt_tm
            ,password_expired_dt_tm
            ,admin_ind
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
            ,OLD.user_id
            ,OLD.username
            ,OLD.temporary_password_ind
            ,OLD.login_attempts
            ,OLD.account_locked_dt_tm
            ,OLD.account_locked_type
            ,OLD.account_expired_dt_tm
            ,OLD.password_changed_dt_tm
            ,OLD.password_expired_dt_tm
            ,OLD.admin_ind
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
        INSERT INTO audit_users (
             audit_reason
            ,user_id
            ,username
            ,temporary_password_ind
            ,login_attempts
            ,account_locked_dt_tm
            ,account_locked_type
            ,account_expired_dt_tm
            ,password_changed_dt_tm
            ,password_expired_dt_tm
            ,admin_ind
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
            ,NEW.user_id
            ,NEW.username
            ,NEW.temporary_password_ind
            ,NEW.login_attempts
            ,NEW.account_locked_dt_tm
            ,NEW.account_locked_type
            ,NEW.account_expired_dt_tm
            ,NEW.password_changed_dt_tm
            ,NEW.password_expired_dt_tm
            ,NEW.admin_ind
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
$users_trig$ LANGUAGE plpgsql;

CREATE TRIGGER users_trig AFTER INSERT OR UPDATE OR DELETE ON users
    FOR EACH ROW EXECUTE PROCEDURE users_trig_function();
