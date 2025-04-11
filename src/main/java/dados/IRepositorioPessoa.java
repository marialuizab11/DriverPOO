package dados;

import negocios.basicas.*;

/**
 * Define o contrato para as acoes que Pessoas tem no sistema.
 *
 * @author Maria Luiza Bezerra
 */
public interface IRepositorioPessoa {
    void adicionarCliente(Cliente cliente);
    void removerCliente(String cpf);
    
    void adicionarMotorista(Motorista motorista);
    Motorista buscarPorCnh(String cnh);
    void removerMotorista(String cpf);    
    
    Pessoa buscarPorCpf(String cpf);    
    void atualizar(Pessoa pessoa, String email, String telefone);
   
}
