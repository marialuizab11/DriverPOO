package negocios.excecoes;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class MotoristaNaoDisponivelException extends RuntimeException implements Serializable{

    private static final long serialVersionUID = -8512292741180425419L;

    public MotoristaNaoDisponivelException(String mensagem){
        super(mensagem); 
    }
}