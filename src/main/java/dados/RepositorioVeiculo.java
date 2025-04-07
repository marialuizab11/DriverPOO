package dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import negocios.basicas.Motorista;
import negocios.basicas.Veiculo;

/**
 * @author Maria Luiza Bezerra
 */
public class RepositorioVeiculo implements IRepositorioVeiculo {
    private static final String ARQUIVO_VEICULOS = "veiculos.dat";
    private List<Veiculo> veiculos;

    public RepositorioVeiculo() {
        this.veiculos = new ArrayList<>();
        carregarDados();
    }
    
    private void salvarDados(){
        try{
            File arquivo = new File(ARQUIVO_VEICULOS);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));
            oos.writeObject(veiculos);
            oos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void carregarDados(){
        File arquivo = new File(ARQUIVO_VEICULOS);
        if(arquivo.exists()){
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo));
                veiculos = (List<Veiculo>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void adicionar(Motorista motorista, Veiculo veiculo) {
        veiculo.setMotorista(motorista);
        veiculos.add(veiculo);
        salvarDados();
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