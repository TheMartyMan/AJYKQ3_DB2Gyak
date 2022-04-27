CREATE OR REPLACE PROCEDURE VDbKiir is
db number:=0;
begin
select COUNT (*) into db from vasarlas;
dbms_output.put_line(db);
end;
