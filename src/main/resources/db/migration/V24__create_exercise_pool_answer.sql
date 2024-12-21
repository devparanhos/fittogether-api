CREATE TABLE exercise_pool_answer (
    exercise_question_option_id BIGINT NOT NULL,
    exercise_pool_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    PRIMARY KEY (exercise_question_option_id, exercise_pool_id),
    CONSTRAINT fk_exercise_pool_answer_question_option FOREIGN KEY (exercise_question_option_id) REFERENCES exercise_question_options(id),
    CONSTRAINT fk_exercise_pool_answer_exercise_pool FOREIGN KEY (exercise_pool_id) REFERENCES exercise_pool(id)
);