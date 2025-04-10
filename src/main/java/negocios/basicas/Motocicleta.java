package negocios.basicas;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class Motocicleta extends Veiculo implements Serializable {

    private static final long serialVersionUID = -7856207561984358984L;

    public Motocicleta(String placa, int capacidade, String modelo, int idMotorista) {
        super(placa, capacidade, modelo, 0.15, idMotorista);
    }

    @Override
    public String getCategoria(){
        return "Motocicleta";
    }
    
}