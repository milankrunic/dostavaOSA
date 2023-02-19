-- KORISNICI
INSERT INTO korisnik(ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika) 
	VALUES('Milan', 'Krunic', 'milank', 'milan123', false, 'ADMINISTRATOR');
INSERT INTO korisnik(ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika)
	VALUES('Mika', 'Mikic', 'mikam', 'mika123', false, 'KUPAC');
INSERT INTO korisnik(ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika)
	VALUES('Laza', 'Lazic', 'lazal', 'laza123', false, 'KUPAC');
INSERT INTO korisnik(ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika)
	VALUES('Sima', 'Simic', 'simas', 'sima123', false, 'KUPAC');
INSERT INTO korisnik(ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika)
	VALUES('Pera', 'Peric', 'perap', 'pera123', false, 'PRODAVAC');
INSERT INTO korisnik(ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika)
	VALUES('Zika', 'Zikic', 'zikaz', 'zika123', true, 'PRODAVAC');
INSERT INTO korisnik(ime, prezime, korisnicko_ime, lozinka, blokiran, tip_korisnika)
	VALUES('Jova', 'Jovic', 'jovaj', 'jova123', false, 'PRODAVAC');

--ADMINISTRATOR
INSERT INTO administrator(korisnik_id)
	VALUES(1);

--KUPCI
INSERT INTO kupac(adresa, korisnik_id)
	VALUES('Vojvodjanska 50', 2);
INSERT INTO kupac(adresa, korisnik_id)
	VALUES('Ustanicka 10', 3);
INSERT INTO kupac(adresa, korisnik_id)
	VALUES('Kralja Petra 110', 4);
	
--PRODAVCI
INSERT INTO prodavac(adresa, naziv_prodavca, email, posluje_od, korisnik_id)
	VALUES('Vojvodjanska 50', 'STR Dane od sljive do banane', 'pera@gmail.com', '2020-05-03', 5);
INSERT INTO prodavac(adresa, naziv_prodavca, email, posluje_od, korisnik_id)
	VALUES('Karadjordjeva 105', 'STR Zrmanja', 'zika@gmail.com', '2021-05-10', 6);
INSERT INTO prodavac(adresa, naziv_prodavca, email, posluje_od, korisnik_id)
	VALUES('Zlatiborska 3', 'STR Silobad', 'jova@gmail.com', '2019-08-12', 7);

--ARTIKLI
INSERT INTO artikal(naziv, opis, cena, putanja_slike, prodavac) VALUES('Hleb', 'Artikal za ishranu', 50, 'neka putanja', 1);
INSERT INTO artikal(naziv, opis, cena, putanja_slike, prodavac) VALUES('Banane', 'Voce', 120, 'neka putanja', 3);
INSERT INTO artikal(naziv, opis, cena, putanja_slike, prodavac) VALUES('Mleko', 'Artikal za ishranu', 100, 'neka putanja', 2);

--PORUDZBINE
INSERT INTO porudzbina(satnica, ocena, komentar, cena, dostavljeno, anoniman_komentar, arhiviran_komentar, kupac) VALUES('2020-05-03', 4, 'Nije los artikal, bio sam gladan hleb mi je prijao, brzo je i dostavljeno!', 200, true, true, true, 2);
INSERT INTO porudzbina(satnica, ocena, komentar, cena, dostavljeno, anoniman_komentar, arhiviran_komentar, kupac) VALUES('2021-10-05', 5, 'Odlican artikal i zaista brza dostava kada zelite banane!', 150, true, true, true, 3);
INSERT INTO porudzbina(satnica, ocena, komentar, cena, dostavljeno, anoniman_komentar, arhiviran_komentar, kupac) VALUES('2021-11-08', 2, 'Ne svidja mi se artikal, cini mi se da mu je istekao rok, ovo mleko treba baciti!', 120, true, false, true, 1);

--KOMENTARI
INSERT INTO komentar(tekst, ocena, prihvacen, arhiviran, artikal, kupac) VALUES('Odlican artikal i zaista brza dostava kada zelite banane!', 5, true, false, 2, 2);
INSERT INTO komentar(tekst, ocena, prihvacen, arhiviran, artikal, kupac) VALUES('Ne svidja mi se artikal, cini mi se da mu je istekao rok, ovo mleko treba baciti!', 2, true, false, 3, 1);
INSERT INTO komentar(tekst, ocena, prihvacen, arhiviran, artikal, kupac) VALUES('Nije los artikal, bio sam gladan hleb mi je prijao, brzo je i dostavljeno!', 4, true, false, 1, 3);