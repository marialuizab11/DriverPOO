package dados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import negocios.basicas.Motorista;
import negocios.basicas.Veiculo;

/**
 * @author Maria Luiza Bezerra
 */
public class RepositorioVeiculo implements IRepositorioVeiculo {
    private static final String ARQUIVO = "veiculos.ser";
    private List<Veiculo> veiculos;

    public RepositorioVeiculo() {
        this.veiculos = carregar();
    }
    
    private void salvar(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))){
            out.writeObject(veiculos);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public List<Veiculo> carregar(){
        File file = new File(ARQUIVO);
        
        if(!file.exists()){
            return new ArrayList<>();
        }
        
         try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
             return (List<Veiculo>) in.readObject();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    @Override
    public void adicionar(Motorista motorista, Veiculo veiculo) {
        veiculo.setMotorista(motorista);
        veiculos.add(veiculo);
        salvar();
    }

    @Override
    public Veiculo buscarPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null;
    }

}