package negocios.basicas;

import java.io.Serializable;

/**
 * Representa um veiculo do tipo "Carro de Luxo" no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class Luxo extends Veiculo implements Serializable{

    private static final long serialVersionUID = 2506375002821295536L;

    public Luxo(String placa, int capacidade, String modelo, int idMotorista) {
        super(placa, capacidade, modelo, 0.7, idMotorista);
    }

    /*Retorna a categoria do veiculo*/
    @Override
    public String getCategoria(){
        return "Luxo";
    }
}