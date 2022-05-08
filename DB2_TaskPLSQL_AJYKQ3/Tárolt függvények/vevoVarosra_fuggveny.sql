CREATE OR REPLACE FUNCTION vevoVarosra(belakcim varchar) RETURN NUMBER IS

db number := 0;

BEGIN
    SELECT COUNT(*) INTO db FROM Vevo WHERE lakcim LIKE belakcim || '%';
    RETURN db;
END;
