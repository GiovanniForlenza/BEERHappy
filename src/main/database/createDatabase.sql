DROP DATABASE IF EXISTS beerhappy;

create DATABASE beerhappy;
USE beerhappy;

DROP user IF EXISTS 'beerhappy'@'localhost';
CREATE USER 'beerhappy'@'localhost' IDENTIFIED BY 'Amministratore';
GRANT ALL ON storage.* TO 'beerhappy'@'localhost';

CREATE TABLE utente(
                       nome varchar(25) not null,
                       cognome varchar(25) not null,
                       email varchar(50) not null unique,
                       password varchar(500) not null,
                       primary key (email)
);

CREATE TABLE utenteBO(
                         email varchar(50) not null,
                         password varchar(50) not null,
                         ruolo int not null,
                         primary key(email, password),
                         check((ruolo>0 AND ruolo<4) AND(right(email, 13)='@beerhappy.it'))
);

CREATE TABLE prodotto(
                         nome varchar(25) not null,
                         birrificio varchar(25) not null,
                         descrizione varchar(750) null,
                         formato varchar(25) not null,
                         quantita int not null,
                         prezzo varchar(25) not null,
                         constraint beerID primary key(nome, birrificio)
);

CREATE TABLE indirizzo(
                          via varchar(20) not null,
                          civico int not null,
                          citta varchar(20) not null,
                          telefono varchar(10) not null,
                          cap varchar(5) not null,
                          email varchar(25) not null,
                          primary key(via, civico),
                          foreign key(email) references utente(email) on delete cascade
                              on update cascade
);

CREATE TABLE carta(
                      numero varchar(16) not null,
                      email varchar(25) not null,
                      cvv int not null,
                      titolare varchar(20) not null,
                      primary key(numero),
                      foreign key(email) references utente(email) on delete cascade
);

CREATE TABLE prodottoNelCarrello(
                                    email varchar(25) not null,
                                    nome varchar(50) not null,
                                    birrificio varchar(50) not null,
                                    quantita int not null,
                                    primary key(email, nome, birrificio),
                                    foreign key(nome, birrificio) references prodotto(nome, birrificio) on delete cascade
                                        on update cascade,
                                    foreign key(email) references utente(email) on delete cascade
);

CREATE TABLE ordine(
                       ordineID int not null auto_increment,
                       email varchar(25) not null,
                       data date,
                       stato enum('inoltrato', 'visualizzato', 'pronto', 'in consegna', 'cosegnato', 'annullato'),
                       via varchar(20) not null,
                       civico int not null,
                       numero varchar(16) not null,
                       prezzo float not null,
                       primary key(ordineID),
                       foreign key(via, civico) references indirizzo(via, civico),
                       foreign key(numero) references carta(numero),
                       foreign key(email) references utente(email)
);

CREATE TABLE prodottoOrdinato(
                                 nome varchar(50) not null,
                                 birrificio varchar(50) not null,
                                 ordineID int not null,
                                 quantita int not null,
                                 primary key(ordineID, nome, birrificio),
                                 foreign key(nome, birrificio) references prodotto(nome, birrificio) on update cascade,
                                 foreign key(ordineID) references ordine(ordineID) on delete cascade
);

/*Come memorizziamo i prodotti nel carrello? */
/* UTENTI */
INSERT INTO utente (nome, cognome, email, password) values ("Gino","Paolo","ginoPaolo@gmail.com","ilPiccoloGino");
INSERT INTO utente (nome, cognome, email, password) values ("Pino","Paolo","pinoPaolo@gmail.com","ilPiccoloPino");

/* INDIRIZZI */
insert into indirizzo(via, civico, citta, telefono, cap, email) values ("via Pariti", 16, "Campagna", "3385875173","84022",  "ginoPaolo@gmail.com");
insert into indirizzo(via, civico, citta, telefono, cap, email) values ("via Pariti", 12, "Campagna", "3385875173","84022",  "ginoPaolo@gmail.com");

/* AMMINISTRATORE */
insert into utenteBO(email, password, ruolo) values ("ciropallinopal@beerhappy.it", "pallinopal", 1);

/* BIRRE */
INSERT INTO prodotto (nome, birrificio, descrizione, formato, quantita, prezzo) values ("Breakfast Porter", "Impavida",
                                                                                        "Nuova versione della Robust Porter, con l`aggiunta di lattosio e del pregiato caffè Castillo Colombiano.", "Lattina 44cl",
                                                                                        50, "4.05");
INSERT INTO prodotto (nome, birrificio, descrizione, formato, quantita, prezzo) values ("Lazy Wit", "Impavida",
                                                                                        "Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit! Opalescente, sinuosa, profumatissima… è la birra
                                                                                        perfetta per bevute spensierate e rinfrescanti, ma ti darà grandi soddisfazioni anche a tavola. Are you ready?", "Lattina 44cl",
                                                                                        50, "4.05");
INSERT INTO prodotto (nome, birrificio, descrizione, formato, quantita, prezzo) values ("Free Solo", "Impavida",
                                                                                        "Frumento e avena si incontrano per accogliere una luppolatura gentile ma decisa . Un mix di sentori tropicali di mango,
                                                                                        papaya, passion fruit e una leggera nota di cocco, che ti lasciano in bocca il sapore della libertà, dei viaggi e delle
                                                                                        grandi imprese che si celebrano al pub.", "Lattina 44cl", 50, "4.05");
INSERT INTO prodotto (nome, birrificio, descrizione, formato, quantita, prezzo) values ("Pride & Joy", "Vocation",
                                                                                        "Una birra di ottimo equilibrio che si apre su toni dolci e maltati per poi lasciare spazio alla luppolatura in stile West Coast. 
                                                                                        Morbida al palato dove su una base con note di crosta di pane e marmellata di agrumi, emergono note di scorza di pompelmo e frutta tropicale.
                                                                                        In chiusura un amaro gentile e una piacevole vena erbacea.", "Lattina 33cl", 50, "3.95");

/*CARTA*/
INSERT INTO carta(email, numero, cvv, titolare) values ("ginoPaolo@gmail.com", "3241234567891234", 580, "Gino");

/* PRODOTTO NEL CARRELLO */
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("ginoPaolo@gmail.com", "Breakfast Porter", "Impavida", 8);
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("ginoPaolo@gmail.com", "Lazy Wit", "Impavida", 10);
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("ginoPaolo@gmail.com", "Pride & Joy", "Vocation", 2);

/* ORDINE */
INSERT INTO ordine (ordineID, email, data, stato, via, civico, numero, prezzo) values (1, "ginoPaolo@gmail.com", "2023-12-12", "inoltrato", "via Pariti", 16, 3241234567891234, 3);

/*PRODOTTO ORDINATO*/
INSERT INTO prodottoOrdinato (nome, birrificio, ordineID, quantita) values ("Pride & Joy", "Vocation", 1, 4);
INSERT INTO prodottoOrdinato (nome, birrificio, ordineID, quantita) values ("Lazy Wit", "Impavida", 1, 5);