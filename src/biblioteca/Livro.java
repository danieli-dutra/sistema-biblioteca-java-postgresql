
package biblioteca;

public class Livro extends ItemBiblioteca {

    public Livro(int codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int getPrazoEmprestimo() {
        return 14;
    }

    @Override
    public double calcularMulta(int diasAtraso) {
        return Math.max(0, diasAtraso) * 0.50;
    }
}