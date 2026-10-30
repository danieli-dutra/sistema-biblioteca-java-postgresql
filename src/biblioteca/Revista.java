
package biblioteca;

public class Revista extends ItemBiblioteca {

    public Revista(int codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int getPrazoEmprestimo() {
        return 7;
    }

    @Override
    public double calcularMulta(int diasAtraso) {
        return Math.max(0, diasAtraso) * 1.00;
    }
}