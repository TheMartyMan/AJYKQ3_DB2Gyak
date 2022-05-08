CREATE OR REPLACE PROCEDURE telModosit(bevevoid number, ujtel varchar) IS

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

END;
