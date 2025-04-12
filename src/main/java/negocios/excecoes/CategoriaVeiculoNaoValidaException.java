package negocios.excecoes;

import java.io.Serializable;

/**
 * Excecao lancada quando uma categoria de veiculo invalida eh fornecida no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class CategoriaVeiculoNaoValidaException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5182150584269438479L;

    public CategoriaVeiculoNaoValidaException(String mensagem){
        super(mensagem); 
    }
}