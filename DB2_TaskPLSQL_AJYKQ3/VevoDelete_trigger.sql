CREATE OR REPLACE TRIGGER NaploDelete AFTER DELETE ON Vevo FOR EACH ROW

BEGIN

    INSERT INTO Naplo values ('Törlés',:old.vevoid, sysdate);
    
END;
