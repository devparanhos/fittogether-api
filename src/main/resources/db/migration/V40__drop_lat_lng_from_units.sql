-- Unit coordinates vêm do address; removendo lat/lng da tabela units
ALTER TABLE units
    DROP COLUMN lat,
    DROP COLUMN lng;
