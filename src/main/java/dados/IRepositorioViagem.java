package dados;

import negocios.basicas.Viagem;

/**
 *
 * @author Maria Luiza Bezerra
 */
public interface IRepositorioViagem {
    public void adicionar(Viagem viagem);
    Viagem buscarViagem(int id);
}
