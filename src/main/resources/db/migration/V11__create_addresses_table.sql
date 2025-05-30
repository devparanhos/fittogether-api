CREATE TABLE IF NOT EXISTS addresses(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    neighbourhood VARCHAR(100) NOT NULL,
    number VARCHAR(25) NULL,
    complement VARCHAR(100) NULL,
    state VARCHAR(2) NOT NULL,
    country VARCHAR(100) NOT NULL DEFAULT "Brazil",
    zipcode VARCHAR(25) NOT NULL,
    city VARCHAR(25) NOT NULL,
    lat VARCHAR(25) NULL,
    lng VARCHAR(25) NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL
)