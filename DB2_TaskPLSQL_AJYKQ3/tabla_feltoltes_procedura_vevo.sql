create or replace PROCEDURE feltoltVevo(vevoid number, nev varchar, tel varchar, lakcim varchar, szulido varchar) IS

PERROR Exception;

BEGIN

IF vevoid<=0 OR nev IS NULL OR szulido IS NULL THEN

RAISE PERROR;

ELSE

	INSERT INTO Vevo VALUES (vevoid, nev, tel, lakcim, szulido);
COMMIT;

END IF;


EXCEPTION

	WHEN PERROR THEN

 dbms_output.put_line('A vevoid, nev és a szulido nem lehetnek 0 illetve üresek!');

END;
