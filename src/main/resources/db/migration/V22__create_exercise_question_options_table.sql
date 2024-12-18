CREATE TABLE IF NOT EXISTS exercise_question_options (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    exercise_question_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_exercise_question FOREIGN KEY (exercise_question_id) REFERENCES exercise_questions (id)
);