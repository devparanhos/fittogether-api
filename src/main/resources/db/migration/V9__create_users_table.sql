CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    cellphone VARCHAR(20) NOT NULL,
    birth_date DATE NOT NULL,
    password VARCHAR(255) NOT NULL,
    registration_status VARCHAR(50) NOT NULL,
    registration_step VARCHAR(50) NOT NULL,
    access_token TEXT,
    gender_id BIGINT,
    experience_id BIGINT,
    preference_id BIGINT,
    CONSTRAINT fk_users_gender FOREIGN KEY (gender_id) REFERENCES genders(id),
    CONSTRAINT fk_users_experience FOREIGN KEY (experience_id) REFERENCES experiences(id),
    CONSTRAINT fk_users_preference FOREIGN KEY (preference_id) REFERENCES preferences(id)
);