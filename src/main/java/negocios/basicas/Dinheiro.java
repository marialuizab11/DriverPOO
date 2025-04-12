package negocios.basicas;

import java.io.Serializable;

/**
 * Representa a forma de pagamento via dinheiro em especie no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class Dinheiro extends FormaDePagamento implements Serializable{

    private static final long serialVersionUID = 8097740048280755560L;

    /**
     * Construtor da classe Dinheiro
     * @param valor 
     */
    public Dinheiro(double valor) {
        super(valor);
    }
    
    /*Retorna o tipo de Forma De Pagamento*/
    public String getTipo() {
        return "Dinheiro";
    }

}