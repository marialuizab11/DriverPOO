package dados;

import negocios.basicas.*;

/**
 *
 * @author Maria Luiza Bezerra
 */
public interface IRepositorioPessoa {
    void adicionarCliente(Cliente cliente);
    boolean removerCliente(String cpf);
    
    void adicionarMotorista(Motorista motorista);
    Motorista buscarPorCnh(String cnh);
    boolean removerMotorista(String cpf);    
    
    Pessoa buscarPorCpf(String cpf);    
    boolean atualizar(String cpf, String email, String telefone);
   
}
