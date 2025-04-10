package dados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import negocios.basicas.Viagem;

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
    
    public void viagensNaoAceitas(){
        for(Viagem vg : viagens){
            if(!vg.isAceita()){
                System.out.println("ID: "+vg.getId());
                System.out.println("Origem: "+vg.getOrigem());
                System.out.println("Destino: "+vg.getDestino());
            }
        }
    }
    
    public void listarViagens(){
        System.out.println("VIAGENS");
        for (Viagem vg: viagens){
            
            System.out.println("Categoria: "+vg.getCategoriaVeiculo());
            System.out.println("Aceita: "+vg.isAceita());
            System.out.println("Valor: "+vg.getValorTotal());
            
        }
    }
}