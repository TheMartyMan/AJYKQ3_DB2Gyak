CREATE OR REPLACE FUNCTION vevoSzuldatumra(also IN DATE, felso IN DATE) RETURN NUMBER IS

db NUMBER := 0;

CURSOR cur IS SELECT * FROM Vevo;

BEGIN

    OPEN cur; 

    FOR cur IN (SELECT * FROM Vevo WHERE szulido BETWEEN also AND felso) LOOP

        db := db + 1;

    END LOOP;
    CLOSE cur;

    RETURN db;

END;
