CREATE OR REPLACE FUNCTION fVevoListaz RETURN CHAR IS listaz CHAR (50);

CURSOR cur IS SELECT * FROM Vevo;

cv cur%rowtype;

BEGIN

    OPEN cur;
    LOOP
    
        FETCH cur INTO cv;
        EXIT WHEN cur%notfound;
        
        dbms_output.put_line('VevőID: ' || cv.vevoid || ' Név: ' || cv.nev || ' Telefonszám: ' || cv.tel || ' Lakcím: ' || cv.lakcim || ' Születési idő: ' || cv.szulido);
        
        END LOOP;
        CLOSE cur;
        
        RETURN listaz;
END;
