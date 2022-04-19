CREATE TABLE datosPelicula
(
	clvPelicula			NUMERIC,
	titulo			VARCHAR(255)		NOT NULL,
	fechaPublicacion			DATE		NOT NULL,
	duracionMinutos			NUMERIC		NOT NULL,
	popularidad			DECIMAL NOT NULL,
	presupuesto			NUMERIC		NOT NULL,
	ingresos			NUMERIC		NOT NULL,
	PRIMARY KEY(clvPelicula));


CREATE TABLE productor
(
	clv_productor			NUMERIC,
	nombreTrabajador			VARCHAR(255)		NOT NULL,
	--genero			NUMERIC		NOT NULL,
	PRIMARY KEY(clv_productor));

CREATE TABLE actor
(
	clvActor			NUMERIC,
	nombreActor			VARCHAR(255)		NOT NULL,
	--generoActor			NUMERIC		NOT NULL,
	PRIMARY KEY(clvActor));


CREATE TABLE pelicula
(
	clv_pelicula			NUMERIC,
	PRIMARY KEY(clv_pelicula));


CREATE TABLE actoresPelicula
(
	clv_peliculaCast			VARCHAR(255), --credit_id
	clvPelicula			NUMERIC,
	clvActor			NUMERIC,
	clv_pelicula			NUMERIC,
	descripcionPersonaje			VARCHAR(255)		NOT NULL, --character
	PRIMARY KEY(clv_peliculaCast),
	FOREIGN KEY(clvPelicula) REFERENCES datosPelicula (clvPelicula),
	FOREIGN KEY(clvActor) REFERENCES actor (clvActor),
	FOREIGN KEY(clv_pelicula) REFERENCES pelicula (clv_pelicula));

CREATE TABLE produccion
(
	clv_pelicula			NUMERIC,
	PRIMARY KEY(clv_pelicula));


CREATE TABLE productorPeliculas
(
	clv_productorPelis			VARCHAR(255),
	clvPelicula			NUMERIC,
	clv_productor			NUMERIC,
	clv_pelicula			NUMERIC,
	nombreDepartamento			VARCHAR(255)		NOT NULL,
	cargo			VARCHAR(255)		NOT NULL,
	PRIMARY KEY(clv_productorPelis),
	FOREIGN KEY(clv_productor) REFERENCES productor (clv_productor),
	FOREIGN KEY(clvPelicula) REFERENCES datosPelicula (clvPelicula),
	FOREIGN KEY(clv_pelicula) REFERENCES produccion (clv_pelicula));

CREATE TABLE genero
(
	clv_genero			NUMERIC,
	nombre			VARCHAR(255)		NOT NULL,
	PRIMARY KEY(clv_genero));

CREATE TABLE DetallePelicula
(
	clvPelicula			NUMERIC		NOT NULL,
	clv_pelicula		NUMERIC		NOT NULL,
	clvActuaciones			NUMERIC		NOT NULL,
	clvActor			NUMERIC		NOT NULL,
	clvGenero			NUMERIC		NOT NULL,
	userID			NUMERIC		NOT NULL,
	calificacion			NUMERIC		NOT NULL,
	PRIMARY KEY(userID,clvPelicula),
	FOREIGN KEY(clvPelicula) REFERENCES datosPelicula (clvPelicula),
	FOREIGN KEY(clvActuaciones) REFERENCES pelicula (clv_pelicula),
	FOREIGN KEY(clvActor) REFERENCES actor (clvActor),
	FOREIGN KEY(clv_pelicula) REFERENCES produccion (clv_pelicula),
	FOREIGN KEY(clvGenero) REFERENCES genero (clv_genero));

