-- Create notifications table
CREATE TABLE IF NOT EXISTS suggestions
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    type        VARCHAR(255) NOT NULL,
    image       TEXT         NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL
);

-- Insert initial notification record
INSERT INTO suggestions (title, description, type, image)
VALUES ('Complete seu cadastro',
        'lorem ipsum',
        'FINISH_REGISTRATION',
        'https://image.png');