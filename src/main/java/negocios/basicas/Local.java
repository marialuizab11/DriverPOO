package negocios.basicas;

import java.io.Serializable;

/**
 * @author Maria Luiza Bezerra
 */
public abstract class Local implements Serializable{

    private static final long serialVersionUID = 7660702858075527851L;
    
    private String nome;
    private int numero;
    private String cidade;
    private String estado;

    public Local(String nome, int numero, String cidade, String estado) {
        this.nome = nome;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
    
}