CREATE OR REPLACE TRIGGER NaploUpdate AFTER UPDATE ON Vevo FOR EACH ROW

BEGIN

    INSERT INTO Naplo values ('Módosítás',:new.vevoid, sysdate);
    
END;
