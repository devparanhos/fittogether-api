CREATE TABLE IF NOT EXISTS genders_preferences(
    preference_id BIGINT NOT NULL,
    gender_id BIGINT NOT NULL,
    CONSTRAINT fk_genders_preferences_preferences FOREIGN KEY (preference_id) REFERENCES preferences(id),
    CONSTRAINT fk_genders_preferences_genders FOREIGN KEY (gender_id) REFERENCES genders(id)
)