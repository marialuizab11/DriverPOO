package negocios.excecoes;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class VeiculoNaoExisteException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7491113383494973426L;

    public VeiculoNaoExisteException(String mensagem){
        super(mensagem); 
    }
}