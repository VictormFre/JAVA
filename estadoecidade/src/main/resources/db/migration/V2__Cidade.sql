CREATE TABLE Cidade (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idestado BIGINT NOT NULL,
    FOREIGN KEY (idestado) REFERENCES Estado(id)
);