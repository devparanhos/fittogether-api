-- Add lat, lng, type to stores (with defaults for existing rows)
ALTER TABLE stores
    ADD COLUMN lat DOUBLE NOT NULL DEFAULT 0,
    ADD COLUMN lng DOUBLE NOT NULL DEFAULT 0,
    ADD COLUMN type VARCHAR(50) NOT NULL DEFAULT 'GYM';

-- Backfill lat/lng from addresses
UPDATE stores s
LEFT JOIN addresses a ON s.address_id = a.id
SET s.lat = COALESCE(CAST(a.lat AS DECIMAL(10,7)), 0),
    s.lng = COALESCE(CAST(a.lng AS DECIMAL(10,7)), 0);

-- Rename table stores to units (FKs in store_exercises and preference_stores will follow)
RENAME TABLE stores TO units;
