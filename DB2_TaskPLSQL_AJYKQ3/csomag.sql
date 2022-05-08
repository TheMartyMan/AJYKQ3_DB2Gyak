CREATE OR REPLACE PACKAGE Vevocsomag IS


    FUNCTION vevoSzuldatumra(also IN DATE, felso IN DATE) RETURN NUMBER;
    PROCEDURE telModosit(bevevoid number, ujtel varchar);
    PROCEDURE nevModosit(bevevoid number, ujnev varchar);

END Vevocsomag;



/
CREATE OR REPLACE PACKAGE BODY Vevocsomag IS
    
    FUNCTION vevoSzuldatumra(also IN DATE, felso IN DATE) RETURN NUMBER IS

        db NUMBER := 0;

        CURSOR cur IS SELECT * FROM Vevo;

    BEGIN

        OPEN cur; 

        FOR cur IN (SELECT * FROM Vevo WHERE szulido BETWEEN also AND felso) LOOP

            db := db + 1;

        END LOOP;
        
        CLOSE cur;

        RETURN db;
    END vevoSzuldatumra;




PROCEDURE telModosit(bevevoid number, ujtel varchar) IS

PERROR Exception;

BEGIN

IF (ujtel IS NULL) THEN
    RAISE PERROR;
    
    
ELSE

UPDATE Vevo SET tel = ujtel WHERE vevoid = bevevoid;
COMMIT;

END IF;

Exception
    WHEN PERROR THEN
    dbms_output.enable; 
    dbms_output.put_line('Az új telefonszám nem lehet üres!');
    
    END telModosit;
    
    
    
PROCEDURE nevModosit(bevevoid number, ujnev varchar) IS

PERROR Exception;

BEGIN

IF (ujnev IS NULL) THEN
    RAISE PERROR;
    
    
ELSE

UPDATE Vevo SET nev = ujnev WHERE vevoid = bevevoid;
COMMIT;

END IF;

Exception
    WHEN PERROR THEN
    
    dbms_output.put_line('Az új név nem lehet üres!');

END nevModosit;

END Vevocsomag;
/
