package negocios.excecoes;

import java.io.Serializable;

/**
 * Excecao lancada quando um veiculo nao foi encontrado no sistema, ou seja, nao foi cadastrado.
 * 
 * @author Maria Luiza Bezerra
 */
public class VeiculoNaoExisteException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7491113383494973426L;

    public VeiculoNaoExisteException(String mensagem){
        super(mensagem); 
    }
}