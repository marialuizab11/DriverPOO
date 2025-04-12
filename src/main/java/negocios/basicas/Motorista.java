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
    private int idVeiculo;
    private boolean validado;
    private boolean disponivel;
        
    /**
     * Cria um novo motorista.
     * O motorista é criado como nao valido e disponivel (nao esta fazendo viagem).
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

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
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