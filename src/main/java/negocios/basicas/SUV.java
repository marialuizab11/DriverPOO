package negocios.basicas;

import java.io.Serializable;

/**
 * Representa um veiculo do tipo "Carro SUV" no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class SUV extends Veiculo implements Serializable{

    private static final long serialVersionUID = 7774065859785739947L;

    public SUV(String placa, int capacidade, String modelo, int idMotorista) {
        super(placa, capacidade, modelo, 0.4, idMotorista);
    }
    
    /*Retorna a categoria do veiculo*/
    @Override
    public String getCategoria(){
        return "SUV";
    }
}