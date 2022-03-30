DECLARE

-----7. gyak-----
-----Feladatok-----
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


-- Fibonacci
first number := 0;
second number := 1;
temp number;
i number;


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


---1. feladat
dbms_output.put_line(t);

---2.feladat
dbms_output.put_line(x||'+'||y||'='||z);

---3.feladat
dbms_output.put_line(x||'*'||y||'='||n);

---4.feladat
dbms_output.put_line(UPPER(t));
dbms_output.put_line(LOWER(t));

---5.feladat
dbms_output.put_line(v||k);

---6.feladat
dbms_output.put_line('Jelenlegi rendszeridő: '||SYSDATE);

---7.feladat
dbms_output.put_line('Jelenlegi rendszeridő formázva: '||TO_CHAR(SYSDATE, 'YYYY/MM/DD'));



r:=12;
pi:=3.1415;
terulet:=r*r*pi;

---8.feladat
dbms_output.put_line('A(z) '||r||' sugarú körnek a területe: '||terulet);


---Vezérlési szerkezetek---

a:=10;
b:=81;

---2.feladat
if a>b then
dbms_output.put_line('A nagyobb szám: '||a);
else
dbms_output.put_line('A nagyobb szám: '||b);
end if;



---3.feladat

mini:=10;
maxi:=100;
ertek:=78;

if ertek < mini OR ertek > maxi then
dbms_output.put_line(ertek||' nem esik bele az intervallumba');
else
dbms_output.put_line(ertek||' beleesik az intervallumba');
end if;





---4. és 5.feladat
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



---6.feladat
if ha + hb < hc OR ha + hc < hb OR hb + hc < ha then
    dbms_output.put_line('Ezekből az oldalakból nem lehet háromszöget csinálni');   
else
    dbms_output.put_line('Ezekből az oldalakból lehet háromszöget csinálni');
end if;



---7.feladat
s:=(ha+hb+hc)/2;


hterulet:=sqrt(s*(s-ha)*(s-hb)*(s-hc));

dbms_output.put_line('A(z) '||ha||', '||hb||', '||hc||' oldalú háromszög területe: '||hterulet);


---8. és 9. feladat

n:=10;
FOR floop IN 1..n
  LOOP
    DBMS_OUTPUT.PUT_LINE(floop);
  END LOOP;

---10. feladat

  
    dbms_output.put_line('Series:');
  

    dbms_output.put_line(first);
    dbms_output.put_line(second);
  
    for i in 2..n
    loop
        temp:=first+second;
  
first := second;
second := temp;
  

    dbms_output.put_line(temp);
end loop;

---11.feladat           

i := 2;
temp := 1;
  
  for i in 2..n/2
    loop
        if mod(n, i) = 0
        then
            temp := 0;
            exit;
        end if;
    end loop;
   
    if temp = 1
    then
        dbms_output.put_line(n||' prímszám.');
    else
        dbms_output.put_line(n||' nem prímszám.');
    end if;


    
END;
