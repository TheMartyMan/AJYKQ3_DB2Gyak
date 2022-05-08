CREATE OR REPLACE PROCEDURE vevoListaz AS

    CURSOR cur IS SELECT * FROM Vevo;
    cv cur%rowtype;

BEGIN

    OPEN cur;
    LOOP
        FETCH cur into cv;
        EXIT WHEN cur%notfound;
        
    dbms_output.enable; 
    dbms_output.put_line('VevőID: ' || cv.vevoid || ' Név: ' || cv.nev || ' Telefonszám: ' || cv.tel || ' Lakcím: ' || cv.lakcim || ' Születési idő: ' || cv.szulido);
    
    END LOOP;
    CLOSE cur;
    
END;
