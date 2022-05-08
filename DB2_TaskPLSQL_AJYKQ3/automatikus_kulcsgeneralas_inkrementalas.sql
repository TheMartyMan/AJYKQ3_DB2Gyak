CREATE SEQUENCE vevo_sequence;
CREATE SEQUENCE film_sequence;

CREATE OR REPLACE TRIGGER vevo_on_insert

    BEFORE INSERT ON Vevo
    FOR EACH ROW

BEGIN

    SELECT vevo_sequence.NEXTVAL
    INTO :new.vevoid
    FROM dual;

END;


CREATE OR REPLACE TRIGGER film_on_insert

    BEFORE INSERT ON Film
    FOR EACH ROW

BEGIN

    SELECT film_sequence.NEXTVAL
    INTO :new.filmid
    FROM dual;

END;
