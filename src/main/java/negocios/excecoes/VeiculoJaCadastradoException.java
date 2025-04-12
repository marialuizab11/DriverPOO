package negocios.excecoes;

import java.io.Serializable;

/**
 * Excecao lancada quando um motorista tenta cadastrar um veiculo que ja esta cadastrado no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class VeiculoJaCadastradoException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1295540848523110541L;

    public VeiculoJaCadastradoException(String mensagem){
        super(mensagem); 
    }
}