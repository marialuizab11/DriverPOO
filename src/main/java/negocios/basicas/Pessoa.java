package negocios.basicas;

import java.io.Serializable;

/**
 * Representa uma pessoa no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = -1670114453198280995L;

    private static int proximoId = 1;
    private final int id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    /**
     * Construtor da classe Pessoa
     * @param nome nome completo
     * @param email email para contato  
     * @param telefone telefone
     * @param cpf cpf sem caracteres
     */
    public Pessoa(String nome, String email, String telefone, String cpf) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.id = proximoId++;
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
}