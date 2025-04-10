package negocios;

import dados.*;
import java.util.ArrayList;
import java.util.List;
import negocios.basicas.*;
import negocios.excecoes.*;

/**
 * @author Maria Luiza Bezerra
 */
public class GerenciadorViagem {
    RepositorioViagem repoViagem;
    RepositorioVeiculo repoVeiculo;
    GerenciadorPagamentos gerenciadorPagamentos;
    GerenciadorPessoa gerenciadorPessoa;
    
    public GerenciadorViagem(RepositorioViagem repoViagem, RepositorioVeiculo repoVeiculo, GerenciadorPagamentos gerenciadorPagamentos, GerenciadorPessoa gerenciadorPessoa){
        this.repoViagem = repoViagem;
        this.repoVeiculo = repoVeiculo;
        this.gerenciadorPagamentos = gerenciadorPagamentos;
        this.gerenciadorPessoa = gerenciadorPessoa;        
    }
    
    public void adicionarPagamentoViagem(int id, FormaDePagamento pagamento) throws ViagemNaoEncontradaException {
        if(repoViagem.buscarViagem(id) == null){
            throw new ViagemNaoEncontradaException("Viagem nao encontrada no sistema");
        }
        String idViagem = String.valueOf(id);
        gerenciadorPagamentos.cadastrarPagamento(idViagem, pagamento);
        
    }
    
    private double calcularValorTotal(String categoria) {
    Veiculo veiculoFicticio;

    switch (categoria.toLowerCase()) {
        case "Motocicleta":
            veiculoFicticio = new Motocicleta("dummy", 0, "modelo", 0);
            break;
        case "Economico":
            veiculoFicticio = new Economico("dummy", 0, "modelo", 0);
            break;
        case "SUV":
            veiculoFicticio = new SUV("dummy", 0, "modelo", 0);
            break;
        case "Luxo":
            veiculoFicticio = new Luxo("dummy", 0, "modelo", 0);
            break;
        default:
            return 30; 
    }
    System.out.println(veiculoFicticio.getTaxaFixa());
    return 30 * veiculoFicticio.getTaxaFixa(); 
}

    
    public double solicitarViagemPassageiro(Origem origem, Destino destino, String categoria) {
        double valorViagem = calcularValorTotal(categoria);
        ViagemPassageiro viagem = new ViagemPassageiro(origem, destino, null, null, categoria, valorViagem);
        repoViagem.adicionar(viagem);
        
        return valorViagem;
    }
    
    public double solicitarViagemEntrega(Origem origem, Destino destino, double pesoPacoteKg, String categoria) throws VeiculoNaoIdealException{
        if(categoria.equalsIgnoreCase("Motocicleta") && pesoPacoteKg > 5){
            throw new VeiculoNaoIdealException("Motocicleta nao entrega pacotes com mais de 5kg!");
        }
        
        double valorViagem = calcularValorTotal(categoria);
        
        ViagemEntrega viagem = new ViagemEntrega(origem, destino, null, null, categoria, pesoPacoteKg, valorViagem);
        repoViagem.adicionar(viagem);
        return valorViagem;
    }
    
    public void iniciarViagem(Viagem viagem, FormaDePagamento pagamento){
        adicionarPagamentoViagem(viagem.getId(), pagamento);
        
        try{
            gerenciadorPessoa.verificarDisponibilidade(viagem.getMotorista());
        } catch (MotoristaNaoDisponivelException e){
            e.getMessage();
        }
        
        System.out.println("== VOCE ESTA INDO PARA: ==");
        System.out.println(viagem.getDestino());
        System.out.println("APROVEITE, CHEGAREMOS EM POUCOS MINUTOS...");
    }
    
    public void encerrarViagem(Viagem viagem, int estrelas, String descricao){
        Avaliacao avaliacao = new Avaliacao(descricao, estrelas);
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
    
    public List<Viagem> getViagensNaoAceitasPorCategoria(String categoria){
        List<Viagem> disponiveis = new ArrayList<>();
        for (Viagem v : repoViagem.getTodas()){
            if(!v.isAceita() && v.getCategoriaVeiculo().equals(categoria)){
                disponiveis.add(v);
            }
        }
        return disponiveis;
    }
    
    public void mostrarViagensDisponiveisParaMotoristas(String cnh) {
        Motorista motorista = gerenciadorPessoa.buscarMotorista(cnh);
        gerenciadorPessoa.verificarValidacaoMotorista(motorista);
        gerenciadorPessoa.verificarVeiculoMotorista(motorista);
        
        int idVeiculo = motorista.getIdVeiculo();
        Veiculo veiculo = repoVeiculo.buscarPorId(idVeiculo);
        
        String categoria = veiculo.getCategoria();
        List<Viagem> disponiveis = getViagensNaoAceitasPorCategoria(categoria);
        
        if(disponiveis.isEmpty()){
            System.out.println("Nao ha viagens disponiveis");
        } else{
            System.out.println("VIAGENS DISPONIVEIS");
            for(Viagem vg : disponiveis){        
                System.out.println("ID: "+vg.getId());
                System.out.println("Origem: "+vg.getOrigem().getNome());
                System.out.println("Destino: "+vg.getDestino().getNome());
                System.out.println("Valor da corrida: R$"+vg.getValorTotal());
            }
        }
    }
}