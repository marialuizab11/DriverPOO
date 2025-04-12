package negocios.excecoes;

import java.io.Serializable;

/**
 * Excecao lancada quando uma pessoa (cliente ou motorista) nao tem viagens disponiveis e tenta ver as viagens que ele ja fez.
 * 
 * @author Maria Luiza Bezerra
 */
public class PessoaSemViagensException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8871821814222052856L;

    public PessoaSemViagensException(String mensagem){
        super(mensagem); 
    }

}