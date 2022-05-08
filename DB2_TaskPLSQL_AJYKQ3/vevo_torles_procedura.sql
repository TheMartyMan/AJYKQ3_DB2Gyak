CREATE OR REPLACE PROCEDURE vevoTorol(bevevoid varchar) IS

BEGIN

    DELETE FROM Vevo WHERE vevoid = bevevoid;
    COMMIT;

END;
