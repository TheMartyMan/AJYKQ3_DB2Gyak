DECLARE

--Stringek
t char(50);
v char(50);
k char(50);

beosztas varchar(50);

--Számok

x number;
y number;
z number;
n number;

mini number;
maxi number;
ertek number;


a number;
b number;




--Kör adatok

terulet float;
pi float;
r number;



--Háromszög adatok

ha number;
hb number;
hc number;

hterulet float;
s float;


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


r:=12;
pi:=3.1415;
terulet:=r*r*pi;


dbms_output.put_line('A(z) '||r||' sugarú körnek a területe: '||terulet);


--Vezérlési szerkezetek

a:=10;
b:=81;

if a>b then
dbms_output.put_line('A nagyobb szám: '||a);
else
dbms_output.put_line('A nagyobb szám: '||b);
end if;





mini:=10;
maxi:=100;
ertek:=78;

if ertek < mini OR ertek > maxi then
dbms_output.put_line(ertek||' nem esik bele az intervallumba');
else
dbms_output.put_line(ertek||' beleesik az intervallumba');
end if;




beosztas:='root';

CASE beosztas
WHEN 'root' THEN
    dbms_output.put_line('Rendszergazda');

WHEN 'user' THEN 
    dbms_output.put_line('Felhasznalo');
END CASE;

ha:=3;
hb:=4;
hc:=5;




if ha + hb < hc OR ha + hc < hb OR hb + hc < ha then
    dbms_output.put_line('Ezekből az oldalakból nem lehet háromszöget csinálni');   
else
    dbms_output.put_line('Ezekből az oldalakból lehet háromszöget csinálni');
end if;

s:=(ha+hb+hc)/2;

hterulet:=sqrt(s*(s-ha)*(s-hb)*(s-hc));

dbms_output.put_line('A(z) '||ha||', '||hb||', '||hc||' oldalú háromszög területe: '||hterulet);


END;
