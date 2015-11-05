select * from pessoa;
insert into pessoa values (11,11,'11/11/1111 11:11:11',11);
insert into pessoa values (13,13,'13/12/1313 13:13:13',13);
insert into pessoa values ('00', '05/04/1954','francisca','feminino');
insert into pessoa values ('11', '26/05/1981','marcelo','masculino');
insert into pessoa values ('22', '30/11/1976','andrea','feminino');
insert into pessoa values ('33', '30/11/2016','Gunther','masculino');
insert into pessoa values ('44', '10/05/1980','marcia','feminino');

update pessoa set nome='marcelo' where id='11';

select * from pessoaFisica;
insert into pessoaFisica values ('11111111111','1111111',11,'11',true);
insert into pessoaFisica values ('11111111112','1111112',12,'12',false);
select * from recurso;
insert into recurso values ('ferramenta',11,11,11,11,11);
select * from usuario;
insert into usuario values ('11','11@11','11',11,11);