CREATE TABLE IF NOT EXISTS user_exercises(
    user_id BIGINT NOT NULL,
    exercise_id BIGINT NOT NULL,
    CONSTRAINT fk_user_exercises_user
       FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_user_exercises_exercise
       FOREIGN KEY (exercise_id) REFERENCES exercise(id)
);