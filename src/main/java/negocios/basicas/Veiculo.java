package negocios.basicas;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public abstract class Veiculo implements Serializable {

    private static final long serialVersionUID = 7266152354283342286L;
    private static int proximoId = 1;
    private final int id;
    private String placa;
    private int capacidade;
    private String modelo;
    private int idMotorista;
    private double taxaFixa;

    public Veiculo(String placa, int capacidade, String modelo, double taxaFixa, int idMotorista) {
        this.placa = placa;
        this.capacidade = capacidade;
        this.modelo = modelo;
        this.taxaFixa = taxaFixa;
        this.setIdMotorista(idMotorista);
        this.id = proximoId++;
    }

    public int getId() {
        return id;
    }
    
    public int getIdMotorista() {
        return idMotorista;
    }

    private void setIdMotorista(int idMotorista) {
        this.idMotorista = idMotorista;
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
        
    public String getCategoria(){
        return "tipo de veiculo";
    }
}