package negocios.excecoes;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class CategoriaVeiculoNaoValidaException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5182150584269438479L;

    public CategoriaVeiculoNaoValidaException(String mensagem){
        super(mensagem); 
    }
}