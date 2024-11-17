CREATE TABLE IF NOT EXISTS user (
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
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_gender FOREIGN KEY (gender_id) REFERENCES gender(id),
    CONSTRAINT fk_experience FOREIGN KEY (experience_id) REFERENCES experience(id)
);