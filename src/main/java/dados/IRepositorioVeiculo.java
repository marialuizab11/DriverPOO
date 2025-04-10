package dados;

import negocios.basicas.Motorista;
import negocios.basicas.Veiculo;

/**
 * Define as operacoes que o veiculo pode ter no sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public interface IRepositorioVeiculo {
    void adicionar(Veiculo veiculo);
    Veiculo buscarPorId(int id);
    Veiculo buscarPorPlaca(String placa);
}
