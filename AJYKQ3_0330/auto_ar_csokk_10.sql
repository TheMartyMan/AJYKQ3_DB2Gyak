CREATE VIEW vkor AS SELECT rsz, kor FROM autok;

DECLARE

    x number(2) := 7;
	
BEGIN

    UPDATE autok SET ar=ar*0.9 WHERE rsz IN (SELECT rsz FROM vkor WHERE kor > x);
	
END;