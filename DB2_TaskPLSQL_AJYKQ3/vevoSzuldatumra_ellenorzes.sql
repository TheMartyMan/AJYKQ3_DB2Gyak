DECLARE

also DATE := TO_DATE('1990.01.01', 'YYYY.MM.DD');
felso DATE := TO_DATE('2010.01.01', 'YYYY.MM.DD');

x NUMBER;

BEGIN

x := vevoSzuldatumra(also, felso);
dbms_output.enable;
dbms_output.put_line('A(z) ' || also || ' és ' || felso || ' év közötti születésűek darabszáma: ' || x);

END;
