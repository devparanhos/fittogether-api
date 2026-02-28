-- Create preference_stores (replaces preference_gyms; store_id = former gym_id after migration)
CREATE TABLE IF NOT EXISTS preference_stores(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    preference_id BIGINT NOT NULL,
    store_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_preference_stores_preference FOREIGN KEY (preference_id) REFERENCES preferences(id),
    CONSTRAINT fk_preference_stores_store FOREIGN KEY (store_id) REFERENCES stores(id)
);

-- Migrate preference_gyms to preference_stores (gym_id = store_id after V35)
INSERT INTO preference_stores (id, preference_id, store_id, created_at, updated_at, deleted_at)
SELECT id, preference_id, gym_id, created_at, updated_at, deleted_at
FROM preference_gyms;

-- Drop old gym-related tables
DROP TABLE IF EXISTS preference_gyms;
DROP TABLE IF EXISTS gym_exercises;
DROP TABLE IF EXISTS gyms;
