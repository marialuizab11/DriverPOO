package negocios.excecoes;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class MotoristaNaoDisponivelException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -8653337321874844506L;

    public MotoristaNaoDisponivelException(String mensagem){
        super(mensagem); 
    }
}