CREATE TABLE IF NOT EXISTS user_goals(
    user_id BIGINT NOT NULL,
    goal_id BIGINT NOT NULL,
    CONSTRAINT fk_user_goals_users FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_goals_goals FOREIGN KEY (goal_id) REFERENCES goals(id)
);