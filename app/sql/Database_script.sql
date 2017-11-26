drop user if exists usertestreaktora;
create user usertestreaktora identified by 'strongPasswordWouldBeNice';
grant select, insert, delete, update on testreaktora.* to 'usertestreaktora'@'localhost' identified by 'strongPasswordWouldBeNice';