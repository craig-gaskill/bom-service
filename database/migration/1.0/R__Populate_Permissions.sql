-- Access / View Settings
INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Access Settings', 'settings.view', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

-- Dictionary Permissions
INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Access Dictionaries', 'settings.dictionary.view', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Add Dictionaries', 'settings.dictionary.add', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Edit Dictionaries', 'settings.dictionary.edit', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Delete Dictionaries', 'settings.dictionary.delete', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

-- Role Permissions
INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Access Roles', 'settings.role.view', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Add Roles', 'settings.role.add', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Edit Roles', 'settings.role.edit', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Delete Roles', 'settings.role.delete', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

-- Staff Permissions
INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Access Staff', 'settings.staff.view', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Add Staff', 'settings.staff.add', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Edit Staff', 'settings.staff.edit', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO permission (feature_id, display, code, created_id, updated_id)
SELECT f.feature_id, 'Delete Staff', 'settings.staff.delete', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

-- User Permissions
INSERT INTO permission (feature_id, display, code, description, created_id, updated_id)
SELECT f.feature_id, 'Lock User', 'settings.user.lock', 'Will lock the user''s account so they can no longer log in.', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO permission (feature_id, display, code, description, created_id, updated_id)
SELECT f.feature_id, 'Un-Lock User', 'settings.user.unlock', 'Will unlock the user''''s account so they can log in.', 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';
