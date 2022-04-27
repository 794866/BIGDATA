CREATE TABLE pelicula
(
	clv_pelicula			NUMERIC,
	titulo			VARCHAR(255)		NOT NULL,
	fechaPublicacion			DATE		NOT NULL,
	duracionMinutos			NUMERIC		NOT NULL,
	presupuesto			NUMERIC		NOT NULL,
	ingresos			NUMERIC		NOT NULL,
	popularidad			NUMERIC		NOT NULL,
	generoPelicula	VARCHAR(255),
	PRIMARY KEY(clv_pelicula));

CREATE TABLE crew
(
	fk_productor			VARCHAR(255),
	fk_pelicula NUMERIC NOT NULL,
	PRIMARY KEY(clv_crew)
	FOREIGN KEY(clv_pelicula) REFERENCES pelicula (clv_pelicula),
	FOREIGN KEY(clv_crew) REFERENCES productor (clv_crew),);

CREATE TABLE casting
(
	fk_actor			VARCHAR(255),
	fk_pelicula NUMERIC NOT NULL,
	PRIMARY KEY(clv_cast),
	FOREIGN KEY(clv_pelicula) REFERENCES pelicula (clv_pelicula)
	FOREIGN KEY(clv_cast) REFERENCES actor (clv_cast)
);

CREATE TABLE actor
(
	clv_actor			NUMERIC,
	clv_cast			VARCHAR(255),
	nombreActor			VARCHAR(255)		NOT NULL,
	descripcionPersonaje			VARCHAR(255)		NOT NULL,
	PRIMARY KEY(clv_cast));

CREATE TABLE productor
(
	idproductor			NUMERIC,
	clv_crew			VARCHAR(255),
	nombreDepartamento			VARCHAR(255)		NOT NULL,
	cargo			VARCHAR(255)		NOT NULL,
	nombreTrabajador			VARCHAR(255)		NOT NULL,
	PRIMARY KEY(clv_crew));

CREATE TABLE valoracionPelicula
(
	clv_pelicula			NUMERIC		NOT NULL,
	userID			NUMERIC		NOT NULL,
	calificacion	NUMERIC		NOT NULL,
	PRIMARY KEY(userID,clv_pelicula),
	FOREIGN KEY(clv_pelicula) REFERENCES pelicula (clv_pelicula));


--actor table
CREATE INDEX clave_actor_from_actorTable ON actor (clv_actor);

--productor table
CREATE INDEX clave_productor_from_productorTable ON productor(clv_productor);
CREATE INDEX clv_crew_from_productorTable ON productor(clv_crew);

--table pelicula
CREATE INDEX clv_pelicula_from_peliculaTable ON pelicula(clv_pelicula);

--crew table
CREATE INDEX clv_crew_from_crewTable ON crew(clv_crew);
CREATE INDEX clv_pelicula_from_crewTable ON crew(clv_pelicula);

--casting table
CREATE INDEX clv_cast_from_actorTable ON casting(clv_cast);
CREATE INDEX clv_pelicula_from_actorTable ON casting(clv_pelicula);

--valoracionesPelicula Table
CREATE INDEX userID_from_factTable ON valoracionPelicula(userID);
CREATE INDEX clv_pelicula_from_factTable ON valoracionPelicula(clv_pelicula);


--relacion productores y actores
ALTER TABLE valoracionpelicula ADD COLUMN fk_productor NUMERIC;
ALTER TABLE valoracionpelicula ADD FOREIGN KEY fk_productor REFERENCES productor(clv_productor);
ALTER TABLE valoracionpelicula ADD COLUMN fk_actor NUMERIC;
ALTER TABLE valoracionpelicula ADD FOREIGN KEY fk_actor REFERENCES actor(clv_actor);


update valoracionpelicula set fk_productor = pro.clv_crew
FROM productor pro INNER JOIN crew ON crew.fk_productor = pro.clv_crew
where valoracionpelicula.clv_pelicula = crew.clv_pelicula

update valoracionpelicula set fk_actor = pro.clv_crew
FROM actor act INNER JOIN casting cas ON cas.fk_actor = act.clv_cast
where valoracionpelicula.clv_pelicula = cas.clv_pelicula