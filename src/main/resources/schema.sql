CREATE TABLE IF NOT EXISTS
    reservas(
    reserva_id     INT NOT NULL AUTO_INCREMENT,
    cliente_id     INT,
    destino       VARCHAR2(120),
    transporte    VARCHAR2(20),
    total_dias     INT,
    total_personas INT,
    costo         VARCHAR2(30),
    estado        VARCHAR2(20),
    fecha_ingreso  VARCHAR2(20),
    fecha_salida   VARCHAR2(20),
    PRIMARY KEY (reserva_id)
);
