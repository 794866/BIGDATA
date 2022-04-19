CREATE TABLE crew
(
	clv_crew			NUMERIC,
	PRIMARY KEY(clv_crew));


CREATE TABLE genero
(
	clv_genero			NUMERIC,
	nombre			VARCHAR(255)		NOT NULL,
	PRIMARY KEY(clv_genero));


CREATE TABLE casting
(
	clv_cast			NUMERIC,
	PRIMARY KEY(clv_cast));


CREATE TABLE actor
(
	clv_actor			NUMERIC,
	clv_cast			NUMERIC,
	nombreActor			VARCHAR(255)		NOT NULL,
	descripcionPersonaje			VARCHAR(255)		NOT NULL,
	PRIMARY KEY(clv_actor,clv_cast),
	FOREIGN KEY(clv_cast) REFERENCES casting (clv_cast));

CREATE TABLE productor
(
	clv_productor			NUMERIC,
	clv_crew			NUMERIC,
	nombreDepartamento			VARCHAR(255)		NOT NULL,
	cargo			VARCHAR(255)		NOT NULL,
	nombreTrabajador			VARCHAR(255)		NOT NULL,
	PRIMARY KEY(clv_productor,clv_crew),
	FOREIGN KEY(clv_crew) REFERENCES crew (clv_crew));

CREATE TABLE pelicula
(
	clv_pelicula			NUMERIC,
	clv_crew			NUMERIC,
	clv_cast			NUMERIC,
	titulo			VARCHAR(255)		NOT NULL,
	fechaPublicacion			DATE		NOT NULL,
	duracionMinutos			NUMERIC		NOT NULL,
	presupuesto			NUMERIC		NOT NULL,
	ingresos			NUMERIC		NOT NULL,
	popularidad			NUMERIC		NOT NULL,
	PRIMARY KEY(clv_pelicula),
	FOREIGN KEY(clv_crew) REFERENCES crew (clv_crew),
	FOREIGN KEY(clv_cast) REFERENCES casting (clv_cast));

CREATE TABLE valoracionPelicula
(
	clv_pelicula			NUMERIC		NOT NULL,
	clv_genero			NUMERIC		NOT NULL,
	userID			NUMERIC		NOT NULL,
	calificacion	NUMERIC		NOT NULL,
	clvProduccionPelicula			NUMERIC		NOT NULL		UNIQUE,
	clvActuaciones			NUMERIC		NOT NULL		UNIQUE,
	clvActor			VARCHAR(255)		NOT NULL		UNIQUE,
	clvGruposGenero			NUMERIC		NOT NULL		UNIQUE,
	PRIMARY KEY(userID,clv_pelicula),
	FOREIGN KEY(clv_pelicula) REFERENCES pelicula (clv_pelicula),
	FOREIGN KEY(clv_genero) REFERENCES genero (clv_genero));

