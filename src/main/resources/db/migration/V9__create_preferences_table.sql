CREATE TABLE IF NOT EXISTS preferences(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    start_age INT NOT NULL,
    end_age INT NOT NULL,
    radius_distance INT NOT NULL,
    CONSTRAINT fk_preferences_user FOREIGN KEY (user_id) REFERENCES user(id)
)