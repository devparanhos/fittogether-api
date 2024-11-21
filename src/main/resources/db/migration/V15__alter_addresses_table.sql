ALTER TABLE addresses
ADD COLUMN coordinates POINT NULL AFTER lng;

UPDATE addresses
SET coordinates = ST_GeomFromText(CONCAT('POINT(', lng, ' ', lat, ')'));

ALTER TABLE addresses
MODIFY COLUMN coordinates POINT NOT NULL;

ALTER TABLE addresses
ADD SPATIAL INDEX (coordinates);