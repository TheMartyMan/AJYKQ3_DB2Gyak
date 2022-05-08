CREATE OR REPLACE PROCEDURE VDbKiir is
db number:=0;


BEGIN

select COUNT (*) into db from vasarlas;
dbms_output.put_line(db);

END;
