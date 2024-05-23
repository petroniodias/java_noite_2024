CREATE DATABASE oficina_mecanica;

USE oficina_mecanica;

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    telefone VARCHAR(15)
);

CREATE TABLE veiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100),
    placa VARCHAR(15),
    proprietario_id INT,
    FOREIGN KEY (proprietario_id) REFERENCES cliente(id)
);

CREATE TABLE servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    veiculo_id INT,
    descricao VARCHAR(255),
    valor DOUBLE,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (veiculo_id) REFERENCES veiculo(id)
);
