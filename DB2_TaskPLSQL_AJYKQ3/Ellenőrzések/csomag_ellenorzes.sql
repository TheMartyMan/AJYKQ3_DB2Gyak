DECLARE

also date := TO_DATE('1990.01.01', 'YYYY.MM.DD');
felso date := TO_DATE('2010.01.01', 'YYYY.MM.DD');

BEGIN

    dbms_output.put_line(Vevocsomag.vevoSzuldatumra(also, felso)|| ' db vevő él ' || also || ' és ' || felso || ' között.' );
    
END;
