DECLARE

    r autok.kor%type := 10;
	
BEGIN


    DELETE FROM autok WHERE kor>r;
	
	
END;
