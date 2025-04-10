package negocios;

import dados.RepositorioPessoa;
import negocios.basicas.Cliente;
import negocios.basicas.Motorista;
import negocios.basicas.Pessoa;
import negocios.excecoes.*;

/**
 * Gerenciador que implementa as regras de negocio no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class GerenciadorPessoa {
    RepositorioPessoa repoPessoa;
    
    public GerenciadorPessoa(RepositorioPessoa repoPessoa){
        this.repoPessoa = repoPessoa;
    }
    
    /**
     * Retorna se o motorista já está validado e apto para aceitar corridas
     * @param motorista Objeto motorista a ser checado
     * @return true se o objeto nao for nulo e estiver validado, false se nao estiver validado ou for nulo
     */
    public boolean isMotoristaValido(Motorista motorista){
        return motorista != null && motorista.isValidado();
    }
    
    /**
     * Valida um motorista pedindo para ele digitar corretamente sua cnh, cpf e email cadastrado.
     * @param cnh cnh do motorista
     * @param email email do motorista
     * @param cpf cpf do motorista
     * @throws PessoaNaoEncontradaException lançado quando o motorista nao estiver no sistema
     */
    public void validarMotorista(String cnh, String email, String cpf) throws PessoaNaoEncontradaException{
        Motorista motoristaEncontrado = repoPessoa.buscarPorCnh(cnh);
        
        if(!motoristaEncontrado.getEmail().equalsIgnoreCase(email)){
            throw new PessoaNaoEncontradaException("Email nao corresponde");
        }
        if(!motoristaEncontrado.getCpf().equals(cpf)){
            throw new PessoaNaoEncontradaException("CPF nao corresponde");
        }
        repoPessoa.atualizarValidacao(motoristaEncontrado);
    }
    
    /**
     * Cadastra o motorista no sistema, chamando o adicionarMotorista do repositorio
     * @param nome nome completo
     * @param email email valido
     * @param telefone telefone para contato
     * @param cpf cpf 
     * @param cnh cnh
     * @throws PessoaJaCadastradaException lançado quando o motorista ja existir no sistema
     */
    public void cadastrarMotorista(String nome, String email, String telefone, String cpf, String cnh) throws PessoaJaCadastradaException {
        if (repoPessoa.buscarPorCnh(cnh) != null){
            throw new PessoaJaCadastradaException("Motorista ja cadastrado no sistema");
        }
        repoPessoa.adicionarMotorista(new Motorista(nome, email, telefone, cpf, cnh));
    }
    
    public void cadastrarCliente(String nome, String email, String telefone, String cpf) throws PessoaJaCadastradaException {
        if(repoPessoa.buscarPorCpf(cpf) != null){
            throw new PessoaJaCadastradaException("Cliente ja cadastrado no sistema");
        }
        repoPessoa.adicionarCliente(new Cliente(nome, email, telefone, cpf));    
    }
    
    public Cliente buscarCliente(String cpf){
        return repoPessoa.buscarPorCpf(cpf);
    }
    
    public Motorista buscarMotorista(String cnh) throws PessoaNaoEncontradaException{
        Motorista motorista = repoPessoa.buscarPorCnh(cnh);
        if(motorista == null){
            throw new PessoaNaoEncontradaException("Motorista nao encontrado.");
        }
        return motorista;
    }
    
    public void removerCliente(String cpf) throws PessoaNaoEncontradaException{
        Cliente clienteEncontrado = repoPessoa.buscarPorCpf(cpf);
        
        if(clienteEncontrado == null){
            throw new PessoaNaoEncontradaException("Cliente nao encontrado");
        }        
        repoPessoa.removerCliente(cpf);
    }
    
    public void removerMotorista(String cnh) throws PessoaNaoEncontradaException {
        Motorista motoristaEncontrado = repoPessoa.buscarPorCnh(cnh);
        
        if(motoristaEncontrado == null){
            throw new PessoaNaoEncontradaException("Motorista nao encontrado");
        }
        repoPessoa.removerMotorista(cnh);
    }
    
    public void verificarDisponibilidade(Motorista motorista) throws MotoristaNaoDisponivelException{
        if(!motorista.isDisponivel()){
            throw new MotoristaNaoDisponivelException("Motorista solicitado esta em outra corrida ou nao disponivel no momento");
        } else{
            motorista.setDisponivel(false);
        }
    }

    public void atualizarCliente(String cpf, String email, String telefone) throws PessoaNaoEncontradaException{
        Cliente cliente = repoPessoa.buscarPorCpf(cpf);
        if(cliente == null){
            throw new PessoaNaoEncontradaException("Cliente nao encontrado");
        }
        repoPessoa.atualizar(cliente, email, telefone);
    }
    
    public void atualizarMotorista(String cnh, String email, String telefone) throws PessoaNaoEncontradaException{
        Motorista motorista = repoPessoa.buscarPorCnh(cnh);
        if(motorista == null){
            throw new PessoaNaoEncontradaException("Motorista nao encontrado");
        }
        repoPessoa.atualizar(motorista, email, telefone);
    }
    
    public void adicionarVeiculoMotorista(Motorista motorista, int idVeiculo) {
        repoPessoa.atualizarVeiculoDoMotorista(motorista, idVeiculo);
    }
    
    public void verificarValidacaoMotorista(Motorista motorista) throws MotoristaNaoValidadoException{
        if(!motorista.isValidado()){
            throw new MotoristaNaoValidadoException("O motorista deve estar validado para aceitar corridas");
        }
    }
    
    public void verificarVeiculoMotorista(Motorista motorista) throws MotoristaSemVeiculoException{
        if(motorista.getIdVeiculo()<=0){
            throw new MotoristaSemVeiculoException("O motorista deve ter um veiculo cadastrado para comecar a trabalhar");
        }
    }
}