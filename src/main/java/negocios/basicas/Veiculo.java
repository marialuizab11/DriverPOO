package negocios.basicas;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public abstract class Veiculo implements Serializable {

    private static final long serialVersionUID = 7266152354283342286L;

    private String placa;
    private int capacidade;
    private String modelo;
    private Motorista motorista;
    private double taxaFixa;

    public Veiculo(String placa, int capacidade, String modelo, double taxaFixa) {
        this.placa = placa;
        this.capacidade = capacidade;
        this.modelo = modelo;
        this.taxaFixa = taxaFixa;
    }

    public double getTaxaFixa() {
        return taxaFixa;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public Motorista getMotorista() {
        return motorista;
    }
    
    
}