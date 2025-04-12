package negocios.basicas;

import java.io.Serializable;

/**
 * Representa um local que sera a origem da viagem.
 * 
 * @author Maria Luiza Bezerra
 */
public class Origem extends Local implements Serializable {

    private static final long serialVersionUID = -4605097207754933956L;

    public Origem(String nome, int numero) {
        super(nome, numero);
    }

}