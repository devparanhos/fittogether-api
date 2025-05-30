CREATE TABLE IF NOT EXISTS user_goals(
    user_id BIGINT NOT NULL,
    goal_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_goal FOREIGN KEY (goal_id) REFERENCES goal(id)
);