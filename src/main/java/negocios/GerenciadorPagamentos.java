package negocios;

import dados.RepositorioPagamentos;
import negocios.basicas.FormaDePagamento;
import negocios.excecoes.PagamentoRecusadoException;

/**
 * @author Maria Luiza Bezerra
 */
public class GerenciadorPagamentos {
    RepositorioPagamentos repoPagamentos;
    
    public GerenciadorPagamentos(RepositorioPagamentos repoPagamentos){
        this.repoPagamentos = repoPagamentos;
    }
    
    public String gerarChavePix(double valor){
        return repoPagamentos.gerarPix(valor);
    }
    
    public void validarPix(String confirmacao) throws PagamentoRecusadoException{
        if(confirmacao.equals("N")){
            throw new PagamentoRecusadoException("Pagamento por pix nao concluido");
        }
    }
    
    public void debitarCartaoCredito(double limite, double valor) throws PagamentoRecusadoException{
        if((limite-valor) < 0){
            throw new PagamentoRecusadoException("Cartao de Credito sem limite");
        }
    }
    
    public void cadastrarPagamento(String idViagem, FormaDePagamento pagamento){
        repoPagamentos.adicionar(idViagem, pagamento);
    }
    
    public void pagarComDinheiro(double valor){
        System.out.println("== PAGUE DIRETAMENTE AO MOTORISTA ==");
        System.out.println("valor: R$"+valor);
    }
}