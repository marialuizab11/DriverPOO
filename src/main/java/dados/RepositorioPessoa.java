package dados;

import java.io.*;
import java.util.*;
import negocios.basicas.*;
import negocios.excecoes.PessoaNaoEncontradaException;

/**
 * @author Maria Luiza Bezerra
 */
public class RepositorioPessoa implements IRepositorioPessoa {
    private List<Cliente> clientes;
    private List<Motorista> motoristas;
    
    private IRepositorioPagamentos repoPagamentos;
    
    private static final String PASTA_DADOS = "data/";
    private static final String ARQ_CLIENTES = PASTA_DADOS + "clientes.ser";
    private static final String ARQ_MOTORISTAS = PASTA_DADOS + "motoristas.ser";

    public RepositorioPessoa(){
        criarPastasDados();
        this.clientes = carregar(ARQ_CLIENTES);
        this.motoristas = carregar(ARQ_MOTORISTAS);
    }
    
    private void criarPastasDados(){
        new File (PASTA_DADOS).mkdirs();
    }
    
    @SuppressWarnings("unchecked")
    private <T> List<T> carregar(String caminho){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(caminho))){
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e){
            return new ArrayList<>();
        }
    }
    
    private <T> void salvar(List<T> lista, String caminho){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(caminho))){
            out.writeObject(lista);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    private void salvarDados(){
        salvar(clientes, ARQ_CLIENTES);
        salvar(motoristas, ARQ_MOTORISTAS);
    }

    @Override
    public void adicionarCliente(Cliente cliente) {
        if(cliente!=null && !existePessoa(cliente.getCpf())){
            clientes.add(cliente);
            salvarDados();
        }
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        for (Cliente cl : clientes){
            if(cl.getCpf().equals(cpf)){
                return cl;
            }
        }
        return null;
    }

    @Override
    public void adicionarMotorista(Motorista motorista) {
        if(motorista != null && !existePessoa(motorista.getCpf())){
            motoristas.add(motorista);
            salvarDados();
        }
    }

    @Override
    public Motorista buscarPorCnh(String cnh) {
        for(Motorista mt: motoristas){
            if(mt.getCnh().equals(cnh)){
                return mt;
            }
        }
        return null;
    }

    @Override
    public boolean atualizar(String cpf, String email, String telefone) {
        Pessoa pessoa = buscarPorCpf(cpf);
        if(pessoa!=null){
            pessoa.setEmail(email);
            pessoa.setTelefone(telefone);
            salvarDados();
            return true;
        } else{
            return false;
        }
    }
    
    public void atualizarValidacao(Motorista motorista) throws PessoaNaoEncontradaException {
        Motorista encontrado = buscarPorCnh(motorista.getCnh());
        if (encontrado == null) {
            throw new PessoaNaoEncontradaException("Motorista não encontrado!");
        }
        encontrado.setValidado(true);
        salvarDados();
    }
    
    public void atualizarDisponibilidade(Motorista motorista, boolean disponibilidade){
        Motorista encontrado = buscarPorCnh(motorista.getCnh());
        if (encontrado == null) {
            throw new PessoaNaoEncontradaException("Motorista não encontrado!");
        }
        encontrado.setDisponivel(disponibilidade);
        salvarDados();
    }

    @Override
    public void removerCliente(String cpf) {
        Pessoa pessoa = buscarPorCpf(cpf);
        clientes.remove(pessoa);
            salvarDados();
    }
    
    @Override
    public void removerMotorista(String cpf){
        Pessoa pessoa = buscarPorCpf(cpf);
        motoristas.remove(pessoa);
        salvarDados();
    }
    
    private boolean existePessoa(String cpf){
        return buscarPorCpf(cpf)!=null;
    }
    
    /**
     * Lista todos os motoristas disponiveis para viagem. Isso significa que eles tem que estar disponivel e validado no sistema.
     * 
     */
    public void listarMotoristasDisponiveis(){
        if(motoristas.isEmpty()){
            System.out.println("Ainda nao ha motoristas cadastrados");
        }
        for(Motorista mt : motoristas){
            if(mt.isDisponivel() && mt.isValidado()){
                System.out.println("Nome: "+mt.getNome());
            }
        }
    }
}