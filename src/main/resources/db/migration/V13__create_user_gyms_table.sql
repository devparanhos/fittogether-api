CREATE TABLE IF NOT EXISTS user_gyms(
    user_id BIGINT NOT NULL,
    gym_id BIGINT NOT NULL,
    CONSTRAINT fk_user_gyms_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_gyms_gym FOREIGN KEY (gym_id) REFERENCES gyms(id)
)