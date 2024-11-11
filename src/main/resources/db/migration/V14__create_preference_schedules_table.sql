CREATE TABLE IF NOT EXISTS preference_schedules(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    preference_id BIGINT NOT NULL,
    day_week int NOT NULL DEFAULT 0,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_preference_preference FOREIGN KEY (preference_id) REFERENCES preferences(id)
)