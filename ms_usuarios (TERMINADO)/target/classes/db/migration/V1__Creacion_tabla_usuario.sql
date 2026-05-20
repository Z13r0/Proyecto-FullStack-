-- Tabla creada como 'usuario' (minúscula)
-- CREATE TABLE IF NOT EXISTS usuario (...)

CREATE TABLE IF NOT EXISTS usuario (
                                       id          BIGINT          NOT NULL AUTO_INCREMENT,
                                       nombre      VARCHAR(100)    NOT NULL,
    email       VARCHAR(320)    NOT NULL UNIQUE,
    contrasena  VARCHAR(65)     NOT NULL,
    direccion   VARCHAR(400)    NOT NULL,
    pais        VARCHAR(70)     NOT NULL,

    CONSTRAINT pk_usuario PRIMARY KEY (id)
    );