package negocios.basicas;

import java.io.Serializable;

/**
 * Representa uma forma de pagamento via cartao de credito no sistema
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
     * Construtor da classe CartaoCredito
     * 
     * @param nomeTitular nome do titular 
     * @param numero numero completo 
     * @param cvv codigo de seguranca
     * @param dataVencimento data de vencimento
     * @param limite limite total
     * @param valor valor que sera cobrado da corrida
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
    
    public String getTipo(){
        return "Cartao de Credito";
    }
}