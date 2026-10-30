
package biblioteca;

public abstract class ItemBiblioteca {

    private int codigo;
    private String titulo;
    private boolean disponivel;

    public ItemBiblioteca(int codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.disponivel = true;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public abstract int getPrazoEmprestimo();

    public abstract double calcularMulta(int diasAtraso);

    boolean emprestar() {
        if (!disponivel) {
            return false;
        }

        disponivel = false;
        return true;
    }

    void devolver() {
        disponivel = true;
    }
}