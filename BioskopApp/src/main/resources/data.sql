  
INSERT INTO ADMINISTRATOR (korisnicko_ime,lozinka,ime,prezime,uloga,aktivan) VALUES ('markus','333','Marko','Markovic','Administrator',true);

INSERT INTO MENADZER (korisnicko_ime,lozinka,ime,prezime,uloga,aktivan) VALUES ('nidzo','111','Nikola','Nikolic','Menadzer',true);
INSERT INTO MENADZER (korisnicko_ime,lozinka,ime,prezime,uloga,aktivan) VALUES ('milanko','123','Milanko','Milankovic','Menadzer',true);


INSERT INTO GLEDALAC (korisnicko_ime,lozinka,ime,prezime,email,uloga) VALUES ('andreja','andreja123','Anreja','Andrejevic','andreja@gmail.com','Gledalac');
INSERT INTO GLEDALAC (korisnicko_ime,lozinka,ime,prezime,email,uloga) VALUES ('milosm','12kp','Milos','Milosic','milos@gmail.com','Gledalac');
INSERT INTO GLEDALAC (korisnicko_ime,lozinka,ime,prezime,email,uloga) VALUES ('markic','aggsh','Mirko','Markic','markic@gmail.com','Gledalac');

INSERT INTO BIOSKOP (naziv,adresa,broj_centrale,menadzer_id) VALUES ('KVART','Cara Lazara','+381 21 2311 72',1);
INSERT INTO BIOSKOP (naziv,adresa,broj_centrale,menadzer_id) VALUES ('Cineplexx','Bulevar despota Stefana 7','+381 45 632 784',2);

INSERT INTO FILM(naziv,opis,zanr,trajanje,srednja_ocena) VALUES ('Hacksaw Ridge','Opis...','akcija',90,8);
INSERT INTO FILM(naziv,opis,zanr,trajanje,srednja_ocena) VALUES ('SAW7','Opis...','horor',90,9);
INSERT INTO FILM(naziv,opis,zanr,trajanje,srednja_ocena) VALUES ('P.S I Love You','Opis...','romantika',115,8.0);
INSERT INTO FILM(naziv,opis,zanr,trajanje,srednja_ocena) VALUES ('RUN','Opis...','triler',120,9);

INSERT INTO SALA(kapacitet,oznaka_sale,bioskop_id) VALUES (235,'A1',1);
INSERT INTO SALA(kapacitet,oznaka_sale,bioskop_id) VALUES (360,'A2',1);
INSERT INTO SALA(kapacitet,oznaka_sale,bioskop_id) VALUES (95,'A3',2);

INSERT INTO OCENA (film_id,ocena) VALUES (1,7);
INSERT INTO OCENA (film_id,ocena) VALUES (2,10);
INSERT INTO OCENA (film_id,ocena) VALUES (3,6);
INSERT INTO OCENA (film_id,ocena) VALUES (1,3);

INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (1,1);
INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (1,3);
INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (2,1);
INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (3,3);
INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (2,4);


INSERT INTO LISTA_OCENJENIH_FILMOVA (gledalac_id,ocena_id) VALUES (1,1);
INSERT INTO LISTA_OCENJENIH_FILMOVA (gledalac_id,ocena_id) VALUES (2,4);
INSERT INTO LISTA_OCENJENIH_FILMOVA (gledalac_id,ocena_id) VALUES (3,3);

INSERT INTO TERMINSKI_RASPORED (film_id,datum_odrzavanja,vreme_pocetka,cena,broj_rezervacija,sala_id) VALUES (1,'2020-05-03','18:00',280,50,1);
INSERT INTO TERMINSKI_RASPORED (film_id,datum_odrzavanja,vreme_pocetka,cena,broj_rezervacija,sala_id) VALUES (2,'2020-06-10','20:00',280,10,1);
INSERT INTO TERMINSKI_RASPORED (film_id,datum_odrzavanja,vreme_pocetka,cena,broj_rezervacija,sala_id) VALUES (3,'2020-05-15','21:30',250,85,2);
INSERT INTO TERMINSKI_RASPORED (film_id,datum_odrzavanja,vreme_pocetka,cena,broj_rezervacija,sala_id) VALUES (1,'2020-06-22','20:45',200,100,3);
INSERT INTO TERMINSKI_RASPORED (film_id,datum_odrzavanja,vreme_pocetka,cena,broj_rezervacija,sala_id) VALUES (2,'2020-06-22','15:45',200,70,3);
INSERT INTO TERMINSKI_RASPORED (film_id,datum_odrzavanja,vreme_pocetka,cena,broj_rezervacija,sala_id) VALUES (4,'2020-07-22','18:45',260,15,2);


INSERT INTO LISTA_REZERVISANIH_FILMOVA (gledalac_id,terminski_raspored_id) VALUES (1,2);
INSERT INTO LISTA_REZERVISANIH_FILMOVA (gledalac_id,terminski_raspored_id) VALUES (2,3);
INSERT INTO LISTA_REZERVISANIH_FILMOVA (gledalac_id,terminski_raspored_id) VALUES (3,1);

INSERT INTO RASPORED_FILMOVA (bioskop_id,terminski_raspored_id) VALUES (1,1);
INSERT INTO RASPORED_FILMOVA (bioskop_id,terminski_raspored_id) VALUES (1,2);
INSERT INTO RASPORED_FILMOVA (bioskop_id,terminski_raspored_id) VALUES (1,3);
INSERT INTO RASPORED_FILMOVA (bioskop_id,terminski_raspored_id) VALUES (2,4);
INSERT INTO RASPORED_FILMOVA (bioskop_id,terminski_raspored_id) VALUES (2,5);
INSERT INTO RASPORED_FILMOVA (bioskop_id,terminski_raspored_id) VALUES (1,6);







