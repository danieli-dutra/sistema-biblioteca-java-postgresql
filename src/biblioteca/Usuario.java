
package biblioteca;

public abstract class Usuario {

    private String nome;
    private int quantidadeEmprestada;

    public Usuario(String nome) {
        this.nome = nome;
        this.quantidadeEmprestada = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeEmprestada() {
        return quantidadeEmprestada;
    }

    public abstract int getLimiteItens();

    public boolean podeEmprestar() {
        return quantidadeEmprestada < getLimiteItens();
    }

    public boolean registrarEmprestimo() {
        if (!podeEmprestar()) {
            return false;
        }

        quantidadeEmprestada++;
        return true;
    }

    public void registrarDevolucao() {
        if (quantidadeEmprestada > 0) {
            quantidadeEmprestada--;
        }
    }
}