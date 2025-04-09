package negocios;

import dados.RepositorioPagamentos;
import dados.RepositorioViagem;
import negocios.basicas.*;
import negocios.excecoes.*;

/**
 * @author Maria Luiza Bezerra
 */
public class GerenciadorViagem {
    RepositorioViagem repoViagem = new RepositorioViagem();
    GerenciadorPagamentos gerenciadorPagamentos;
    GerenciadorPessoa gerenciadorPessoa;
    
    
    public void adicionarPagamentoViagem(int id, FormaDePagamento pagamento) throws ViagemNaoEncontradaException {
        if(repoViagem.buscarViagem(id) == null){
            throw new ViagemNaoEncontradaException("Viagem nao encontrada no sistema");
        }
        String idViagem = String.valueOf(id);
        gerenciadorPagamentos.cadastrarPagamento(idViagem, pagamento);
    }
    
    public void solicitarViagemPassageiro(Origem origem, Destino destino, Motorista motorista, Veiculo veiculo) {
        ViagemPassageiro novaViagem = new ViagemPassageiro(origem, destino, motorista, veiculo);
        repoViagem.adicionar(novaViagem);
    }
    
    public void solicitarViagemEntrega(Origem origem, Destino destino, Motorista motorista, Veiculo veiculo, double pesoPacoteKg){
        ViagemEntrega novaViagem = new ViagemEntrega(origem, destino, motorista, veiculo, pesoPacoteKg);
        repoViagem.adicionar(novaViagem);
    }
    
    public void iniciarViagem(Viagem viagem, FormaDePagamento pagamento){
        adicionarPagamentoViagem(viagem.getId(), pagamento);
        
        try{
            gerenciadorPessoa.verificaDisponibilidade(viagem.getMotorista());
        } catch (MotoristaNaoDisponivelException e){
            e.getMessage();
        }
        
        System.out.println("== VOCE ESTA INDO PARA: ==");
        System.out.println(viagem.getDestino());
        System.out.println("APROVEITE, CHEGAREMOS EM POUCOS MINUTOS...");
    }
    
    public void encerrarViagem(Viagem viagem){
        System.out.println("== VOCE CHEGOU AO SEU DESTINO ==");
        System.out.println("OBRIGADA POR ESCOLHER A DRIVERPOO!");
        viagem.getMotorista().setDisponivel(true);
    }
    
    public void pagarViagemPorPix(double valor, String confirmacao) {
        try{
            String chavePix = gerenciadorPagamentos.gerarChavePix(valor);
            System.out.println("PAGUE AGORA:");
            System.out.println(chavePix);
            System.out.println("== ESPERANDO CONFIRMACAO ==");
            gerenciadorPagamentos.validarPix(confirmacao);
        } catch(PagamentoRecusadoException e){
            e.getMessage();
        }
    }
    
    public void pagarViagemCartao (CartaoCredito cartao){
        try{
            gerenciadorPagamentos.debitarCartaoCredito(cartao.getLimite(), cartao.getValor());
        } catch(PagamentoRecusadoException e){
            e.getMessage();
        }
    }
}