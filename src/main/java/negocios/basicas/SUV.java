package negocios.basicas;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class SUV extends Veiculo implements Serializable{

    private static final long serialVersionUID = 7774065859785739947L;

    public SUV(String placa, int capacidade, String modelo) {
        super(placa, capacidade, modelo, 0.4);
    }
    
    public String getCategoria(){
        return "SUV";
    }
}