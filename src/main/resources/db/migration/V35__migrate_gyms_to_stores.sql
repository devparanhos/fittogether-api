-- Create default company for existing gyms
INSERT INTO companies (name, created_at, updated_at, deleted_at)
SELECT 'Fit Together', CURRENT_TIMESTAMP, NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM companies LIMIT 1);

SET @default_company_id = (SELECT id FROM companies LIMIT 1);

-- Migrate gyms to stores (one store per gym, under default company)
INSERT INTO stores (id, company_id, address_id, name, created_at, updated_at, deleted_at)
SELECT id, @default_company_id, address_id, name, created_at, updated_at, deleted_at
FROM gyms;

-- Migrate gym_exercises to store_exercises (store_id = former gym_id)
INSERT INTO store_exercises (store_id, exercise_id, created_at, updated_at, deleted_at)
SELECT gym_id, exercise_id, created_at, updated_at, deleted_at
FROM gym_exercises;
