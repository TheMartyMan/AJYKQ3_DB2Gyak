CREATE OR REPLACE PROCEDURE nevModosit(bevevoid number, ujnev varchar) IS

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

END;
