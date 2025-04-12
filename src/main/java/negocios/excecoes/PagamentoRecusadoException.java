package negocios.excecoes;

import java.io.Serializable;

/**
 * Excecao lancada quando o pagamento da viagem nao foi aceita.
 * 
 * @author Maria Luiza Bezerra
 */
public class PagamentoRecusadoException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 7107240608240388762L;

    public PagamentoRecusadoException(String mensagem){
        super(mensagem); 
    }
}