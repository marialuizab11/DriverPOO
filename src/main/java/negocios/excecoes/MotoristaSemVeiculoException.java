package negocios.excecoes;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class MotoristaSemVeiculoException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 9205532996622880181L;
    
    public MotoristaSemVeiculoException(String mensagem){
        super(mensagem); 
    }

}