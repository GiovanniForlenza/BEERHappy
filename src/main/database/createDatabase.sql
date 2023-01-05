DROP DATABASE IF EXISTS beerhappy;

create DATABASE beerhappy;
USE beerhappy;

DROP user IF EXISTS 'beerhappy'@'localhost';
CREATE USER 'beerhappy'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL ON storage.* TO 'beerhappy'@'localhost';

CREATE TABLE utente(
	id int auto_increment,
	nome varchar(25) not null,
	cognome varchar(25) not null,
	email varchar(50) not null unique,
	password varchar(500) not null,
    primary key (id)
);

CREATE TABLE beer(
	nome varchar(25) not null,
    birrificio varchar(25) not null,
    descrizione varchar(250) null,
    formato varchar(25) not null,
    quantita int not null,
    prezzo varchar(25) not null,
    primary key(nome, birrificio)
);

/* UTENTI */
INSERT INTO utente (nome, cognome, email, password) values ("Gino","Paolo","ginoPaolo@gmail.com","ilPiccoloGino");

/* BIRRE */
INSERT INTO beer (nome, birrificio, descrizione, formato, quantita, prezzo) values ("Breakfast Porter", "Impavida",
 "Nuova versione della Robust Porter, con l`aggiunta di lattosio e del pregiato caffè Castillo Colombiano.", "Lattina 44cl",
 50, "4.05");
INSERT INTO beer (nome, birrificio, descrizione, formato, quantita, prezzo) values ("Lazy Wit", "Impavida",
 "Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit! Opalescente, sinuosa, profumatissima… è la birra
 perfetta per bevute spensierate e rinfrescanti, ma ti darà grandi soddisfazioni anche a tavola. Are you ready?", "Lattina 44cl",
 50, "4.05");

