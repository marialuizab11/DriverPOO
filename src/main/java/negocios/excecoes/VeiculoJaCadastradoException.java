package negocios.excecoes;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class VeiculoJaCadastradoException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1295540848523110541L;

    public VeiculoJaCadastradoException(String mensagem){
        super(mensagem); 
    }
}