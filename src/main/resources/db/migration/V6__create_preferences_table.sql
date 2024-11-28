CREATE TABLE IF NOT EXISTS preferences(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_age INT NOT NULL,
    end_age INT NOT NULL,
    radius_distance INT NOT NULL
)