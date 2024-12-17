CREATE TABLE IF NOT EXISTS exercise_questions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    exercise_id BIGINT NOT NULL,
    type ENUM('MULTIPLE', 'SINGLE') NOT NULL DEFAULT 'SINGLE',
    description TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_exercise FOREIGN KEY (exercise_id) REFERENCES exercise (id)
);