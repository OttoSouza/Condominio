INSERT INTO TB_CONDOMINIO (codigo, nome, email, telefone)
VALUES ('1', 'Residencial xyz', 'residencialxyz@gmail.com', '99988575');

INSERT INTO TB_RESPONSAVEL (id, nome, email, telefone)
VALUES ('2', 'Paula', 'paulaz@gmail.com', '99988575');

INSERT INTO TB_UNIDADE (id, bloco_unidade, numero_unidade, id_condominio, id_responsavel)
VALUES ('3', 'A', '100', '1', '2');

INSERT INTO TB_MULTAs (id, descricao_multa, data_multa, valor_multa, id_condominio, id_unidade)
VALUES ('4', 'cachorro latindo', '2019-04-21', '200.00', '1', '3');

