package negocios.excecoes;

import java.io.Serializable;

/**
 * Excecao lancada quando a viagem nao foi encontrada no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class ViagemNaoEncontradaException extends RuntimeException implements Serializable{

    private static final long serialVersionUID = -1681439057051184474L;

    public ViagemNaoEncontradaException(String mensagem){
        super(mensagem); 
    }
}