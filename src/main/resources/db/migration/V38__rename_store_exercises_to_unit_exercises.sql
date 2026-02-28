-- Rename store_id to unit_id in store_exercises, then rename table
ALTER TABLE store_exercises
    CHANGE COLUMN store_id unit_id BIGINT NOT NULL;

RENAME TABLE store_exercises TO unit_exercises;

-- Update FK constraint name (optional; MySQL keeps reference to units(id) after RENAME)
ALTER TABLE unit_exercises
    DROP FOREIGN KEY fk_store_exercises_store,
    ADD CONSTRAINT fk_unit_exercises_unit FOREIGN KEY (unit_id) REFERENCES units(id);
