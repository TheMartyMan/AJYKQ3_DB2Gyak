CREATE OR REPLACE TRIGGER NaploInsert2 AFTER INSERT ON Film FOR EACH ROW

BEGIN

    INSERT INTO Naplo values ('Beszúrás',:new.filmid, sysdate);
    
END;
