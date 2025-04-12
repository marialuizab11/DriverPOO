package negocios.basicas;

import java.io.Serializable;

/**
 * Representa um veiculo do tipo "Carro Economico".
 * 
 * @author Maria Luiza Bezerra
 */
public class Economico extends Veiculo implements Serializable{

    private static final long serialVersionUID = -8843112505190397660L;

    /**
     * Cria um novo carro economico.
     * A taxaFixa de carro economico é 0.3.
     * @param placa
     * @param capacidade
     * @param modelo
     * @param idMotorista 
     */
    public Economico(String placa, int capacidade, String modelo, int idMotorista) {
        super(placa, capacidade, modelo, 0.30, idMotorista);
    }
    
    /*Retorna o tipo de veiculo*/
    @Override
    public String getCategoria(){
        return "Economica";
    }
}