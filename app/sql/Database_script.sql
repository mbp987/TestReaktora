drop database if exists testreaktora;
create database testreaktora;
use testreaktora;


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
    prawidlowa_odp VARCHAR(5),
    czas TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

#select * from pytania;

CREATE TABLE wyniki (
    id_wynik INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(30),
    jezyk VARCHAR(10),
    liczba_pytan INT,
    wynik INT,
    czas TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (login)
        REFERENCES uzytkownicy (login)
)

#select * from wyniki;