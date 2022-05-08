CREATE OR REPLACE PROCEDURE rendezoModosit(befilmid number, ujrendezo varchar) IS

PERROR Exception;

BEGIN

IF (ujrendezo IS NULL) THEN
    RAISE PERROR;
    
    
ELSE

UPDATE Film SET rendezo = ujrendezo WHERE filmid = befilmid;
COMMIT;

END IF;

Exception
    WHEN PERROR THEN
    dbms_output.enable; 
    dbms_output.put_line('Az új rendező neve nem lehet üres!');

END;
