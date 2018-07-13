
drop database if exists alunos_banco;
create database if not exists alunos_banco;

use alunos_banco;

CREATE TABLE alunos(
id             int AUTO_INCREMENT PRIMARY key,
nome           varchar(100),
maticula       varchar(100),
nota1          float,
nota2          float,
nota3          float,
frequencia     tinyint
);
