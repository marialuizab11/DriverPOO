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
    private Cliente cliente;
    private boolean aceita;
    private String categoriaVeiculo;
    private double valorTotal;

    public Viagem(Origem origem, Destino destino, Motorista motorista, Veiculo veiculo, Cliente cliente, String categoria, double valorTotal) {
        this.origem = origem;
        this.destino = destino;
        this.motorista = motorista;
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.aceita = false;
        this.categoriaVeiculo = categoria;
        this.valorTotal = valorTotal;
        this.id = proximoId++;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getCategoriaVeiculo() {
        return categoriaVeiculo;
    }

    public void setCategoriaVeiculo(String categoriaVeiculo) {
        this.categoriaVeiculo = categoriaVeiculo;
    }    
    
    public boolean isAceita(){
        return aceita;
    }

    public void setAceita(boolean aceita){
        this.aceita = aceita;
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

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    
    
}