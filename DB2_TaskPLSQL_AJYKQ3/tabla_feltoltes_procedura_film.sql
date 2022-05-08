CREATE OR REPLACE PROCEDURE feltoltFilm(filmid number, filmcim varchar, rendezo varchar, mdatum date) IS

PERROR Exception;


BEGIN

IF filmid<=0 OR filmcim IS NULL OR rendezo IS NULL OR mdatum IS NULL THEN

RAISE PERROR;

ELSE

	INSERT INTO Film VALUES (filmid, filmcim, rendezo, mdatum);
COMMIT;	

END IF;



EXCEPTION

	WHEN PERROR THEN
	dbms_output.put_line('A filmid, filmcim, rendezo és az mdatum nem lehetnek 0 illetve üresek!');
