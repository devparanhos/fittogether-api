CREATE TABLE IF NOT EXISTS gym_exercises(
    exercise_id BIGINT NOT NULL,
    gym_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_gym_exercises_exercise FOREIGN KEY (exercise_id) REFERENCES exercise(id),
    CONSTRAINT fk_gym_exercises_gym FOREIGN KEY (gym_id) REFERENCES gyms(id)
)