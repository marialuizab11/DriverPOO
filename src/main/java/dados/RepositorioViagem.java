package dados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import negocios.basicas.*;

/**
 * @author Maria Luiza Bezerra
 */
public class RepositorioViagem implements IRepositorioViagem {
    private List<Viagem> viagens;
    
    private static final String PASTA_DADOS = "data/";
    private static final String ARQ_VIAGENS = PASTA_DADOS+"viagens.ser";

    public RepositorioViagem() {
        criarPastasDados();
        this.viagens = carregar();
    }
    
     private void criarPastasDados(){
        new File (PASTA_DADOS).mkdirs();
    }
    
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
    
    private void salvar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQ_VIAGENS))){
            oos.writeObject(viagens);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void adicionar(Viagem viagem) {
        viagens.add(viagem);
        salvar();
    }

    @Override
    public Viagem buscarViagem(int id) {
        for(Viagem vg: viagens){
            if(vg.getId() == id){
                return vg;
            }
        }
        return null;
    }
    
    public List<Viagem> getTodas(){
        return viagens;
    }
    
    public void listarViagens(){
        System.out.println("VIAGENS");
        for (Viagem vg: viagens){
            System.out.println("Categoria: "+vg.getCategoriaVeiculo());
            System.out.println("Aceita: "+vg.isAceita());
            System.out.println("Valor: "+vg.getValorTotal());
            System.out.println("Cliente: "+vg.getCliente().getNome());
            
        }
    }
    
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
    
    public void aceitarViagem(Viagem viagem, Motorista motorista, Veiculo veiculo){
        viagem.setAceita(true);
        viagem.setMotorista(motorista);
        viagem.setVeiculo(veiculo);
        salvar();
    }
    
    public void adicionarAvaliacaoCliente(Viagem viagem, Avaliacao avaliacaoCliente){
        viagem.setAvaliacaoCliente(avaliacaoCliente);
        salvar();
    }
    
    public void adicionarAvaliacaoMotorista(Viagem viagem, Avaliacao avaliacaoMotorista){
        viagem.setAvaliacaoMotorista(avaliacaoMotorista);
        salvar();
    }
}