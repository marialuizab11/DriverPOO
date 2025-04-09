package negocios.excecoes;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class PessoaNaoEncontradaException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 4459976929158318699L;
    
    public PessoaNaoEncontradaException(String mensagem){
        super(mensagem); 
    }
}