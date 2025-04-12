package negocios.basicas;

/**
 * Representa uma classe generica de forma de pagamento no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public abstract class FormaDePagamento {
    private double valor;

    public FormaDePagamento(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }   
}