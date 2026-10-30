
package biblioteca;

public class Biblioteca {

    private ItemBiblioteca[] acervo;
    private Usuario[] responsaveis;
    private int quantidadeItens;

    public Biblioteca(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException(
                    "A capacidade deve ser maior que zero."
            );
        }

        this.acervo = new ItemBiblioteca[capacidade];
        this.responsaveis = new Usuario[capacidade];
        this.quantidadeItens = 0;
    }

    public boolean adicionarItem(ItemBiblioteca item) {
        if (item == null || quantidadeItens >= acervo.length) {
            return false;
        }

        for (int i = 0; i < quantidadeItens; i++) {
            if (acervo[i].getCodigo() == item.getCodigo()) {
                return false;
            }
        }

        acervo[quantidadeItens] = item;
        quantidadeItens++;
        return true;
    }

    public boolean emprestar(ItemBiblioteca item, Usuario usuario) {
        if (item == null || usuario == null) {
            return false;
        }

        int indice = buscarIndice(item);

        if (indice == -1) {
            return false;
        }

        if (!item.isDisponivel() || !usuario.podeEmprestar()) {
            return false;
        }

        if (!item.emprestar()) {
            return false;
        }

        if (!usuario.registrarEmprestimo()) {
            item.devolver();
            return false;
        }

        responsaveis[indice] = usuario;

        System.out.println(
                "Emprestimo realizado: " + item.getTitulo()
                        + " para " + usuario.getNome()
        );

        return true;
    }

    public boolean devolver(ItemBiblioteca item, Usuario usuario) {
        if (item == null || usuario == null) {
            return false;
        }

        int indice = buscarIndice(item);

        if (indice == -1 || item.isDisponivel()) {
            return false;
        }

        if (responsaveis[indice] != usuario) {
            System.out.println(
                    "Devolucao recusada: usuario nao e "
                            + "responsavel pelo item."
            );
            return false;
        }

        if (usuario.getQuantidadeEmprestada() <= 0) {
            return false;
        }

        item.devolver();
        usuario.registrarDevolucao();
        responsaveis[indice] = null;

        System.out.println(
                "Devolucao realizada: " + item.getTitulo()
        );

        return true;
    }

    private int buscarIndice(ItemBiblioteca item) {
        for (int i = 0; i < quantidadeItens; i++) {
            if (acervo[i] == item) {
                return i;
            }
        }

        return -1;
    }

    public void listarAcervo() {
        System.out.println("\n=== ACERVO DA BIBLIOTECA ===");

        for (int i = 0; i < quantidadeItens; i++) {
            ItemBiblioteca item = acervo[i];

            System.out.println(
                    "Codigo: " + item.getCodigo()
                            + " | Titulo: " + item.getTitulo()
                            + " | Prazo: " + item.getPrazoEmprestimo()
                            + " dias | Disponivel: "
                            + (item.isDisponivel() ? "Sim" : "Nao")
            );
        }
    }
}