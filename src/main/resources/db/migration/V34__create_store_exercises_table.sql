CREATE TABLE IF NOT EXISTS store_exercises(
    store_id BIGINT NOT NULL,
    exercise_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_store_exercises_store FOREIGN KEY (store_id) REFERENCES stores(id),
    CONSTRAINT fk_store_exercises_exercise FOREIGN KEY (exercise_id) REFERENCES exercise(id)
);
