package negocios.basicas;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public abstract class Viagem implements Serializable {
    
    private static final long serialVersionUID = 1842033359528886575L;
    
    private static int proximoId = 1;
    private final int id;
    private Origem origem;
    private Destino destino;
    private Motorista motorista;
    private Veiculo veiculo;

    public Viagem(Origem origem, Destino destino, Motorista motorista, Veiculo veiculo) {
        this.origem = origem;
        this.destino = destino;
        this.motorista = motorista;
        this.veiculo = veiculo;
        this.id = proximoId++;
    }

    public int getId() {
        return id;
    }

    public Origem getOrigem() {
        return origem;
    }

    public Destino getDestino() {
        return destino;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    
    
}