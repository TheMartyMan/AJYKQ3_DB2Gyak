CREATE OR REPLACE PROCEDURE filmListaz AS

    CURSOR cur IS SELECT * FROM Film;
    cv cur%rowtype;

BEGIN

    OPEN cur;
    LOOP

        FETCH cur into cv;
        EXIT WHEN cur%notfound;
        
    dbms_output.put_line('FilmID: ' || cv.filmid || ' Filmcím: ' || cv.filmcim || ' Rendező: ' || cv.rendezo || ' Megjelenési dátum: ' || cv.mdatum);
    
    END LOOP;
    CLOSE cur;
    
END;
