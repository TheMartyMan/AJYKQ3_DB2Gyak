CREATE OR REPLACE FUNCTION fFilmListaz RETURN CHAR IS listaz CHAR (50);

CURSOR cur IS SELECT * FROM Film;

cv cur%rowtype;

BEGIN

    OPEN cur;
    LOOP
    
        FETCH cur INTO cv;
        EXIT WHEN cur%notfound;
        
        dbms_output.put_line('FilmID: ' || cv.filmid || ' Filmcím: ' || cv.filmcim || ' Rendező: ' || cv.rendezo || ' Megjelenési dátum: ' || cv.mdatum);
        
        END LOOP;
        CLOSE cur;
        
        RETURN listaz;

END;
