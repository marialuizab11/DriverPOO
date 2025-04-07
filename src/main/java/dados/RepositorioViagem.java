package dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import negocios.basicas.Viagem;

/**
 * @author Maria Luiza Bezerra
 */
public class RepositorioViagem implements IRepositorioViagem {
    private List<Viagem> viagens;
    
    private static final String ARQUIVO = "viagens.dat";

    public RepositorioViagem(List<Viagem> viagens) {
        this.viagens = new ArrayList<>();
        carregar();
    }
    
    private void carregar(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))){
            this.viagens = (List<Viagem>) ois.readObject();            
        } catch (IOException | ClassNotFoundException e){
            e.getMessage();
        }
    }
    
    private void salvar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))){
            oos.writeObject(viagens);
        } catch (IOException e){
            e.getMessage();
        }
    }
    
    @Override
    public void adicionar(Viagem viagem) {
        if(viagem != null){
            viagens.add(viagem);
            salvar();
        }
    }

    @Override
    public Viagem exibirViagem(int id) {
        for(Viagem vg: viagens){
            if(vg.getId() == id){
                return vg;
            }
        }
        return null;
    }
    
}