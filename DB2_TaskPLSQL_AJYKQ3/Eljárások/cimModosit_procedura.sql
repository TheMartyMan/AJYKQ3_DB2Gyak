CREATE OR REPLACE PROCEDURE cimModosit(befilmid number, ujcim varchar) IS

PERROR Exception;

BEGIN

IF (ujcim IS NULL) THEN
    RAISE PERROR;
    
    
ELSE

UPDATE Film SET filmcim = ujcim WHERE filmid = befilmid;
COMMIT;

END IF;

Exception
    WHEN PERROR THEN
    dbms_output.enable; 
    dbms_output.put_line('Az új filmcím nem lehet üres!');

END;
