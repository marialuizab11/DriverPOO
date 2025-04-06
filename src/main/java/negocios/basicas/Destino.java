package negocios.basicas;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public class Destino extends Local implements Serializable{

    private static final long serialVersionUID = 8154835401067621888L;

    public Destino(String nome, int numero, String cidade, String estado) {
        super(nome, numero, cidade, estado);
    }

}