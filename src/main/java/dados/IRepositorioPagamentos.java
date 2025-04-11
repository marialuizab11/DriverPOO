package dados;

import negocios.basicas.FormaDePagamento;

/**
 * Define o contrato para as operacoes das formas de pagamento. 
 *  
 * @author Maria Luiza Bezerra
 */
public interface IRepositorioPagamentos {
    /**
     * Adiciona uma forma de pagamento.
     * @param idViagem id da viagem a qual a forma de pagamento esta associada
     * @param pagamento forma de pagamento escolhida pelo cliente
     */
    void adicionar(String idViagem, FormaDePagamento pagamento);
}
