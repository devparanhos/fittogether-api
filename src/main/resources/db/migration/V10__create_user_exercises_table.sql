CREATE TABLE IF NOT EXISTS user_exercises(
    user_id BIGINT NOT NULL,
    exercise_id BIGINT NOT NULL,
    CONSTRAINT fk_user_exercises_users FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_exercises_exercises FOREIGN KEY (exercise_id) REFERENCES exercises(id)
);