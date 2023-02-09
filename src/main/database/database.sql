DROP DATABASE IF EXISTS beerhappy;

create DATABASE beerhappy;
USE beerhappy;

DROP user IF EXISTS 'beerhappy'@'localhost';
CREATE USER 'beerhappy'@'localhost' IDENTIFIED BY 'Amministratore';
GRANT ALL ON storage.* TO 'beerhappy'@'localhost';

CREATE TABLE utente(
    nome varchar(25) not null,
    cognome varchar(25) not null,
    email varchar(40) not null unique,
    password varchar(25) not null, 
    primary key (email)
);

CREATE TABLE utenteBO(
	email varchar(40) not null,
    password varchar(25) not null,
	ruolo enum('1', '2', '3', '4'),
    primary key(email),
    check((ruolo>0 AND ruolo<4) AND(right(email, 13)='@beerhappy.it'))
);
 
CREATE TABLE prodotto(
    nome varchar(25) not null,
    birrificio varchar(25) not null,
    descrizione varchar(750) null,
    formato varchar(25) not null,
    quantita int not null,
    prezzo varchar(25) not null,
    pathImage varchar(50),
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
	numero varchar(16) not null,
    email varchar(25) not null, 
    cvv int not null,
    titolare varchar(20) not null,
    dataScadenza date not null,
    primary key(cartaID),
    foreign key(email) references utente(email) on delete cascade
												on update cascade
);

 CREATE TABLE prodottoNelCarrello(
	email varchar(25) not null,
    nome varchar(50) not null,
    birrificio varchar(50) not null,
    quantita int not null,
    primary key(nome, birrificio, email),
    foreign key(nome, birrificio) references prodotto(nome, birrificio) on delete cascade
																		on update cascade,
    foreign key(email) references utente(email) on delete cascade
												on update cascade
 );

 CREATE TABLE ordine(
	ordineID int auto_increment,
    email varchar(25) not null,
    data varchar(25), 
    stato enum('inoltrato', 'in consegna', 'cosegnato', 'annullato'),
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
    nome varchar(25) not null,
    birrificio varchar(25) not null,
    descrizione varchar(750) null,
    formato varchar(25) not null,
    quantita int not null,
    prezzo float not null,
    ordineID int not null, 
    pathImage varchar(50) not null,
    primary key(ordineID, nome, birrificio),
    foreign key(ordineID) references ordine(ordineID) on delete cascade
													on update cascade
   
 );
 
/*Come memorizziamo i prodotti nel carrello? */
/* UTENTI */
INSERT INTO utente (nome, cognome, email, password) values ("Gino","Paolo","ginoPaolo@gmail.com","ilPiccoloGino");
INSERT INTO utente (nome, cognome, email, password) values ("Pino","Paolo","pinoPaolo@gmail.com","ilPiccoloPino");
INSERT INTO utente (nome, cognome, email, password) values ("Andrea","Maglio","andreaMaglio@gmail.com","ViaColVento");
INSERT INTO utente (nome, cognome, email, password) values ("Gaetana","Galdi","gaetanagaldi.gg@gmail.com","ViaColVento");
 /*INDIRIZZI DI GINO*/
insert into indirizzo(via, civico, citta, telefono, cap, email) values ("via Pariti", 16, "Campagna", "3385875173","84022",  "ginoPaolo@gmail.com");
insert into indirizzo(via, civico, citta, telefono, cap, email) values ("via Pariti", 16, "Campagna", "3385875173","84022",  "pinoPaolo@gmail.com");

insert into utenteBO(email, password, ruolo) values ("ciropallinopal@beerhappy.it", "pallinopal", 3);
insert into utenteBO(email, password, ruolo) values ("admin@beerhappy.it", "admin", 3);

/*BIRRE*/
INSERT INTO prodotto (nome, birrificio, descrizione, formato, quantita, prezzo, pathImage) values ("Breakfast Porter", "Impavida",
"Nuova versione della Robust Porter, con l`aggiunta di lattosio e del pregiato caffè Castillo Colombiano.", "Lattina 44cl",
50, "4.05", "catalogo/");
INSERT INTO prodotto (nome, birrificio, descrizione, formato, quantita, prezzo, pathImage) values ("Lazy Wit", "Impavida",
"Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit! Opalescente, sinuosa, profumatissima… è la birra
perfetta per bevute spensierate e rinfrescanti, ma ti darà grandi soddisfazioni anche a tavola. Are you ready?", "Lattina 44cl",
50, "4.05", "catalogo/");
INSERT INTO prodotto (nome, birrificio, descrizione, formato, quantita, prezzo, pathImage) values ("Free Solo", "Impavida",
"Frumento e avena si incontrano per accogliere una luppolatura gentile ma decisa . Un mix di sentori tropicali di mango,
papaya, passion fruit e una leggera nota di cocco, che ti lasciano in bocca il sapore della libertà, dei viaggi e delle
grandi imprese che si celebrano al pub.", "Lattina 44cl", 50, "4.05", "catalogo/");
INSERT INTO prodotto (nome, birrificio, descrizione, formato, quantita, prezzo, pathImage) values ("Pride and Joy", "Vocation",
"Una birra di ottimo equilibrio che si apre su toni dolci e maltati per poi lasciare spazio alla luppolatura in stile West Coast. 
Morbida al palato dove su una base con note di crosta di pane e marmellata di agrumi, emergono note di scorza di pompelmo e frutta tropicale.
In chiusura un amaro gentile e una piacevole vena erbacea.", "Lattina 33cl", 50, "3.95", "catalogo/");

/*CARTA*/
INSERT INTO carta(email, numero, cvv, titolare, dataScadenza) values ("ginoPaolo@gmail.com", "1111111111111", 123 ,"Gino", current_date());

/* PRODOTTO NEL CARRELLO*/
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("ginoPaolo@gmail.com", "Breakfast Porter", "Impavida", 8);
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("ginoPaolo@gmail.com", "Lazy Wit", "Impavida", 10);
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("ginoPaolo@gmail.com", "Pride and Joy", "Vocation", 2);
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("pinoPaolo@gmail.com", "Pride and Joy", "Vocation", 2);
/* ORDINE 
INSERT INTO ordine (ordineID, email, data, stato, via, civico, numero, prezzo) values (1, "ginoPaolo@gmail.com", "2023-12-12", "inoltrato", "via Pariti", 16, 3241234567891234, 3);

/*PRODOTTO ORDINATO
INSERT INTO prodottoOrdinato (nome, birrificio, ordineID, quantita) values ("Pride & Joy", "Vocation", 1, 4);
INSERT INTO prodottoOrdinato (nome, birrificio, ordineID, quantita) values ("Lazy Wit", "Impavida", 1, 5);
*/