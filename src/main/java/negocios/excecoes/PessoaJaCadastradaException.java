package negocios.excecoes;

import java.io.Serializable;

/**
 * Excecao lancada quando uma pessoa (cliente ou motorista) tentar se cadastrar novamente no sistema, mesmo ja tendo uma conta aberta.
 * 
 * @author Maria Luiza Bezerra
 */
public class PessoaJaCadastradaException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7130998117242808374L;

    public PessoaJaCadastradaException(String mensagem){
        super(mensagem); 
    }
}