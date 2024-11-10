CREATE TABLE IF NOT EXISTS preference_genders(
    preference_id BIGINT NOT NULL,
    gender_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_preference_genders_preference FOREIGN KEY (preference_id) REFERENCES preferences(id),
    CONSTRAINT fk_preference_genders_gender FOREIGN KEY (gender_id) REFERENCES gender(id)
)