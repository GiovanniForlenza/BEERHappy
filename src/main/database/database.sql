DROP DATABASE IF EXISTS beerhappy;

create DATABASE beerhappy;
USE beerhappy;

DROP user IF EXISTS 'beerhappy'@'localhost';
CREATE USER 'beerhappy'@'localhost' IDENTIFIED BY 'Amministratore';
GRANT ALL ON storage.* TO 'beerhappy'@'localhost';

CREATE TABLE utente(
    nome varchar(30) not null,
    cognome varchar(30) not null,
    email varchar(40) not null unique,
    password varchar(25) not null, 
    primary key (email)
);

CREATE TABLE utenteBO(
	email varchar(40) not null,
    password varchar(25) not null,
	ruolo enum('1', '2', '3', '4'),
    primary key(email),
    check((ruolo>0 AND ruolo<5) AND(right(email, 13)='@beerhappy.it'))
);
 
CREATE TABLE prodotto(
    nome varchar(30) not null,
    birrificio varchar(30) not null,
    descrizione varchar(1000) null,
    formato varchar(25) not null,
    quantita int not null,
    prezzo varchar(25) not null,
    pathImage varchar(250) null,
    primary key(nome, birrificio)
);

CREATE TABLE indirizzo(
	indirizzoID int auto_increment,
    via varchar(20) not null,
    civico int not null,
    citta varchar(20) not null,
    telefono varchar(10) not null,
    cap varchar(5) not null,
    email varchar(40) not null,
    primary key(indirizzoID),
	foreign key(email) references utente(email) on delete cascade
												on update cascade
);

CREATE TABLE carta(
	cartaID int auto_increment,
	numero varchar(25) not null,
    email varchar(40) not null, 
    cvv int not null,
    titolare varchar(30) not null,
    dataScadenza varchar(10) not null,
    primary key(cartaID),
    foreign key(email) references utente(email) on delete cascade
												on update cascade
);

 CREATE TABLE prodottoNelCarrello(
	email varchar(40) not null,
    nome varchar(30) not null,
    birrificio varchar(30) not null,
    quantita int not null,
    primary key(nome, birrificio, email),
    foreign key(nome, birrificio) references prodotto(nome, birrificio) on delete cascade
																		on update cascade,
    foreign key(email) references utente(email) on delete cascade
												on update cascade
 );

 CREATE TABLE ordine(
	ordineID int auto_increment,
    email varchar(40) not null,
    data varchar(25), 
    stato enum('inoltrato', 'inConsegna', 'consegnato', 'annullato'),
    prezzo float not null,
	via varchar(20) not null,
    civico int not null,
    citta varchar(20) not null,
    telefono varchar(10) not null,
    primary key(ordineID),
    foreign key(email) references utente(email) on delete cascade
												on update cascade
 );
 
 CREATE TABLE prodottoOrdinato(
	nome varchar(30) not null,
    birrificio varchar(30) not null,
    descrizione varchar(1000) null,
    formato varchar(25) not null,
    quantita int not null,
    prezzo float not null,
    ordineID int not null, 
    pathImage varchar(50) not null,
    primary key(ordineID, nome, birrificio),
    foreign key(ordineID) references ordine(ordineID) on delete cascade
													on update cascade
   
 );