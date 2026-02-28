-- Rename store_id to unit_id in preference_stores, then rename table
ALTER TABLE preference_stores
    CHANGE COLUMN store_id unit_id BIGINT NOT NULL;

RENAME TABLE preference_stores TO preference_units;

ALTER TABLE preference_units
    DROP FOREIGN KEY fk_preference_stores_store,
    ADD CONSTRAINT fk_preference_units_unit FOREIGN KEY (unit_id) REFERENCES units(id);
