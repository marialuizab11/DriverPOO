package dados;

import java.io.*;
import java.util.*;
import negocios.basicas.*;
import negocios.excecoes.MotoristaNaoDisponivelException;
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
        clientes.add(cliente);
        salvarDados();
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
        motoristas.add(motorista);
        salvarDados();
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

    public Motorista buscarPorId(int id){
        for(Motorista mt: motoristas){
            if(mt.getId() == id){
                return mt;
            }
        }
        return null;
    }
    
    @Override
    public void atualizar(Pessoa pessoa, String email, String telefone) {
        pessoa.setEmail(email);
        pessoa.setTelefone(telefone);
        salvarDados();
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
    
    public void atualizarVeiculoDoMotorista(Motorista motorista, int idVeiculo){
        motorista.setIdVeiculo(idVeiculo);
        salvarDados();
    }

    @Override
    public void removerCliente(String cpf) {
        Pessoa pessoa = buscarPorCpf(cpf);
        clientes.remove(pessoa);
        salvarDados();
    }
    
    @Override
    public void removerMotorista(String cnh){
        Motorista motorista = buscarPorCnh(cnh);
        if (motorista!=null){
            motoristas.remove(motorista);
            salvarDados();
        }
    }
    
    public void listarMotoristas(){
        System.out.println("MOTORISTAS");
        for (Motorista mt: motoristas){
            System.out.println("ID: "+mt.getId());
            System.out.println("Nome: "+mt.getNome());
            System.out.println("CNH: "+mt.getCnh());
            System.out.println("email: "+mt.getEmail());
            System.out.println("cpf: "+mt.getCpf());
            System.out.println("Id veiculo: "+mt.getIdVeiculo()+"\n");
        }
    }
    
    public void listarClientes(){
        System.out.println("\nCLIENTES");
        for(Cliente cl : clientes){
            System.out.println("Nome: "+cl.getNome());    
            System.out.println("Cpf: "+cl.getCpf());
            System.out.println("Telefone: "+cl.getTelefone());
            System.out.println("Email: "+cl.getEmail()+"\n");
            
            
        }
    }
}