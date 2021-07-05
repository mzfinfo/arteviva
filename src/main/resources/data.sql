Insert Into Periodo(nome, 
                     inicio,
                     fim, 
                     descricao) 
Values('Música Antiga', '476','1400','Música antiga é a definição comum dada à música produzida na Europa da Idade Média até o Renascimento, por vezes abrangendo também parte do período Barroco.');


Insert Into Periodo(nome, 
                     inicio,
                     fim, 
                     descricao) 
Values('Renascentista', '1400','1600','Música renascentista refere-se à música européia escrita durante a Renascença, o período que abrangeu, aproximadamente, os anos de 1400 a 1600.');

Insert Into Periodo(nome, 
                     inicio,
                     fim, 
                     descricao) 
Values('Barroco', '1600','1750','Música barroca é toda música ocidental correlacionada com a época cultural homônima na Europa, que vai desde o surgimento da ópera por Claudio Monteverdi no século XVII, até a morte de Johann Sebastian Bach, em 1750');

Insert Into Periodo(nome, 
                     inicio,
                     fim, 
                     descricao) 
Values('Clássico', '1730','1820','O período clássico situa-se entre os períodos barroco e romântico. A música clássica tem uma textura mais clara do que a música barroca e é menos complexa. ');


Insert Into Periodo(nome, 
                     inicio,
                     fim, 
                     descricao) 
Values('Romântico', '1815','1910','A música do romantismo é aquela composta segundo os princípios da estética do romantismo, predominante durante o século XIX. Na história da música, corresponde ao período que seguiu ao classicismo.');


Insert Into Periodo(nome, 
                     inicio,
                     fim, 
                     descricao) 
Values('Contemporâneo', '1900','2021','Música contemporânea é a música dos séculos XX e XXI, feita após o movimento impressionista e os vários nacionalismos.');


Insert Into Usuario(nome, 
                    email, 
                    senha) 
Values('milton',  
       'mzfinfo.zf@gmail.com',
       '$2a$10$j.jdxqes7WILkVFCKgOhyuplXBugm.tshZpmQgIIuCrPRRRpI2vm6');

Insert Into Usuario(nome, 
                    email, 
                    senha) 
Values('sandra',  
       'sandra@gmail.com',
       '$2a$10$.jV1Xt3iwFL9TW4bAkI9GOpvpuLb8C8PlR2JysGFAysaHrtC3W59u');

Insert Into Usuario(nome, 
                    email, 
                    senha)
Values('gabriel',  
       'gabriel@gmail.com',
       '$2a$10$zK6GKTnEH3wCqMgkUpt/2emRkV6LhETresxHAql2F7pIRDDvZ.Rf6');
       
INSERT INTO PERFIL(id, nome) VALUES(1, 'ROLE_COMUM');
INSERT INTO PERFIL(id, nome) VALUES(2, 'ROLE_ADMIN');

INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(1, 2);
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(2, 1);
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(3, 1);

                    
