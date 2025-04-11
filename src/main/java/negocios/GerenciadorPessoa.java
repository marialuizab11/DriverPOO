package negocios;

import dados.RepositorioPessoa;
import negocios.basicas.Cliente;
import negocios.basicas.Motorista;
import negocios.excecoes.*;

/**
 * Gerencia as operacoes relacionadas a pessoas (clientes e motoristas) no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class GerenciadorPessoa {
    RepositorioPessoa repoPessoa;
    
    /**
     * Constroi um novo gerenciador de pessoas com repositorio especificado.
     * @param repoPessoa 
     */
    public GerenciadorPessoa(RepositorioPessoa repoPessoa){
        this.repoPessoa = repoPessoa;
    }
    
    /**
     * Verifica se um motorista esta validado (apto para aceitar corridas)
     * @param motorista 
     * @return true se o motorista nao for nulo e estiver validado, false se nao estiver validado ou for nulo
     */
    public boolean isMotoristaValido(Motorista motorista){
        return motorista != null && motorista.isValidado();
    }
    
    /**
     * Valida um motorista pedindo para ele digitar corretamente sua cnh, cpf e email cadastrado.
     * @param motorista
     * @param email email do motorista
     * @param cpf cpf do motorista
     * @throws PessoaNaoEncontradaException lançado quando o motorista nao estiver no sistema
     */
    public void validarMotorista(Motorista motorista, String email, String cpf) throws PessoaNaoEncontradaException{
        if(!motorista.getEmail().equalsIgnoreCase(email)){
            throw new PessoaNaoEncontradaException("Email nao corresponde");
        }
        if(!motorista.getCpf().equals(cpf)){
            throw new PessoaNaoEncontradaException("CPF nao corresponde");
        }
        repoPessoa.atualizarValidacao(motorista);
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
    
    /**
     * Cadastrar novo cliente no sistema.
     * 
     * @param nome
     * @param email
     * @param telefone
     * @param cpf
     * @throws PessoaJaCadastradaException se o cpf ja corresponder com de outro cliente
     */
    public void cadastrarCliente(String nome, String email, String telefone, String cpf) throws PessoaJaCadastradaException {
        if(repoPessoa.buscarPorCpf(cpf) != null){
            throw new PessoaJaCadastradaException("Cliente ja cadastrado no sistema");
        }
        repoPessoa.adicionarCliente(new Cliente(nome, email, telefone, cpf));    
    }
    
    /**
     * Busca um cliete pelo seu cpf
     * @param cpf
     * @return O cliente encontrado
     * @throws PessoaNaoEncontradaException Se o cliente nao estiver cadastrada no sistema
     */
    public Cliente buscarCliente(String cpf) throws PessoaNaoEncontradaException{
        Cliente cliente = repoPessoa.buscarPorCpf(cpf);
        if(cliente==null){
            throw new PessoaNaoEncontradaException("Cpf nao cadastrado no sistema.");
        }
        return cliente;
    }
    
    /**
     * Busca um motorista pela cnh.
     * @param cnh
     * @return O motorista encontrado
     * @throws PessoaNaoEncontradaException Se o motorista nao estiver cadastrado no sistema
     */
    public Motorista buscarMotorista(String cnh) throws PessoaNaoEncontradaException{
        Motorista motorista = repoPessoa.buscarPorCnh(cnh);
        if(motorista == null){
            throw new PessoaNaoEncontradaException("Motorista nao encontrado.");
        }
        return motorista;
    }
    
    /**
     * Remove um cliente do sistema
     * @param cliente
     * @throws PessoaNaoEncontradaException Se o cliente nao for encontrado
     */
    public void removerCliente(Cliente cliente) throws PessoaNaoEncontradaException{
        if(cliente == null){
            throw new PessoaNaoEncontradaException("Cliente nao encontrado");
        }        
        repoPessoa.removerCliente(cliente.getCpf());
    }
    
    /**
     * Remove um motorista do sistema.
     * @param cnh
     * @throws PessoaNaoEncontradaException Se o motorista nao for encontrado
     */
    public void removerMotorista(String cnh) throws PessoaNaoEncontradaException {
        Motorista motoristaEncontrado = repoPessoa.buscarPorCnh(cnh);
        
        if(motoristaEncontrado == null){
            throw new PessoaNaoEncontradaException("Motorista nao encontrado");
        }
        repoPessoa.removerMotorista(cnh);
    }
    
    /**
     * Verifica se o motorista esta disponivel. Um motorista nao pode aceitar mais de uma corrida.
     * @param motorista
     * @throws MotoristaNaoDisponivelException Se o motorista ja estiver em uma corrida
     */
    public void verificarDisponibilidade(Motorista motorista) throws MotoristaNaoDisponivelException{
        if(!motorista.isDisponivel()){
            throw new MotoristaNaoDisponivelException("Motorista solicitado esta em outra corrida ou nao disponivel no momento");
        } else{
            motorista.setDisponivel(false);
        }
    }

    /**
     * Atualiza informacoes de um cliente
     * @param cliente
     * @param email
     * @param telefone
     * @throws PessoaNaoEncontradaException Se o cliente for nulo
     */
    public void atualizarCliente(Cliente cliente, String email, String telefone) throws PessoaNaoEncontradaException{
        if(cliente == null){
            throw new PessoaNaoEncontradaException("Cliente nao encontrado");
        }
        repoPessoa.atualizar(cliente, email, telefone);
    }
    
    /**
     * Adiciona um veiculo a um motorista.
     * @param motorista
     * @param idVeiculo 
     */
    public void adicionarVeiculoMotorista(Motorista motorista, int idVeiculo) {
        repoPessoa.atualizarVeiculoDoMotorista(motorista, idVeiculo);
    }
    
    /**
     * Verifica se o motorista ja esta validado.
     * @param motorista
     * @throws MotoristaNaoValidadoException Se o motorista ainda nao estiver validado, assim ele nao pode aceitar viagens.
     */
    public void verificarValidacaoMotorista(Motorista motorista) throws MotoristaNaoValidadoException{
        if(!motorista.isValidado()){
            throw new MotoristaNaoValidadoException("O motorista deve estar validado para aceitar corridas");
        }
    }
    
    /**
     * Verifica se o motorista tem um veiculo.
     * @param motorista
     * @throws MotoristaSemVeiculoException Se o motorista nao tiver um veiculo cadastrado, assim ele nao pode aceitar viagens.
     */
    public void verificarVeiculoMotorista(Motorista motorista) throws MotoristaSemVeiculoException{
        if(motorista.getIdVeiculo()<=0){
            throw new MotoristaSemVeiculoException("O motorista deve ter um veiculo cadastrado para comecar a trabalhar");
        }
    }
    
    
}