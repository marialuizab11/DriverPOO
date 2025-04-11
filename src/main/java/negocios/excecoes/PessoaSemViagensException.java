package negocios.excecoes;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class PessoaSemViagensException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8871821814222052856L;

    public PessoaSemViagensException(String mensagem){
        super(mensagem); 
    }

}