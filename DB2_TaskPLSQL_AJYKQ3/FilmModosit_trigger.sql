CREATE OR REPLACE TRIGGER NaploUpdate2 AFTER UPDATE ON Film FOR EACH ROW

BEGIN

    INSERT INTO Naplo values ('Módosítás',:new.filmid, sysdate);
    
END;
