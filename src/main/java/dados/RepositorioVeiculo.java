package dados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import negocios.basicas.Veiculo;

/**
 * Implementacao concreta de {@link IRepositorioVeiculo} para operacoes de veiculos com persistencia em arquivos.
 *
 * @author Maria Luiza Bezerra
 */
public class RepositorioVeiculo implements IRepositorioVeiculo {
    private static final String PASTA_DADOS = "data/";
    private static final String ARQ_VEICULOS = PASTA_DADOS+"veiculos.ser";
    private List<Veiculo> veiculos;

    /**
     * Constroi um novo repositorio de veiculos, inicilizando os diretorios e carregando os dados persistentes se existente.
     */
    public RepositorioVeiculo() {
        criarPastasDados();
        this.veiculos = carregar();
    }
    
    /**
     * Cria a estrutura de diretorios necessaria para o armazenamento dos dados
     */
    private void criarPastasDados(){
        new File (PASTA_DADOS).mkdirs();
    }
    
    /**
     * Persiste a lista de veiculos no arquivo configurado.
     */
    private void salvar(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQ_VEICULOS))){
            out.writeObject(veiculos);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Carrega a lista de veiculos do arquivo de persistencia.
     * @return Lista de veiculos carregada ou uma lista vazia se o arquivo nao existir
     */
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

    /**
     * Adiciona um novo veiculo ao repositorio.
     * 
     * @param veiculo 
     */
    @Override
    public void adicionar(Veiculo veiculo) {
        veiculos.add(veiculo);
        salvar();
    }

    /**
     * Busca o veiculo pela sua placa
     * @param placa
     * @return O veiculo encontrado ou null se a placa nao estiver cadastrada.
     */
    @Override
    public Veiculo buscarPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null;
    }
    
    /**
     * Busca um veiculo pelo seu ID
     * @param id
     * @return O veiculo encontrado ou null se o id nao estiver ligado a nenhum veiculo.
     */
    @Override
    public Veiculo buscarPorId(int id){
        for(Veiculo v : veiculos){
            if(v.getId() == id){
                return v;
            }
        }
        return null;
    }

    /**
     * Remove um veiculo do repositorio pela placa
     * 
     * @param placa 
     */
    public void remover(String placa) {
        Veiculo veiculo = buscarPorPlaca(placa);
        veiculos.remove(veiculo);
        salvar();
    }
}