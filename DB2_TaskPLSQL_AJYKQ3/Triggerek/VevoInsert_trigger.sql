CREATE OR REPLACE TRIGGER NaploInsert AFTER INSERT ON Vevo FOR EACH ROW

BEGIN

    INSERT INTO Naplo values ('Beszúrás',:new.vevoid, sysdate);
    
END;
