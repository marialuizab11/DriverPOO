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
    private static final String PASTA_DADOS = "data/";
    private static final String ARQ_VEICULOS = PASTA_DADOS+"veiculos.ser";
    private List<Veiculo> veiculos;
    RepositorioPessoa repoPessoa = new RepositorioPessoa();

    public RepositorioVeiculo() {
        criarPastasDados();
        this.veiculos = carregar();
    }
    
    private void criarPastasDados(){
        new File (PASTA_DADOS).mkdirs();
    }
    
    private void salvar(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQ_VEICULOS))){
            out.writeObject(veiculos);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public List<Veiculo> carregar(){
        File file = new File(ARQ_VEICULOS);
        
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
    public void adicionar(Veiculo veiculo) {
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
    
    @Override
    public Veiculo buscarPorId(int id){
        for(Veiculo v : veiculos){
            if(v.getId() == id){
                return v;
            }
        }
        return null;
    }

    public void remover(String placa) {
        Veiculo veiculo = buscarPorPlaca(placa);
        veiculos.remove(veiculo);
        salvar();
    }
    
    public void listarVeiculos(){
        System.out.println("VEICULOS");
        for(Veiculo vc: veiculos){
            System.out.println("Id do veiculo: "+vc.getId());
            System.out.println("Placa: "+vc.getPlaca());
            System.out.println("Modelo: "+vc.getModelo());
            System.out.println("Categoria: "+vc.getCategoria());
            System.out.println("Motorista id: "+vc.getIdMotorista());
        }
    }
}