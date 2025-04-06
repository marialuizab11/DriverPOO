package negocios.basicas;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class ViagemPassageiro extends Viagem implements Serializable {

    private static final long serialVersionUID = 4797770253489019354L;
    
    public ViagemPassageiro(Origem origem, Destino destino, Motorista motorista, Veiculo veiculo) {
        super(origem, destino, motorista, veiculo);
    }

}