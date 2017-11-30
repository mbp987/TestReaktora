drop database if exists testreaktora;
create database testreaktora DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use testreaktora;
#drop database testreaktora;

drop user if exists usertestreaktora;
create user usertestreaktora identified by 'strongPasswordWouldBeNice';
grant select, insert, delete, update on testreaktora.* to 'usertestreaktora'@'localhost' identified by 'strongPasswordWouldBeNice';



CREATE TABLE uzytkownicy (
    login VARCHAR(30) PRIMARY KEY,
    haslo VARCHAR(20),
    imie VARCHAR(20),
    nazwisko VARCHAR(30),
    grupa VARCHAR(10),
    rola VARCHAR(10),
    czas TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

#select * from uzytkownicy;

CREATE TABLE pytania (
    id_pytania INT AUTO_INCREMENT PRIMARY KEY,
    jezyk VARCHAR(10),
    tresc TEXT,
    odp1 TEXT,
    odp2 TEXT,
    odp3 TEXT,
    odp4 TEXT,
    prawidlowa_odp TEXT,
    czas TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

select * from pytania;

CREATE TABLE wyniki (
    id_wynik INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(30),
    jezyk VARCHAR(30),
    liczba_pytan INT,
    wynik FLOAT,
    czas TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (login)
        REFERENCES uzytkownicy (login)
);

#select * from wyniki;

insert into uzytkownicy(login, haslo, imie, nazwisko, grupa, rola) values ('admin@pwn.pl', 'admin123', 'Bożena', 'Jamka-Czyż', NULL, 'admin');
insert into uzytkownicy(login, haslo, imie, nazwisko, grupa, rola) values ('a.adamska@wp.pl', 'adamska', 'Anna', 'Adamska', 'D2-2017', 'user');
insert into uzytkownicy(login, haslo, imie, nazwisko, grupa, rola) values ('b.baranski@wp.pl', 'baranski', 'Bartosz', 'Baranski', 'W1-2017', 'user');
insert into uzytkownicy(login, haslo, imie, nazwisko, grupa, rola) values ('user', 'user', 'Henryk', 'Użytkownik', 'W1-2017', 'user');
insert into uzytkownicy(login, haslo, imie, nazwisko, grupa, rola) values ('admin', 'admin', 'Jan', 'Administrator', NULL , 'admin');

insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Java', 'Treść pytania numer 1', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Java', 'Treść pytania numer 2', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Java', 'Treść pytania numer 3', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Java', 'Treść pytania numer 4', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Java', 'Treść pytania numer 5', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Java', 'Treść pytania numer 6', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Java', 'Treść pytania numer 7', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Java', 'Treść pytania numer 8', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');

insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Spring', 'Treść pytania numer 1', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Spring', 'Treść pytania numer 2', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Spring', 'Treść pytania numer 3', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Spring', 'Treść pytania numer 4', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Spring', 'Treść pytania numer 5', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Spring', 'Treść pytania numer 6', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Spring', 'Treść pytania numer 7', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Spring', 'Treść pytania numer 8', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');

insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Git', 'Treść pytania numer 1', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Git', 'Treść pytania numer 2', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Git', 'Treść pytania numer 3', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Git', 'Treść pytania numer 4', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Git', 'Treść pytania numer 5', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Git', 'Treść pytania numer 6', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Git', 'Treść pytania numer 7', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Git', 'Treść pytania numer 8', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');


insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 1', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 2', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 3', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 4', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 5', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 6', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 7', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 8', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');

insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 1', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 2', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 3', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 4', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 5', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 6', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 7', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 8', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');

insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 1', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 2', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 3', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 4', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 5', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 6', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 7', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('BD', 'Treść pytania numer 8', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');

insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 1', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 2', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 3', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 4', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 5', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 6', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 7', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('FE', 'Treść pytania numer 8', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');

insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('a.adamska@wp.pl', 'Python', 20, 0.75);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('a.adamska@wp.pl', 'Python', 30, 0.85);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('a.adamska@wp.pl', 'Python', 15, 0.95);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('a.adamska@wp.pl', 'Java', 20, 0.5);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('a.adamska@wp.pl', 'Java', 30, 0.6);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('a.adamska@wp.pl', 'Java', 15, 0.7);

insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'Python', 20, 0.75);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'Python', 30, 0.85);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'Python', 15, 0.95);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'Java', 20, 0.5);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'Java', 30, 0.6);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'Java', 15, 0.7);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'FE', 20, 0.5);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'FE', 30, 0.4);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'FE', 10, 0.2);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'Git', 15, 0.9);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'Git', 30, 0.9);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'Git', 25, 0.75);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'Spring', 40, 0.3);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'Spring', 10, 0.5);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'Spring', 15, 0.65);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'BD', 20, 0.3);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'BD', 20, 0.5);
insert into wyniki (login, jezyk, liczba_pytan, wynik) values ('b.baranski@wp.pl', 'BD', 15, 0.65);

