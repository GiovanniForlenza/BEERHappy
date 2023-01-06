DROP DATABASE IF EXISTS beerhappy;

create DATABASE beerhappy;
USE beerhappy;



DROP user IF EXISTS 'beerhappy'@'localhost';
CREATE USER 'beerhappy'@'localhost' IDENTIFIED BY 'admin';
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

CREATE TABLE beer(
                     nome varchar(25) not null,
                     birrificio varchar(25) not null,
                     descrizione varchar(750) null,
                     formato varchar(25) not null,
                     quantita int not null,
                     prezzo varchar(25) not null,
                     constraint beerID primary key(nome, birrificio)
);

CREATE TABLE INDIRIZZO(
                          via varchar(20) not null,
                          civico int not null,
                          citta varchar(20),
                          telefono varchar(10),
                          cap varchar(5),
                          email varchar(25) not null,
                          PRIMARY KEY(via, civico),
                          foreign key(email) REFERENCES utente(email) ON DELETE CASCADE
);

CREATE TABLE CARTA(
                      numero varchar(16) NOT NULL,
                      email varchar(25) NOT NULL,
                      cvv int NOT NULL,
                      titolare varchar(20) NOT NULL,
                      PRIMARY KEY(numero),
                      FOREIGN KEY(email) REFERENCES utente(email) ON DELETE CASCADE
);

CREATE TABLE prodottoNelCarrello(
                                    email varchar(25),
                                    nome varchar(50),
                                    birrificio varchar(50),
                                    quantita int,
                                    primary key(email, nome, birrificio),
                                    foreign key(nome, birrificio) references beer(nome, birrificio),
                                    foreign key(email) references utente(email)
);

/*Come memorizziamo i prodotti nel carrello? */
/* UTENTI */
INSERT INTO utente (nome, cognome, email, password) values ("Gino","Paolo","ginoPaolo@gmail.com","ilPiccoloGino");
INSERT INTO utente (nome, cognome, email, password) values ("Pino","Paolo","pinoPaolo@gmail.com","ilPiccoloPino");
/* INDIRIZZI */
insert into INDIRIZZO(via, civico, citta, telefono, cap, email) values ("via Pariti", 16, "Campagna", "3385875173","84022",  "ginoPaolo@gmail.com");
insert into INDIRIZZO(via, civico, citta, telefono, cap, email) values ("via Pariti", 12, "Campagna", "3385875173","84022",  "ginoPaolo@gmail.com");
/* AMMINISTRATORE */
insert into utenteBO(email, password, ruolo) values ("ciropallinopal@beerhappy.it", "pallinopal", 1);
/* BIRRE */
INSERT INTO beer (nome, birrificio, descrizione, formato, quantita, prezzo) values ("Breakfast Porter", "Impavida",
                                                                                    "Nuova versione della Robust Porter, con l`aggiunta di lattosio e del pregiato caffè Castillo Colombiano.", "Lattina 44cl",
                                                                                    50, "4.05");
INSERT INTO beer (nome, birrificio, descrizione, formato, quantita, prezzo) values ("Lazy Wit", "Impavida",
                                                                                    "Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit! Opalescente, sinuosa, profumatissima… è la birra
                                                                                    perfetta per bevute spensierate e rinfrescanti, ma ti darà grandi soddisfazioni anche a tavola. Are you ready?", "Lattina 44cl",
                                                                                    50, "4.05");
INSERT INTO beer (nome, birrificio, descrizione, formato, quantita, prezzo) values ("Free Solo", "Impavida",
                                                                                    "Frumento e avena si incontrano per accogliere una luppolatura gentile ma decisa . Un mix di sentori tropicali di mango,
                                                                                    papaya, passion fruit e una leggera nota di cocco, che ti lasciano in bocca il sapore della libertà, dei viaggi e delle
                                                                                    grandi imprese che si celebrano al pub.", "Lattina 44cl", 50, "4.05");
INSERT INTO beer (nome, birrificio, descrizione, formato, quantita, prezzo) values ("Pride & Joy", "Vocation",
                                                                                    "Una birra di ottimo equilibrio che si apre su toni dolci e maltati per poi lasciare spazio alla luppolatura in stile West Coast. 
                                                                                    Morbida al palato dove su una base con note di crosta di pane e marmellata di agrumi, emergono note di scorza di pompelmo e frutta tropicale.
                                                                                    In chiusura un amaro gentile e una piacevole vena erbacea.", "Lattina 33cl", 50, "3.95");


/* PRODOTTO NEL CARRELLO */
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("ginoPaolo@gmail.com", "Breakfast Porter", "Impavida", 8);
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("ginoPaolo@gmail.com", "Lazy Wit", "Impavida", 10);
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("ginoPaolo@gmail.com", "Pride & Joy", "Vocation", 2);