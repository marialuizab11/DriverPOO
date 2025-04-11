package negocios.basicas;

import java.io.Serializable;

/**
 * Representa a forma de pagamento via pix no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class Pix extends FormaDePagamento implements Serializable {

    private static final long serialVersionUID = -8336523857044209616L;
    /**
     * Construtor da classe Pix
     * @param valor valor a ser cobrado da corrida
     * a chave pix é gerada automaticamente
     */
    public Pix(double valor) {
        super(valor);
    }

    public String getTipo(){
        return "Pix";
    }
}