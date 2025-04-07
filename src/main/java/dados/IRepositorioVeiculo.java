package dados;

import negocios.basicas.Motorista;
import negocios.basicas.Veiculo;

/**
 *
 * @author Maria Luiza Bezerra
 */
public interface IRepositorioVeiculo {
    void adicionar(Motorista motorista, Veiculo veiculo);
    Veiculo buscarPorPlaca(String placa);
}
