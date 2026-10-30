
-- Exercício 2 - Inserção de dados
-- Sistema de Biblioteca
-- Execute após 01_criar_tabelas.sql

-- Cadastro dos itens
INSERT INTO item (codigo, titulo, tipo, autor, edicao)
VALUES
    (1, 'O Senhor dos Anéis', 'Livro', 'J. R. R. Tolkien', '1ª edição'),
    (2, 'Mentes Brilhantes Não Pensam Igual', 'Livro', 'Marcelo Gleiser', '2ª edição'),
    (3, 'O Mundo de Sofia', 'Livro', 'Jostein Gaarder', '1ª edição'),
    (4, 'MIT Technology Review', 'Revista', NULL, 'Edição 2026'),
    (5, 'Superinteressante', 'Revista', NULL, 'Edição 2026');

-- Cadastro dos usuários
INSERT INTO usuario (nome, tipo, limite_itens)
VALUES
    ('Danieli', 'Aluno', 3),
    ('Carlos', 'Professor', 5);

-- Empréstimo em aberto: Danieli
INSERT INTO emprestimo (
    item_id,
    usuario_id,
    data_retirada,
    data_devolucao_prevista,
    data_devolucao,
    valor_multa
)
SELECT
    i.id,
    u.id,
    DATE '2026-10-20',
    DATE '2026-11-03',
    NULL,
    0.00
FROM item i
         CROSS JOIN usuario u
WHERE i.codigo = 2
  AND u.nome = 'Danieli'
  AND u.tipo = 'Aluno';

-- Empréstimo devolvido com multa: Carlos
INSERT INTO emprestimo (
    item_id,
    usuario_id,
    data_retirada,
    data_devolucao_prevista,
    data_devolucao,
    valor_multa
)
SELECT
    i.id,
    u.id,
    DATE '2026-10-10',
    DATE '2026-10-17',
    DATE '2026-10-20',
    3.00
FROM item i
         CROSS JOIN usuario u
WHERE i.codigo = 4
  AND u.nome = 'Carlos'
  AND u.tipo = 'Professor';

-- Atualização da disponibilidade do item emprestado
UPDATE item
SET disponivel = FALSE
WHERE codigo = 2;
