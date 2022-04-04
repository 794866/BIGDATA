CREATE TABLE Fecha
(
	ID			NUMERIC,
	dia			NUMERIC		NOT NULL,
	mes			NUMERIC		NOT NULL,
	year			NUMERIC		NOT NULL,
	esDiaDeEstreno			BOOLEAN		NOT NULL,
	fechaTexto			VARCHAR(255)		NOT NULL,
	PRIMARY KEY(ID));


CREATE TABLE Valoracion
(
	ID			NUMERIC,
	cantidadValoraciones			NUMERIC		NOT NULL,
	valoracionMasAlta			NUMERIC		NOT NULL,
	valoracionMasBaja			NUMERIC		NOT NULL,
	valoracionMedia			NUMERIC		NOT NULL,
	PRIMARY KEY(ID));


CREATE TABLE genero
(
	ID			NUMERIC,
	nombre			VARCHAR(255)		NOT NULL,
	PRIMARY KEY(ID));


CREATE TABLE EquipoProduccion
(
	ID			VARCHAR(255)		NOT NULL,
	nombreDepartamento			VARCHAR(255)		NOT NULL,
	cargo			VARCHAR(255)		NOT NULL,
	nombreTrabajador			VARCHAR(255)		NOT NULL,
	genero			BOOLEAN		NOT NULL,
	PRIMARY KEY(ID));


CREATE TABLE PELICULA
(
	ID			NUMERIC,
	equipoProduccion			VARCHAR(255),
	genero			NUMERIC,
	valoracion			NUMERIC,
	titulo			VARCHAR(255)		NOT NULL,
	yearPublication			NUMERIC		NOT NULL,
	duracionMinutos			NUMERIC		NOT NULL,
	paisEstreno			VARCHAR(255)		NOT NULL,
	presupuesto			NUMERIC		NOT NULL,
	productionCompany			VARCHAR(255)		NOT NULL,
	ingresos			NUMERIC		NOT NULL,
	PRIMARY KEY(ID),
	FOREIGN KEY(equipoProduccion) REFERENCES EquipoProduccion (ID),
	FOREIGN KEY(genero) REFERENCES genero (ID),
	FOREIGN KEY(valoracion) REFERENCES Valoracion (ID));

CREATE TABLE Actor
(
	ID			NUMERIC,
	pelicula			NUMERIC,
	nombre			VARCHAR(255)		NOT NULL,
	fechaNacimiento			VARCHAR(255)		NOT NULL,
	edad			NUMERIC		NOT NULL,
	genero			BOOLEAN		NOT NULL,
	fecha			NUMERIC		NOT NULL		UNIQUE,
	PRIMARY KEY(ID),
	FOREIGN KEY(pelicula) REFERENCES PELICULA (ID));

CREATE TABLE RatingDetail
(
	fecha			NUMERIC,
	genero			NUMERIC,
	valoracion			NUMERIC,
	equipoProduccion			VARCHAR(255),
	pelicula			NUMERIC,
	actor			NUMERIC,
	puntuacion			NUMERIC		NOT NULL,
	timestamp			DATE		NOT NULL,
	userID			NUMERIC		NOT NULL,
	PRIMARY KEY(fecha, genero, valoracion, equipoProduccion, pelicula, actor),
	FOREIGN KEY(fecha) REFERENCES Fecha (ID),
	FOREIGN KEY(genero) REFERENCES genero (ID),
	FOREIGN KEY(valoracion) REFERENCES Valoracion (ID),
	FOREIGN KEY(equipoProduccion) REFERENCES EquipoProduccion (ID),
	FOREIGN KEY(pelicula) REFERENCES PELICULA (ID),
	FOREIGN KEY(actor) REFERENCES Actor (ID));

