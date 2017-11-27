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

#select * from pytania;

CREATE TABLE wyniki (
    id_wynik INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(30),
    jezyk VARCHAR(10),
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
insert into uzytkownicy(login, haslo, imie, nazwisko, grupa, rola) values ('aaa', 'aaa', 'Aaa', 'Aaa', 'W1-2017', 'user');
insert into uzytkownicy(login, haslo, imie, nazwisko, grupa, rola) values ('a', 'a', 'Aaa', 'Aaa', NULL , 'admin');

insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Python', 'Jaki jest wynik kodu: print(int("22") + int("22")) ?', '22', '44', '444', '2222', '44');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Python', 'Wzkaż krotkę:', '[a,b,c]', '(a,b,c)', '{a,b,c}', "{'a','b','c'}", '(a,b,c)');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Python', 'Jaki jest wynik kodu: int("3"+"4"):', '"7"', '7', '"34"', '34', '34');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Python', 'Jaki jest wynik kodu: float("210" * int(input("Enter a number:")))/nEnter a number: 2', '420', '"210210"', '210210.0', '"420.0"', '210210.0');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Python', 'Jaki jest wynik kodu:/n spam = "eggs"/nprint(spam * 3) ?', 'spamspamspam', '"spamspamspam"', 'eggseggseggs', '"eggseggseggs"', 'eggseggseggs');


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

insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Bazy', 'Treść pytania numer 1', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Bazy', 'Treść pytania numer 2', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Bazy', 'Treść pytania numer 3', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Bazy', 'Treść pytania numer 4', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Bazy', 'Treść pytania numer 5', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Bazy', 'Treść pytania numer 6', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Bazy', 'Treść pytania numer 7', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Bazy', 'Treść pytania numer 8', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');

insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Front', 'Treść pytania numer 1', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Front', 'Treść pytania numer 2', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'trzecia');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Front', 'Treść pytania numer 3', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'czwarta');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Front', 'Treść pytania numer 4', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Front', 'Treść pytania numer 5', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Front', 'Treść pytania numer 6', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Front', 'Treść pytania numer 7', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'druga');
insert into pytania(jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) values ('Front', 'Treść pytania numer 8', 'pierwsza', 'druga', 'trzecia', 'czwarta ', 'pierwsza');