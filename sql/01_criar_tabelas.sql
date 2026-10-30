
-- Exercício 2 - Modelagem Relacional
-- Sistema de Biblioteca
-- PostgreSQL 18

-- Tabela de itens do acervo
CREATE TABLE item (
                      id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      codigo INTEGER NOT NULL UNIQUE,
                      titulo VARCHAR(200) NOT NULL,
                      tipo VARCHAR(20) NOT NULL
                          CHECK (tipo IN ('Livro', 'Revista')),
                      autor VARCHAR(150),
                      edicao VARCHAR(50),
                      disponivel BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabela de usuários
CREATE TABLE usuario (
                         id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         nome VARCHAR(150) NOT NULL,
                         tipo VARCHAR(20) NOT NULL
                             CHECK (tipo IN ('Aluno', 'Professor')),
                         limite_itens INTEGER NOT NULL
                             CHECK (limite_itens > 0)
);

-- Tabela de empréstimos
CREATE TABLE emprestimo (
                            id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            item_id INTEGER NOT NULL,
                            usuario_id INTEGER NOT NULL,
                            data_retirada DATE NOT NULL,
                            data_devolucao_prevista DATE NOT NULL,
                            data_devolucao DATE,
                            valor_multa NUMERIC(10, 2) NOT NULL DEFAULT 0
                                CHECK (valor_multa >= 0),

                            CONSTRAINT fk_emprestimo_item
                                FOREIGN KEY (item_id)
                                    REFERENCES item(id),

                            CONSTRAINT fk_emprestimo_usuario
                                FOREIGN KEY (usuario_id)
                                    REFERENCES usuario(id),

                            CONSTRAINT chk_data_prevista
                                CHECK (data_devolucao_prevista >= data_retirada),

                            CONSTRAINT chk_data_devolucao
                                CHECK (
                                    data_devolucao IS NULL
                                        OR data_devolucao >= data_retirada
                                    )
);