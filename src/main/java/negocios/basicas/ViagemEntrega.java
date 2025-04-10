package negocios.basicas;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class ViagemEntrega extends Viagem implements Serializable {

    private static final long serialVersionUID = -8625290749984901569L;
    
    private double pesoPacoteKg;
    
    public ViagemEntrega(Origem origem, Destino destino, Motorista motorista, Veiculo veiculo, String categoria, double valorTotal, double pesoPacoteKg) {
        super(origem, destino, motorista, veiculo, categoria, valorTotal);
        this.pesoPacoteKg = pesoPacoteKg;
    }

    public double getPesoPacoteKg() {
        return pesoPacoteKg;
    }
}