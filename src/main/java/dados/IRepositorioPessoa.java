package dados;

import negocios.basicas.Cliente;
import negocios.basicas.Motorista;
import negocios.basicas.Pessoa;
import negocios.basicas.Veiculo;

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
   
    
    void adicionarVeiculoMotorista(Veiculo veiculo);
}
