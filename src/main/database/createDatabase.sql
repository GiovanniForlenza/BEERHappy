DROP DATABASE IF EXISTS beerhappy;

create DATABASE beerhappy;
USE beerhappy;

DROP user IF EXISTS 'beerhappy'@'localhost';
CREATE USER 'beerhappy'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL ON storage.* TO 'beerhappy'@'localhost';

CREATE TABLE utente(
	id int primary key,
	nome varchar(60) not null,
	cognome varchar(20) not null,
	email varchar(10) not null,
	password varchar(500) null
);

INSERT INTO utente values (1,"Gino","Paolo","ginoPaolo@gmail.com","ilPiccoloGino");
