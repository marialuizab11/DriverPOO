package negocios;

import dados.RepositorioPagamentos;
import negocios.basicas.FormaDePagamento;
import negocios.excecoes.PagamentoRecusadoException;

/**
 * Responsavel por coordenar todas as operacoes de pagamento do sistema.
 * 
 * @author Maria Luiza Bezerra
 */
public class GerenciadorPagamentos {
    RepositorioPagamentos repoPagamentos;
    
    /**
     * Constroi um novo gerenciador de pagamentos.
     * 
     * @param repoPagamentos 
     */
    public GerenciadorPagamentos(RepositorioPagamentos repoPagamentos){
        this.repoPagamentos = repoPagamentos;
    }
    
    /**
     * Gera uma chave PIX unica para um pagamento.
     * @param valor
     * @return codigo do pix gerado
     */
    public String gerarChavePix(double valor){
        return repoPagamentos.gerarPix(valor);
    }
    
    /**
     * Valida a configuracao de um pagamento via PIX
     * @param confirmacao
     * @throws PagamentoRecusadoException Se confirmacao = 'N'
     */
    public void validarPix(String confirmacao) throws PagamentoRecusadoException{
        if(confirmacao.equals("N")){
            throw new PagamentoRecusadoException("Pagamento por pix nao concluido");
        }
    }
    
    /**
     * Processa um pagamento com cartao de credito, verificando o limite disponivel
     * @param limite
     * @param valor
     * @throws PagamentoRecusadoException Se nao houver limite suficiente
     */
    public void debitarCartaoCredito(double limite, double valor) throws PagamentoRecusadoException{
        if((limite-valor) < 0){
            throw new PagamentoRecusadoException("Cartao de Credito sem limite");
        }
    }
    
    /**
     * Registra um pagamento no sistema associando-o uma viagem.
     * 
     * @param idViagem
     * @param pagamento 
     */
    public void cadastrarPagamento(String idViagem, FormaDePagamento pagamento){
        repoPagamentos.adicionar(idViagem, pagamento);
    }
}