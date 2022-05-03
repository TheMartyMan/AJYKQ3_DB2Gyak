DECLARE

    db number:=0;

BEGIN

    SELECT COUNT(rsz) INTO db FROM autok;
    dbms_output.put_line(db);

END;