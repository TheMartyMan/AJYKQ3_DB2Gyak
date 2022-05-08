CREATE OR REPLACE FUNCTION filmRendezore(berendezo varchar) RETURN NUMBER IS

db number := 0;

BEGIN
    SELECT COUNT(*) INTO db FROM Film WHERE rendezo LIKE berendezo || '%';
    RETURN db;
END;

Ellenőrzés:

DECLARE

    x number;
    rendezo varchar(30) := 'Chris';

BEGIN

    x := filmRendezore(rendezo);
    dbms_output.put_line(x || ' darab filmet rendezett ' || rendezo || ' nevű rendező.' );
    
END;
