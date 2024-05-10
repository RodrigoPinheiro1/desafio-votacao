INSERT INTO associado (id, nome)
SELECT 1, 'fulano'
WHERE NOT EXISTS (SELECT 1 FROM associado WHERE id = 1);

-- Verifica se o registro com id 2 já existe antes de inserir
INSERT INTO associado (id, nome)
SELECT 2, 'fulano2'
WHERE NOT EXISTS (SELECT 1 FROM associado WHERE id = 2);