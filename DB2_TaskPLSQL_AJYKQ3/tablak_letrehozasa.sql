CREATE TABLE Vevo (
	vevoid NUMBER NOT NULL,
	nev VARCHAR(255),
	tel VARCHAR(20),
	lakcim VARCHAR(255),
	szulido DATE,
	PRIMARY KEY(vevoid)
);

CREATE TABLE Film (
	filmid NUMBER NOT NULL,
	filmcim VARCHAR(255),
	rendezo VARCHAR(255),
	mdatum DATE,
	PRIMARY KEY(filmid)
);


CREATE TABLE Naplo(esemeny VARCHAR(30), adat VARCHAR (30), datum TIMESTAMP);
