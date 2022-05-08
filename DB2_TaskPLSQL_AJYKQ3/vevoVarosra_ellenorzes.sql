DECLARE

    x number;
    varos varchar(30) := 'Budapest';

BEGIN

    x := vevoVarosra(varos);
    dbms_output.put_line(x || ' darab vevő él ' || varos || ' városban.' );
    
END;
