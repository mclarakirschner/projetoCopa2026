INSERT INTO TBL_TIME (id, nome, grupo, pontos)
VALUES (1,'Brasil','A',0);

INSERT INTO TBL_TIME (id, nome, grupo, pontos)
VALUES (2,'Argentina','A',0);

INSERT INTO TBL_TIME (id, nome, grupo, pontos)
VALUES (4,'Alemanha','B',0);

INSERT INTO TBL_PARTIDA
(time_casa_id, time_visitante_id, gols_casa, gols_visitante)
VALUES
(1,2,2,1);

-- adicionei para aparecer os nomes dos times no tbl_time
SELECT
    p.id,
    tc.nome AS time_casa,
    tv.nome AS time_visitante,
    p.gols_casa,
    p.gols_visitante
FROM TBL_PARTIDA p
INNER JOIN TBL_TIME tc
    ON p.time_casa_id = tc.id
INNER JOIN TBL_TIME tv
    ON p.time_visitante_id = tv.id;
