use dbinfox;
describe tbusuarios;
select * from tbusuarios;
-- a linha abaixo adiciona um campo a tabela
alter table tbusuarios add column perfil varchar(20) not null;
-- a linha abaixo remove um campo de uma tabela
-- alter table tbusuarios drop column perfil;
update tbusuarios set perfil =  'admin' where iduser = 1;
update tbusuarios set perfil =  'user' where iduser = 2;