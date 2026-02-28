CREATE TABLE IF NOT EXISTS stores(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_id BIGINT NOT NULL,
    address_id BIGINT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_stores_company FOREIGN KEY (company_id) REFERENCES companies(id),
    CONSTRAINT fk_stores_address FOREIGN KEY (address_id) REFERENCES addresses(id)
);
