package dados;

import negocios.basicas.Viagem;

/**
 * Define o contrato para as operacoes que podem acontecer para Viagem.
 * 
 * @author Maria Luiza Bezerra
 */
public interface IRepositorioViagem {
    public void adicionar(Viagem viagem);
    Viagem buscarViagem(int id);
}
