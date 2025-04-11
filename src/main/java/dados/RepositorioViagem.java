package dados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import negocios.basicas.*;

/**
 * Implementacao concreta de {@link IRepositorioViagem} para operacoes de persistencia de viagens com armazenamento em arquivos.
 * 
 * @author Maria Luiza Bezerra
 */
public class RepositorioViagem implements IRepositorioViagem {
    private List<Viagem> viagens;
    
    private static final String PASTA_DADOS = "data/";
    private static final String ARQ_VIAGENS = PASTA_DADOS+"viagens.ser";

    /**
     * Constroi um novo repositorio de viagens, inicializando as estruturas dos diretorios e carregando os dados persistentes se existentes.
     */
    public RepositorioViagem() {
        criarPastasDados();
        this.viagens = carregar();
    }
    
    /**
     * Cria a estrutura de diretorios necessaria para armazenamento dos dados.
     */
     private void criarPastasDados(){
        new File (PASTA_DADOS).mkdirs();
    }
    
     /**
      * Carrega a lista de viagens do arquivo de persistencia.
      * @return Lista de viagens carregadas ou lista vazia se o arquivo nao existir
      */
    private List<Viagem> carregar(){
        File file = new File(ARQ_VIAGENS);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Viagem>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); 
            return new ArrayList<>();
        }
    }
    
    /**
     * Persiste a lista de viagens no arquivo configurado
     */
    private void salvar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQ_VIAGENS))){
            oos.writeObject(viagens);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Adiciona uma nova viagem ao repositorio.
     * @param viagem 
     */
    @Override
    public void adicionar(Viagem viagem) {
        viagens.add(viagem);
        salvar();
    }

    /**
     * Busca uma viagem pelo seu id.
     * @param id
     * @return A viagem encontrada ou null se o id nao estiver associado a uma viagem.
     */
    @Override
    public Viagem buscarViagem(int id) {
        for(Viagem vg: viagens){
            if(vg.getId() == id){
                return vg;
            }
        }
        return null;
    }
    
    /**
     * Retorna todas as viagens armazenadas.
     */
    public List<Viagem> getTodas(){
        return viagens;
    }
    
    /**
     * Lista todas as viagens de um motorista especifico.
     * @param motorista
     * @return true se for encontrada alguma viagem, false se o motorista nao tiver nenhuma viagem.
     */
    public boolean listarViagensDeMotorista(Motorista motorista){
        System.out.println("\nViagens de "+motorista.getNome());
        boolean temViagens = false;
        for (Viagem vg: viagens){
            if(vg.getMotorista() != null && vg.getMotorista().equals(motorista)){
                System.out.println("Valor: "+vg.getValorTotal());
                System.out.println("Cliente: "+vg.getCliente().getNome());
                if(vg.getAvaliacaoCliente()!=null){
                    System.out.println("Avaliacao do cliente:");           
                    System.out.println("Estrelas: "+vg.getAvaliacaoCliente().getEstrelas());
                    System.out.println("Descricao: "+vg.getAvaliacaoCliente().getDescricao());
                } else{
                    System.out.println("Cliente nao avaliou a corrida");
                }
                temViagens = true;
            }
        }
        return temViagens;
    }
    
    /**
     * Lista todas as viagens que o cliente ja fez.
     * @param cliente
     * @return true se foi encontrada alguma viagem feita, false se o cliente ainda nao fez viagens
     */
    public boolean listarViagensFeitasDeCliente(Cliente cliente){
        System.out.println("\nViagens de "+cliente.getNome());
        boolean temViagens = false;
        for (Viagem vg: viagens){
            if(vg.getCliente() != null && vg.getCliente().equals(cliente) && vg.isAceita() && vg.getAvaliacaoCliente() == null){
                System.out.println("ID: "+vg.getId());
                System.out.println("Valor: "+vg.getValorTotal());
                System.out.println("Motorista: "+vg.getMotorista().getNome());
                if(vg.getAvaliacaoMotorista()!=null){
                    System.out.println("Avaliacao do motorista:");           
                    System.out.println("Estrelas: "+vg.getAvaliacaoMotorista().getEstrelas());
                    System.out.println("Descricao: "+vg.getAvaliacaoMotorista().getDescricao());
                } else{
                    System.out.println("Motorista nao avaliou a corrida");
                }
                temViagens = true;
            }
        }
        return temViagens;
    }
    
    /**
     * Aceita a viagem que o motorista escolheu.
     * @param viagem viagem aceita
     * @param motorista motorista que vai fazer a viagem
     * @param veiculo veiculo do motorista responsavel
     */
    public void aceitarViagem(Viagem viagem, Motorista motorista, Veiculo veiculo){
        viagem.setAceita(true);
        viagem.setMotorista(motorista);
        viagem.getMotorista().setDisponivel(true);
        viagem.setVeiculo(veiculo);
        salvar();
    }
    
    /**
     * Adiciona a avaliacao que o motorista fez sobre o cliente.
     * @param viagem 
     * @param avaliacaoCliente 
     */
    public void adicionarAvaliacaoCliente(Viagem viagem, Avaliacao avaliacaoCliente){
        viagem.setAvaliacaoCliente(avaliacaoCliente);
        salvar();
    }
    
    /**
     * Adiciona a avaliacao que o cliente fez sobre o motorista.
     * @param viagem
     * @param avaliacaoMotorista 
     */
    public void adicionarAvaliacaoMotorista(Viagem viagem, Avaliacao avaliacaoMotorista){
        viagem.setAvaliacaoMotorista(avaliacaoMotorista);
        salvar();
    }
}