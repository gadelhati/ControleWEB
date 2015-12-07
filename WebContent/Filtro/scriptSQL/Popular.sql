select * from pessoa;
insert into pessoa values (11,'11/11/1111 11:11:11',11);
insert into pessoa values (12,'12/12/1212 12:12:12',12);
insert into pessoa values (13,'13/01/1313 13:13:13',13);
select * from pessoaFisica;
insert into pessoaFisica values ('21', '05/04/1954','francisca','feminino');
insert into pessoaFisica values ('22', '26/05/1981','marcelo','masculino');
insert into pessoaFisica values ('23', '30/11/1976','andrea','feminino');
insert into pessoaFisica values ('24', '30/11/2016','Gunther','masculino');
insert into pessoaFisica values ('25', '10/05/1980','marcia','feminino');

update pessoa set nome='marcelo' where id='11';

select * from pessoaFisica;
insert into pessoaFisica values ('11111111111','1111111',11,'11',true);
insert into pessoaFisica values ('11111111112','1111112',12,'12',false);
select * from recurso;
insert into recurso values ('ferramenta',11,11,11,11,11);
select * from usuario;
insert into usuario values ('11','11@11','11',11,11);