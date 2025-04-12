package negocios.basicas;

import java.io.Serializable;

/**
 * Representa uma forma generica de local no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public abstract class Local implements Serializable{

    private static final long serialVersionUID = 7660702858075527851L;
    
    private String nome;
    private int numero;
    private String cidade;
    private String estado;

    public Local(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }
    
}