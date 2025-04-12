package negocios.excecoes;

import java.io.Serializable;

/**
 * Excecao lancada quando uma pessoa (cliente ou motorista) nao for encontrada no sistema, ou seja, ela nao esta cadastrada no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class PessoaNaoEncontradaException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 4459976929158318699L;
    
    public PessoaNaoEncontradaException(String mensagem){
        super(mensagem); 
    }
}