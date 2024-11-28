CREATE TABLE IF NOT EXISTS addresses(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    zipcode INT NOT NULL,
    country VARCHAR(50),
    state VARCHAR(50),
    city VARCHAR(255),
    street VARCHAR(255),
    neighbourhood VARCHAR(255),
    complement VARCHAR(255),
    number VARCHAR(255)
)