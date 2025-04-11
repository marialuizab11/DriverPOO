package dados;

import java.io.*;
import java.util.*;
import negocios.basicas.*;

/**
 * Implemetacao concreta de {@link IRepositorioPessoa} para operacoes de pessoas (Cliente, Motorista) com persistencia em arquivos.
 * 
 * @author Maria Luiza Bezerra
 */
public class RepositorioPessoa implements IRepositorioPessoa {
    private List<Cliente> clientes;
    private List<Motorista> motoristas;
    
    private IRepositorioPagamentos repoPagamentos;
    
    private static final String PASTA_DADOS = "data/";
    private static final String ARQ_CLIENTES = PASTA_DADOS + "clientes.ser";
    private static final String ARQ_MOTORISTAS = PASTA_DADOS + "motoristas.ser";

    /**
     * Constroi um novo repositorio, inicializando as estruturas de dados e carregando os dados persistentes se existirem.
     */
    public RepositorioPessoa(){
        criarPastasDados();
        this.clientes = carregar(ARQ_CLIENTES);
        this.motoristas = carregar(ARQ_MOTORISTAS);
    }
    
    /**
     * Cria a estrutura de diretorios de armazenamento necessaria.
     */
    private void criarPastasDados(){
        new File (PASTA_DADOS).mkdirs();
    }
    
    /**
     * Carrega uma lista de objetos do arquivo especificado.
     * @param <T> Tipo generico dos objetos a serem carregados
     * @param caminho Caminho completo do arquivo a ser carregado
     * @return Lista contendo os objetos carregados, ou uma lista vazia se o arquivo nao existir.
     */   
    @SuppressWarnings("unchecked")
    private <T> List<T> carregar(String caminho){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(caminho))){
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e){
            return new ArrayList<>();
        }
    }
    
    /**
     * Persiste uma lista de objetos no arquivo especificado.
     * @param <T> Tipo generico dos objetos a serem persistidos
     * @param lista lista de objetos a serem salvos
     * @param caminho Caminho completo do arquivo de destino
     */
    private <T> void salvar(List<T> lista, String caminho){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(caminho))){
            out.writeObject(lista);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Salva todas as alteracoes feitas no repositorio.
     */
    private void salvarDados(){
        salvar(clientes, ARQ_CLIENTES);
        salvar(motoristas, ARQ_MOTORISTAS);
    }

    /**
     * Adiciona um novo cliente.
     * @param cliente Objeto cliente (nao-nulo) a ser adicionado
     */
    @Override
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
        salvarDados();
    }

    /**
     * Busca um cliente pelo cpf.
     * @param cpf
     * @return O cliente encontrado ou null se o cpf nao estiver cadastrado.
     */
    @Override
    public Cliente buscarPorCpf(String cpf) {
        for (Cliente cl : clientes){
            if(cl.getCpf().equals(cpf)){
                return cl;
            }
        }
        return null;
    }

    /**
     * Adiciona um novo motorista.
     * @param motorista Objeto motorista (nao-nulo) a ser adicionado
     */
    @Override
    public void adicionarMotorista(Motorista motorista) {
        motoristas.add(motorista);
        salvarDados();
    }

    /**
     * Busca um motorista pela sua cnh.
     * @param cnh 
     * @return O motorista encontrado ou nulo se a cnh nao estiver cadastrada.
     */
    @Override
    public Motorista buscarPorCnh(String cnh) {
        for(Motorista mt: motoristas){
            if(mt.getCnh().equals(cnh)){
                return mt;
            }
        }
        return null;
    }
    
    /**
     * Atualiza informacoes de contato de uma pessoa.
     * @param pessoa 
     * @param email
     * @param telefone 
     */
    @Override
    public void atualizar(Pessoa pessoa, String email, String telefone) {
        pessoa.setEmail(email);
        pessoa.setTelefone(telefone);
        salvarDados();
    }
    
    /**
     * Atualiza o status de validacao de um motorista.
     * @param motorista 
     */
    public void atualizarValidacao(Motorista motorista) {
        Motorista encontrado = buscarPorCnh(motorista.getCnh());
        encontrado.setValidado(true);
        salvarDados();
    }
    
    /**
     * Atualiza o motorista com o Id do veiculo dele para que seja feita o referenciamento.
     * @param motorista
     * @param idVeiculo 
     */
    public void atualizarVeiculoDoMotorista(Motorista motorista, int idVeiculo){
        motorista.setIdVeiculo(idVeiculo);
        salvarDados();
    }

    /**
     * Remove um cliente do sistema.
     * @param cpf 
     */
    @Override
    public void removerCliente(String cpf) {
        Pessoa pessoa = buscarPorCpf(cpf);
        clientes.remove(pessoa);
        salvarDados();
    }
    
    /**
     * Remove um motorista do sistema.
     * @param cnh 
     */
    @Override
    public void removerMotorista(String cnh){
        Motorista motorista = buscarPorCnh(cnh);
        if (motorista!=null){
            motoristas.remove(motorista);
            salvarDados();
        }
    }
}