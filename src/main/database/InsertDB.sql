/* UTENTI */
INSERT INTO utente (nome, cognome, email, password) values ("Gino","Paolo","ginoPaolo@gmail.com","ilPiccoloGino");
INSERT INTO utente (nome, cognome, email, password) values ("Pino","Paolo","pinoPaolo@gmail.com","ilPiccoloPino");
INSERT INTO utente (nome, cognome, email, password) values ("Andrea","Maglio","andreaMaglio@gmail.com","ViaColVento");
INSERT INTO utente (nome, cognome, email, password) values ("Gaetana","Galdi","gaetanagaldi.gg@gmail.com","ViaColVento");

 /*INDIRIZZI DI GINO*/
insert into indirizzo(via, civico, citta, telefono, cap, email) values ("via Pariti", 16, "Campagna", "3385875173","84022",  "ginoPaolo@gmail.com");
insert into indirizzo(via, civico, citta, telefono, cap, email) values ("via Pariti", 16, "Campagna", "3385875173","84022",  "pinoPaolo@gmail.com");

/* UTENTE BO*/
insert into utenteBO(email, password, ruolo) values ("ciropallinopal@beerhappy.it", "pallinopal", 3);
insert into utenteBO(email, password, ruolo) values ("admin@beerhappy.it", "admin", 3);
insert into utenteBO(email, password, ruolo) values ("asd@beerhappy.it", "asd", 4);

/*BIRRE*/
INSERT INTO prodotto (nome, birrificio, descrizione, formato, quantita, prezzo, pathImage) values ("Breakfast Porter", "Impavida",
"Nuova versione della Robust Porter, con l`aggiunta di lattosio e del pregiato caffè Castillo Colombiano.", "Lattina 44cl",
50, "4.05", "https://www.beer-shop.it/uploads/items/2355/4653-1024.jpg?=1669463109");
INSERT INTO prodotto (nome, birrificio, descrizione, formato, quantita, prezzo, pathImage) values ("Lazy Wit", "Impavida",
"Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit! Opalescente, sinuosa, profumatissima… è la birra
perfetta per bevute spensierate e rinfrescanti, ma ti darà grandi soddisfazioni anche a tavola. Are you ready?", "Lattina 44cl",
50, "4.05", "https://www.beer-shop.it/uploads/items/2354/4652-1024.jpg?=1669462851");
INSERT INTO prodotto (nome, birrificio, descrizione, formato, quantita, prezzo, pathImage) values ("Free Solo", "Impavida",
"Frumento e avena si incontrano per accogliere una luppolatura gentile ma decisa . Un mix di sentori tropicali di mango,
papaya, passion fruit e una leggera nota di cocco, che ti lasciano in bocca il sapore della libertà, dei viaggi e delle
grandi imprese che si celebrano al pub.", "Lattina 44cl", 50, "4.05", "https://www.beer-shop.it/uploads/items/2347/4645-1024.jpg?=1669461102");
INSERT INTO prodotto (nome, birrificio, descrizione, formato, quantita, prezzo, pathImage) values ("Pride and Joy", "Vocation",
"Una birra di ottimo equilibrio che si apre su toni dolci e maltati per poi lasciare spazio alla luppolatura in stile West Coast. 
Morbida al palato dove su una base con note di crosta di pane e marmellata di agrumi, emergono note di scorza di pompelmo e frutta tropicale.
In chiusura un amaro gentile e una piacevole vena erbacea.", "Lattina 33cl", 50, "3.95", "https://www.beer-shop.it/uploads/items/2329/4608-1024.jpg?=1664879698");

/*CARTA*/
INSERT INTO carta(email, numero, cvv, titolare, dataScadenza) values ("ginoPaolo@gmail.com", "1111111111111", 123 ,"Gino", current_date());

/* PRODOTTO NEL CARRELLO*/
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("ginoPaolo@gmail.com", "Breakfast Porter", "Impavida", 8);
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("ginoPaolo@gmail.com", "Lazy Wit", "Impavida", 10);
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("ginoPaolo@gmail.com", "Pride and Joy", "Vocation", 2);
INSERT INTO prodottoNelCarrello (email, nome, birrificio, quantita) values("pinoPaolo@gmail.com", "Pride and Joy", "Vocation", 2);

/* ORDINI*/
insert INTO ordine(email, data, stato, via, civico, citta, telefono, prezzo) values("ginoPaolo@gmail.com", current_date(), "inoltrato", "via Pariti", 16, "Campagna", "3385875173", "4.05");
  
/* PRODOTTI ORDINATI*/
INSERT INTO prodottoOrdinato (nome, birrificio, descrizione, formato, quantita, prezzo, ordineID, pathImage) values ("Lazy Wit", "Impavida",
"Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit! Opalescente, sinuosa, profumatissima… è la birra
perfetta per bevute spensierate e rinfrescanti, ma ti darà grandi soddisfazioni anche a tavola. Are you ready?", "Lattina 44cl",
6, 4.05, 1,"");


