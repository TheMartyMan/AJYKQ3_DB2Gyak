DECLARE

--Karakterek
t char(50);
v char(50);
k char(50);

--Számok

x number(5);
y number(5);
z number(7);
n number(7);


--Kör adatok

terulet number(5);
pi number (5,4);




BEGIN

t:='Garamszegi Márton';
v:='Garamszegi';
k:='Márton';


x:=2;
y:=3;


z:=x+y;
n:=x*y;

dbms_output.put_line(t);


dbms_output.put_line(x||'+'||y||'='||z);
dbms_output.put_line(x||'*'||y||'='||n);

dbms_output.put_line(UPPER(t));
dbms_output.put_line(LOWER(t));

dbms_output.put_line(v||k);

dbms_output.put_line('Jelenlegi rendszeridő: '||SYSDATE);
dbms_output.put_line('Jelenlegi rendszeridő formázva: '||TO_CHAR(SYSDATE, 'YYYY/MM/DD'));

terulet := 

END;
