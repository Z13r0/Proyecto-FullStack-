CREATE TABLE IF NOT EXISTS pelicula (
                                        id                  BIGINT       NOT NULL AUTO_INCREMENT,
                                        titulo              VARCHAR(50)  NOT NULL,
    sinopsis            VARCHAR(120) NOT NULL,
    duracion_minutos    INT          NOT NULL,
    clasificacion_edad  VARCHAR(10)  NOT NULL,

    CONSTRAINT pk_pelicula PRIMARY KEY (id)
    );