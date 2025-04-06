package negocios.basicas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma pessoa que sera um cliente no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class Cliente extends Pessoa implements Serializable {

    private static final long serialVersionUID = -5632156220270537037L;
    
    private List<FormaDePagamento> formasPagamento;
    
    /**
     * Construtor da classe Cliente
     * @param nome 
     * @param email
     * @param telefone
     * @param cpf 
     */
    public Cliente(String nome, String email, String telefone, String cpf) {
        super(nome, email, telefone, cpf);
        this.formasPagamento = new ArrayList<>();
    }
    
    public List<FormaDePagamento> getFormasPagamento(){
        return formasPagamento;
    }

}