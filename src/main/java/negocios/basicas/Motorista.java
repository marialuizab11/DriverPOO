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
    private boolean validado;
    private boolean disponivel;
        
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
        this.validado = false;
        this.disponivel = true;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getCnh() {
        return cnh;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    
}