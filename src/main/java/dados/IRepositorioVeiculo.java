package dados;

import negocios.basicas.Motorista;
import negocios.basicas.Veiculo;

/**
 * Define as operacoes que o veiculo pode ter no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public interface IRepositorioVeiculo {
    void adicionar(Motorista motorista, Veiculo veiculo);
    Veiculo buscarPorPlaca(String placa);
}
