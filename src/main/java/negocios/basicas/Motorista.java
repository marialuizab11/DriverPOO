package negocios.basicas;

import java.io.Serializable;

/**
 * Representa uma pessoa que sera um motorista no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class Motorista extends Pessoa implements Serializable{

    private static final long serialVersionUID = 1675543234322004727L;
    private String cnh;
    private Veiculo veiculo;
        
    /**
     * Construtor da classe motorista
     * @param nome
     * @param email
     * @param telefone
     * @param cpf
     * @param cnh 
     */
    public Motorista(String nome, String email, String telefone, String cpf, String cnh) {
        super(nome, email, telefone, cpf);
        this.cnh = cnh;
    }

    public String getCnh() {
        return cnh;
    }
    
}