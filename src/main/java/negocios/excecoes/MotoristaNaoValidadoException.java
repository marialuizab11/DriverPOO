package negocios.excecoes;

import java.io.Serializable;

/**
 * Excecao lancada quando um motorista tenta iniciar uma viagem e ainda nao fez a validacao no sistema.
 * @author Maria Luiza Bezerra
 */
public class MotoristaNaoValidadoException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 3681832332876452281L;
    
    public MotoristaNaoValidadoException(String mensagem){
        super(mensagem); 
    }
}