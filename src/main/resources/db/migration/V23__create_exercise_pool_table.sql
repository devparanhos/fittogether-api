CREATE TABLE exercise_pool (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    exercise_id BIGINT NOT NULL,
    start_time TIMESTAMP NULL,
    end_time TIMESTAMP NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_exercises_pool_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_exercises_pool_exercise FOREIGN KEY (exercise_id) REFERENCES exercise(id)
);