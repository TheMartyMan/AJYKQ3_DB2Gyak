CREATE OR REPLACE TRIGGER NaploDelete2 AFTER DELETE ON Film FOR EACH ROW

BEGIN

    INSERT INTO Naplo values ('Törlés',:old.filmid, sysdate);
    
END;
