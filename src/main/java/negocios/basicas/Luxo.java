package negocios.basicas;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class Luxo extends Veiculo implements Serializable{

    private static final long serialVersionUID = 2506375002821295536L;

    public Luxo(String placa, int capacidade, String modelo, double taxaFixa) {
        super(placa, capacidade, modelo, 0.7);
    }

    public String getCategoria(){
        return "Luxo";
    }
}