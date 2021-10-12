INSERT INTO prodavac(ime, prezime, korisnicko_ime, lozinka, blokiran, naziv_prodavca, email, adresa, posluje_od) VALUES('Pera', 'Peric', 'perap', 'pera123', true, 'naziv1', 'pera@gmail.com', 'Cara Dusana 12', '2020-05-03');
INSERT INTO prodavac(ime, prezime, korisnicko_ime, lozinka, blokiran, naziv_prodavca, email, adresa, posluje_od) VALUES('Zika', 'Zikic', 'zikaz', 'zika123', false, 'naziv2', 'zika@gmail.com', 'Karadjordjeva 105', '2021-05-10');
INSERT INTO prodavac(ime, prezime, korisnicko_ime, lozinka, blokiran, naziv_prodavca, email, adresa, posluje_od) VALUES('Jova', 'Jovic', 'jovaj', 'jova123', false, 'naziv3', 'jova@gmail.com', 'Zlatiborska 3', '2019-08-12');

INSERT INTO akcija(tekst, procenat, od_kad, do_kad, prodavac) VALUES('Na akciji', 10, '2020-05-03', '2020-05-03', 1);
INSERT INTO akcija(tekst, procenat, od_kad, do_kad, prodavac) VALUES('Na akciji', 5, '2021-09-03', '2021-09-07', 3);
INSERT INTO akcija(tekst, procenat, od_kad, do_kad, prodavac) VALUES('Na akciji', 20, '2021-07-05', '2021-07-09', 2);

INSERT INTO artikal(naziv, opis, cena, putanja_slike, prodavac) VALUES('Hleb', 'Artikal za ishranu', 50, 'neka putanja', 3);
INSERT INTO artikal(naziv, opis, cena, putanja_slike, prodavac) VALUES('Mleko', 'Artikal za ishranu', 100, 'neka putanja', 1);
INSERT INTO artikal(naziv, opis, cena, putanja_slike, prodavac) VALUES('Banane', 'Voce', 120, 'neka putanja', 2);

INSERT INTO artikal_akcija(akcija, artikal) VALUES(1, 1);
INSERT INTO artikal_akcija(akcija, artikal) VALUES(2, 3);
INSERT INTO artikal_akcija(akcija, artikal) VALUES(3, 2);

INSERT INTO administrator(ime, prezime, korisnicko_ime, lozinka, blokiran) VALUES('Milan', 'Krunic', 'milank', 'milan123', false);

INSERT INTO kupac(ime, prezime, korisnicko_ime, lozinka, blokiran, adresa) VALUES('Mika', 'Mikic', 'mikam', 'mika123', false, 'Vojvodjanska 50');
INSERT INTO kupac(ime, prezime, korisnicko_ime, lozinka, blokiran, adresa) VALUES('Laza', 'Lazarevic', 'lazal', 'laza123', false, 'Ustanicka 10');
INSERT INTO kupac(ime, prezime, korisnicko_ime, lozinka, blokiran, adresa) VALUES('Sima', 'Simic', 'simas', 'sima123', false, 'Kralja Petra 110');

INSERT INTO porudzbina(satnica, ocena, komentar, dostavljeno, anoniman_komentar, arhiviran_komentar, kupac) VALUES('2020-05-03', 5, 'Zelim da mi dostavite hleb', true, true, true, 1);
INSERT INTO porudzbina(satnica, ocena, komentar, dostavljeno, anoniman_komentar, arhiviran_komentar, kupac) VALUES('2021-10-05', 5, 'Zelim da mi dostavite voce', true, true, true, 2);
INSERT INTO porudzbina(satnica, ocena, komentar, dostavljeno, anoniman_komentar, arhiviran_komentar, kupac) VALUES('2021-11-08', 5, 'Zelim da mi dostavite mleko', true, true, true, 3);

INSERT INTO stavka(kolicina, artikal, porudzbina) VALUES(20, 1, 1);
INSERT INTO stavka(kolicina, artikal, porudzbina) VALUES(50, 3, 2);
INSERT INTO stavka(kolicina, artikal, porudzbina) VALUES(10, 2, 3);