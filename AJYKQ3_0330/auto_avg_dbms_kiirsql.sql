DECLARE

    atlag number;

BEGIN

    SELECT avg(ar) INTO atlag FROM autok;
    DBMS_OUTPUT.put_line(atlag);

END;