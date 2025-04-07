package dados;

import java.util.List;
import negocios.basicas.CartaoCredito;
import negocios.basicas.FormaDePagamento;

/**
 *
 * @author Maria Luiza Bezerra
 */
public interface IRepositorioPagamentos {
    void adicionar(String cpfCliente, FormaDePagamento formaPagamento);
    void removerCartao(String cpf, String numeroCartao);
    List<CartaoCredito> listarCartoes(String cpf);
}
