package dados;

import negocios.basicas.*;

/**
 * Define as operacoes que as pessoas podem fazer no sistema
 *
 * @author Maria Luiza Bezerra
 */
public interface IRepositorioPessoa {
    /**
     * Adiciona um cliente ao sistema
     * @param cliente objeto cliente
     */
    void adicionarCliente(Cliente cliente);
    
    /**
     * 
     * @param cpf 
     */
    void removerCliente(String cpf);
    
    void adicionarMotorista(Motorista motorista);
    Motorista buscarPorCnh(String cnh);
    void removerMotorista(String cpf);    
    
    Pessoa buscarPorCpf(String cpf);    
    boolean atualizar(String cpf, String email, String telefone);
   
}
