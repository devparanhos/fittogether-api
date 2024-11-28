CREATE TABLE IF NOT EXISTS preference_gyms(
    preference_id BIGINT NOT NULL,
    gym_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_preference_gyms_preference FOREIGN KEY (preference_id) REFERENCES preferences(id),
    CONSTRAINT fk_preference_gyms_gym FOREIGN KEY (gym_id) REFERENCES gyms(id)
)