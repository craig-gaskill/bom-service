-- Create Administrator Role for Sandbox
INSERT INTO role (tenant_id, name, full_access_ind, created_id, updated_id)
VALUES (1, 'Administrator', true, 1, 1);

-- Create Administrator Role for Demo
INSERT INTO role (tenant_id, name, full_access_ind, created_id, updated_id)
VALUES (2, 'Administrator', true, 1, 1);
