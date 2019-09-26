-- comentários
-- a linha abaixo cria um banco de dados
create database dbinfox;
-- a linha abaixo escolhe o banco de dados 
use dbinfox;
-- o bloco de instruções abaixo cria uma tabela
create table tbusuarios(
iduser int primary key, 
usuario varchar(50) not null,
cpf varchar(11) not null unique,
cep varchar(10) not null,
email varchar(50) not null, 
fone varchar(15) not null,
login varchar(15)not null unique,
senha varchar(15)not null
);
-- o comando abaixo descreve a tabela 
describe tbusuarios;
-- a linha abaixo insere dados na tabela (CRUD)
-- Creat -> insert 
insert into tbusuarios (iduser, usuario, cpf, cep, email, fone, login, senha)
values(1,'Edvaldo Filho','09627113492','58050590','edvaldorpg@gmail.com','996385300','EdyFilho','123456');
-- a linha abaixo exibe os dados da tabela (CRUD )
-- read -> select
select * from tbusuarios;

insert into tbusuarios (iduser, usuario, cpf, cep, email, fone, login, senha)
values(2,'Administrador','99999999999','58050590','adming@gmail.com','999999999','admin','god');
insert into tbusuarios (iduser, usuario, cpf, cep, email, fone, login, senha)
values(3,'Gezus','00000000000','58050590','gezus@gmail.com','996385300','gezus','gezus');

-- a linha abaixo modifica dados na tabela (GRUD)
-- update -> update
update tbusuarios set fone='8888888888' where iduser=2;

-- a linha abaixo apaga um registro da tabela (CRUD)
-- delete -> delete
delete from tbusuarios where iduser=3;

create table tbcidadao(
idcid int primary key auto_increment,
nomecid varchar(50) not null,
cpfcid varchar(11) not null unique,
cepcid varchar(10) not null,
emailcid varchar(50) not null, 
fonecid varchar(15) not null
);

describe tbcidadao;

insert into tbcidadao(nomecid, cpfcid, cepcid,emailcid,fonecid)
values ('Maria','00000000000','66666666','maria@gmail.com','22222222');

select * from tbcidadao;

use dbinfox;

create table tbocorrencia(
ocorrencia int primary key auto_increment,
data_ocorrencia timestamp default current_timestamp,
modo varchar(20) not null,
problema varchar(150) not null,
endereco varchar (50) not null,
referencia varchar (100) ,
detalhes varchar (200),
idcidadao int not null,
foreign key(idcidadao) references tbcidadao(idcid)
);

describe tbocorrencia;

insert into tbocorrencia (modo,problema,endereco,referencia,detalhe,idcidadao)
values ('Aberto','Buraco','Rua waldemar', 'do lado da lanchonete','o buraco ta grande',1);
select * from tbocorrencia;

-- o codigo abaixo traz informações de duas tabelas
select
O.ocorrencia,modo,problema,endereco,
C.nomecid,fonecid,emailcid
from tbocorrencia as O
inner join tbcidadao as C
on (O.idcidadao = C.idcid);