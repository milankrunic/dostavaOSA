--ADMINISTRATOR
INSERT INTO korisnik(dtype, ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika, adresa, naziv_prodavca, email, posluje_od)
	VALUES('administrator', 'Milan', 'Krunic', 'milank', 'milan123', false, 'ADMINISTRATOR', null, null, null, null);

--KUPCI
INSERT INTO korisnik(dtype, ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika, adresa, naziv_prodavca, email, posluje_od)
	VALUES('kupac', 'Mika', 'Mikic', 'mikam', 'mika123', false, 'KUPAC', 'Vojvodjanska 50', null, null, null);
INSERT INTO korisnik(dtype, ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika, adresa, naziv_prodavca, email, posluje_od)
	VALUES('kupac', 'Laza', 'Lazic', 'lazal', 'laza123', false, 'KUPAC', 'Ustanicka 10', null, null, null);
INSERT INTO korisnik(dtype, ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika, adresa, naziv_prodavca, email, posluje_od)
	VALUES('kupac', 'Sima', 'Simic', 'simas', 'sima123', false, 'KUPAC', 'Kralja Petra 110', null, null, null);
	
--PRODAVCI
INSERT INTO korisnik(dtype, ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika, adresa, naziv_prodavca, email, posluje_od)
	VALUES('prodavac', 'Pera', 'Peric', 'perap', 'pera123', false, 'PRODAVAC', 'Vojvodjanska 50', 'naziv1', 'pera@gmail.com', '2020-05-03');
INSERT INTO korisnik(dtype, ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika, adresa, naziv_prodavca, email, posluje_od)
	VALUES('prodavac', 'Zika', 'Zikic', 'zikaz', 'zika123', false, 'PRODAVAC', 'Karadjordjeva 105', 'naziv2', 'zika@gmail.com', '2021-05-10');
INSERT INTO korisnik(dtype, ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika, adresa, naziv_prodavca, email, posluje_od)
	VALUES('prodavac', 'Jova', 'Jovic', 'jovaj', 'jova123', false, 'PRODAVAC', 'Zlatiborska 3', 'naziv3', 'jova@gmail.com', '2019-08-12');

--ARTIKLI
INSERT INTO artikal(naziv, opis, cena, putanja_slike, korisnik) VALUES('Hleb', 'Artikal za ishranu', 50, 'neka putanja', 1);
INSERT INTO artikal(naziv, opis, cena, putanja_slike, korisnik) VALUES('Mleko', 'Artikal za ishranu', 100, 'neka putanja', 1);
INSERT INTO artikal(naziv, opis, cena, putanja_slike, korisnik) VALUES('Banane', 'Voce', 120, 'neka putanja', 1);

--AKCIJE
INSERT INTO akcija(tekst, procenat, od_kad, do_kad, korisnik) VALUES('Na akciji', 10, '2020-05-03', '2020-05-03', 1);
INSERT INTO akcija(tekst, procenat, od_kad, do_kad, korisnik) VALUES('Na akciji', 5, '2021-09-03', '2021-09-07', 1);
INSERT INTO akcija(tekst, procenat, od_kad, do_kad, korisnik) VALUES('Na akciji', 20, '2021-07-05', '2021-07-09', 1);

--AKCIJE_ARTIKLI
INSERT INTO artikal_akcija(akcija, artikal) VALUES(1, 1);
INSERT INTO artikal_akcija(akcija, artikal) VALUES(2, 3);
INSERT INTO artikal_akcija(akcija, artikal) VALUES(3, 2);

--PORUDZBINE
INSERT INTO porudzbina(satnica, ocena, komentar, dostavljeno, anoniman_komentar, arhiviran_komentar, korisnik) VALUES('2020-05-03', 5, 'Zelim da mi dostavite hleb', true, true, true, 1);
INSERT INTO porudzbina(satnica, ocena, komentar, dostavljeno, anoniman_komentar, arhiviran_komentar, korisnik) VALUES('2021-10-05', 5, 'Zelim da mi dostavite voce', true, true, true, 1);
INSERT INTO porudzbina(satnica, ocena, komentar, dostavljeno, anoniman_komentar, arhiviran_komentar, korisnik) VALUES('2021-11-08', 5, 'Zelim da mi dostavite mleko', true, true, true, 1);

--STAVKE
INSERT INTO stavka(kolicina, artikal, porudzbina) VALUES(20, 1, 1);
INSERT INTO stavka(kolicina, artikal, porudzbina) VALUES(50, 3, 2);
INSERT INTO stavka(kolicina, artikal, porudzbina) VALUES(10, 2, 3);