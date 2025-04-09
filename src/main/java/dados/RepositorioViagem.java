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
    
    private static final String ARQ_VIAGENS = "viagens.ser";

    public RepositorioViagem() {
        this.viagens = carregar();
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
    
}