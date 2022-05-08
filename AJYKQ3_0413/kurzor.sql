create or replace procedure tlp as

 cursor cur is select * from kategoria;
 cv cur%rowtype;
 
begin
 open cur;
 loop
 fetch cur into cv;
 exit when cur%notfound;
 dbms_output.put_line('Név: ' || cv.nev);
 dbms_output.put_line('Feldolgozva: ' || cur%rowcount);
 end loop;
 close cur;
end;
