package negocios.basicas;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class Economico extends Veiculo implements Serializable{

    private static final long serialVersionUID = -8843112505190397660L;

    public Economico(String placa, int capacidade, String modelo) {
        super(placa, capacidade, modelo, 0.30);
    }

    public String getCategoria(){
        return "Economica";
    }
}