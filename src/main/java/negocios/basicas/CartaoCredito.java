package negocios.basicas;

import java.io.Serializable;

/**
 * Representa uma forma de pagamento via cartao de credito no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class CartaoCredito extends FormaDePagamento implements Serializable {

    private static final long serialVersionUID = 9106928881515115231L;
    
    private String nomeTitular;
    private String numero;
    private int cvv;
    private String dataVencimento;
    private double limite;

    /**
     * Cria um novo cartao de credito no sistema.
     * 
     * @param nomeTitular
     * @param numero
     * @param cvv 
     * @param dataVencimento
     * @param limite 
     * @param valor 
     */
    public CartaoCredito(String nomeTitular, String numero, int cvv, String dataVencimento, double limite, double valor) {
        super(valor);
        this.nomeTitular = nomeTitular;
        this.numero = numero;
        this.cvv = cvv;
        this.dataVencimento = dataVencimento;
        this.limite = limite;
    }

    public String getNumero() {
        return numero;
    }

    public double getLimite() {
        return limite;
    }
    
    /*Retorna o tipo de Forma de Pagamento*/
    public String getTipo(){
        return "Cartao de Credito";
    }
}