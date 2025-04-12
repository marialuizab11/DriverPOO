package negocios.basicas;

import java.io.Serializable;

/**
 * Representa uma avaliacao no sistema, contendo uma classificacao por estrelas e uma descricao textual.
 * 
 * @author Maria Luiza Bezerra
 */
public class Avaliacao implements Serializable{

    private static final long serialVersionUID = 3559304113583409971L;
    private String descricao;
    private int estrelas;

    /**
     * Cria uma nova avaliacao com descricao e classificacao por estrelas.
     * 
     * @param descricao
     * @param estrelas 
     */
    public Avaliacao(String descricao, int estrelas) {
        this.descricao = descricao;
        this.estrelas = estrelas;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getEstrelas() {
        return estrelas;
    }
    
    
}