CREATE OR REPLACE PROCEDURE filmTorol(befilmid varchar) IS

BEGIN

    DELETE FROM Film WHERE filmid = befilmid;
    COMMIT;

END;
