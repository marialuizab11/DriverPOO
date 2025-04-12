package negocios.excecoes;

import java.io.Serializable;

/**
 * Excecao lancada quando o tipo de veiculo escolhido para a corrida nao e ideal.
 * 
 * Exemplo: Motos nao podem fazer viagem de entrega cuja o peso total da entrega seja maior que 5kg
 * 
 * @author Maria Luiza Bezerra
 */
public class VeiculoNaoIdealException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8963258318228856764L;
    
    public VeiculoNaoIdealException(String mensagem){
        super(mensagem); 
    }

}