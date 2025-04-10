package negocios.excecoes;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class MotoristaNaoValidadoException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 3681832332876452281L;
    
    public MotoristaNaoValidadoException(String mensagem){
        super(mensagem); 
    }
}