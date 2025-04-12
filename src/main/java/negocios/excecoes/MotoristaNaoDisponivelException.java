package negocios.excecoes;

import java.io.Serializable;

/**
 * Excecao lancada quando um motorista ja esta em uma viagem, ou seja, nao esta disponivel.
 * 
 * @author Maria Luiza Bezerra
 */
public class MotoristaNaoDisponivelException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -8653337321874844506L;

    public MotoristaNaoDisponivelException(String mensagem){
        super(mensagem); 
    }
}