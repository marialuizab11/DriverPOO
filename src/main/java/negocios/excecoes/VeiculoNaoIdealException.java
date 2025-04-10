package negocios.excecoes;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class VeiculoNaoIdealException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8963258318228856764L;
    
    public VeiculoNaoIdealException(String mensagem){
        super(mensagem); 
    }

}