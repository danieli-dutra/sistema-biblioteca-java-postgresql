
package biblioteca;

public class Main {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca(10);

        // Cadastro dos itens
        Livro livro1 = new Livro(1, "O Senhor dos Aneis");
        Livro livro2 = new Livro(2, "Mentes Brilhantes Não Pensam Igual");
        Livro livro3 = new Livro(3, "O Mundo de Sofia");
        Revista revista1 = new Revista(4, "MIT Technology Review");
        Revista revista2 = new Revista(5, "Superinteressante");

        biblioteca.adicionarItem(livro1);
        biblioteca.adicionarItem(livro2);
        biblioteca.adicionarItem(livro3);
        biblioteca.adicionarItem(revista1);
        biblioteca.adicionarItem(revista2);

        // Cadastro dos usuarios
        Aluno aluno = new Aluno("Danieli");
        Professor professor = new Professor("Carlos");

        System.out.println("=== SISTEMA DE BIBLIOTECA ===");

        // Acervo inicial
        biblioteca.listarAcervo();

        // Emprestimos para o aluno
        System.out.println("\n=== TESTE DE EMPRESTIMOS ===");

        biblioteca.emprestar(livro1, aluno);
        biblioteca.emprestar(livro2, aluno);
        biblioteca.emprestar(livro3, aluno);

        // Tentativa acima do limite
        boolean resultado = biblioteca.emprestar(revista1, aluno);

        if (!resultado) {
            System.out.println(
                    "Emprestimo recusado: limite do aluno atingido."
            );
        }

        // Emprestimo para professor
        biblioteca.emprestar(revista1, professor);


        // Teste de devolucao
        System.out.println("\n=== TESTE DE DEVOLUCAO ===");

        // Tentativa de devolver item de outro usuario
        boolean devolucaoInvalida =
                biblioteca.devolver(revista1, aluno);

        if (!devolucaoInvalida) {
            System.out.println(
                    "Teste aprovado: devolucao indevida bloqueada."
            );
        }

        // Devolucao pelo usuario correto
        biblioteca.devolver(livro1, aluno);

        // Novo emprestimo apos devolucao
        biblioteca.emprestar(revista2, aluno);

        // Teste de multas
        System.out.println("\n=== TESTE DE MULTAS ===");

        System.out.printf(
                "Multa livro (3 dias): R$ %.2f%n",
                livro1.calcularMulta(3)
        );

        System.out.printf(
                "Multa revista (3 dias): R$ %.2f%n",
                revista1.calcularMulta(3)
        );


        // Teste do limite de emprestimos do professor
        System.out.println("\n=== TESTE DO LIMITE DO PROFESSOR ===");

        Professor professorTeste = new Professor("Professor Teste");

        for (int i = 1; i <= 5; i++) {
            boolean autorizado = professorTeste.registrarEmprestimo();

            System.out.println(
                    "Emprestimo " + i + ": "
                            + (autorizado ? "Autorizado" : "Recusado")
            );
        }

        boolean sextoEmprestimo = professorTeste.registrarEmprestimo();

        if (!sextoEmprestimo) {
            System.out.println(
                    "Teste aprovado: sexto emprestimo do professor recusado."
            );
        }

        // Acervo final
        biblioteca.listarAcervo();

        System.out.println("\n=== RESUMO ===");

        System.out.println(
                aluno.getNome() + ": "
                        + aluno.getQuantidadeEmprestada()
                        + " item(ns) emprestado(s)."
        );

        System.out.println(
                professor.getNome() + ": "
                        + professor.getQuantidadeEmprestada()
                        + " item(ns) emprestado(s)."
        );
    }
}