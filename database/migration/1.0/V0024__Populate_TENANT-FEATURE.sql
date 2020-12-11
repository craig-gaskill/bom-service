INSERT INTO tenant_feature (tenant_id, feature_id, created_id, updated_id)
SELECT 1, f.feature_id, 1, 1
  FROM feature f
 WHERE f.meaning = 'CORE';

INSERT INTO tenant_feature (tenant_id, feature_id, created_id, updated_id)
SELECT 2, f.feature_id, 1, 1
FROM feature f
WHERE f.meaning = 'CORE';
