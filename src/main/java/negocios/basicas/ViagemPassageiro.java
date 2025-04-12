package negocios.basicas;

import java.io.Serializable;

/**
 * Representa uma viagem de corrida no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class ViagemPassageiro extends Viagem implements Serializable {

    private static final long serialVersionUID = 4797770253489019354L;
    
    public ViagemPassageiro(Origem origem, Destino destino, Motorista motorista, Cliente cliente, Veiculo veiculo, String categoria, double valorTotal) {
        super(origem, destino, motorista, veiculo, cliente, categoria, valorTotal);
    }

}