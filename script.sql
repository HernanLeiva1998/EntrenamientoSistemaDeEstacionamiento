CREATE DATABASE sistema_de_estacionamiento;

CREATE USER admin IDENTIFIED BY 'admin';

GRANT ALL PRIVILEGES ON sistema_de_estacionamiento.* TO admin;