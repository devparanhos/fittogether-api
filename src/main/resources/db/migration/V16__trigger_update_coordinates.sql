DELIMITER $$

CREATE TRIGGER update_coordinates BEFORE UPDATE ON addresses
FOR EACH ROW
BEGIN
    -- Verifica se houve alteração nos valores de lat ou lng
    IF OLD.lat != NEW.lat OR OLD.lng != NEW.lng THEN
        -- Atualiza a coluna coordinates com os novos valores de lat e lng
        SET NEW.coordinates = ST_GeomFromText(CONCAT('POINT(', NEW.lng, ' ', NEW.lat, ')'));
    END IF;
END $$

DELIMITER ;