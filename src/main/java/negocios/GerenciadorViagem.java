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
    
    public double calcularValorTotal(String categoria) {
    Veiculo veiculoFicticio;

    switch (categoria) {
        case "MOTOCICLETA":
            veiculoFicticio = new Motocicleta("default", 0, "modelo", 0);
            break;
        case "ECONOMICO":
            veiculoFicticio = new Economico("default", 0, "modelo", 0);
            break;
        case "SUV":
            veiculoFicticio = new SUV("default", 0, "modelo", 0);
            break;
        case "LUXO":
            veiculoFicticio = new Luxo("default", 0, "modelo", 0);
            break;
        default:
            return 30; 
            
    }
    
    return (20 + (20 * veiculoFicticio.getTaxaFixa())); 
}

    
    public Viagem solicitarViagemPassageiro(Cliente cliente, Origem origem, Destino destino, String categoria, double valorViagem) {
        ViagemPassageiro viagem = new ViagemPassageiro(origem, destino, null, cliente, null, categoria, valorViagem);
        repoViagem.adicionar(viagem);
        return viagem;
    }
    
    public Viagem solicitarViagemEntrega(Cliente cliente, Origem origem, Destino destino, double pesoPacoteKg, String categoria) throws VeiculoNaoIdealException{
        if(categoria.equalsIgnoreCase("MOTOCICLETA") && pesoPacoteKg > 5){
            throw new VeiculoNaoIdealException("Motocicleta nao entrega pacotes com mais de 5kg!");
        }        
        double valorViagem = calcularValorTotal(categoria);
        
        ViagemEntrega viagem = new ViagemEntrega(origem, destino, null, null, cliente, categoria, pesoPacoteKg, valorViagem);
        repoViagem.adicionar(viagem);
        return viagem;
    }
    
    public void iniciarViagem(Viagem viagem, String cnh){
        Motorista motorista = gerenciadorPessoa.buscarMotorista(cnh);
        Veiculo veiculo = repoVeiculo.buscarPorId(motorista.getIdVeiculo());
        
        gerenciadorPessoa.verificarDisponibilidade(motorista);
        
        repoViagem.aceitarViagem(viagem, motorista, veiculo);
        System.out.println("== VOCE ESTA INDO PARA: ==");
        System.out.println(viagem.getDestino().getNome());
    }
    
    public void encerrarViagem(Viagem viagem){
        viagem.getMotorista().setDisponivel(true);
    }
    
    public List<Viagem> getViagensNaoAceitasPorCategoria(String categoria){
        List<Viagem> disponiveis = new ArrayList<>();
        for (Viagem v : repoViagem.getTodas()){
            if(!v.isAceita() && v.getCategoriaVeiculo().equalsIgnoreCase(categoria)){
                disponiveis.add(v);
            }
        }
        return disponiveis;
    }
    
    public boolean mostrarViagensDisponiveisParaMotoristas(Motorista motorista) {        
        gerenciadorPessoa.verificarValidacaoMotorista(motorista);
        gerenciadorPessoa.verificarVeiculoMotorista(motorista);
        
        Veiculo veiculo = repoVeiculo.buscarPorId(motorista.getIdVeiculo());
        
        String categoria = veiculo.getCategoria();
        List<Viagem> disponiveis = getViagensNaoAceitasPorCategoria(categoria);
        
        if(disponiveis.isEmpty()){
            System.out.println("Nao ha viagens disponiveis");
            return false;
        } else{
            System.out.println("VIAGENS DISPONIVEIS");
            for(Viagem vg : disponiveis){        
                System.out.println("ID: "+vg.getId());
                System.out.println("Origem: "+vg.getOrigem().getNome());
                System.out.println("Destino: "+vg.getDestino().getNome());
                System.out.println("Valor da corrida: R$"+vg.getValorTotal());
            }
        }
        return true;
    }
    
    public void avaliarMotorista(int id, int estrelas, String descricao){
        Viagem viagem = repoViagem.buscarViagem(id);
        Avaliacao avaliacaoCliente = new Avaliacao(descricao, estrelas);
        repoViagem.adicionarAvaliacaoCliente(viagem, avaliacaoCliente);
    }
    
    public void avaliarCliente(Viagem viagem, int estrelas, String descricao){
        Avaliacao avaliacaoMotorista = new Avaliacao(descricao, estrelas);
        repoViagem.adicionarAvaliacaoMotorista(viagem, avaliacaoMotorista);
    }
    
    public void mostrarViagensDeMotorista(Motorista motorista) throws PessoaSemViagensException{
        boolean temViagens = repoViagem.listarViagensDeMotorista(motorista);
        if(!temViagens){
            throw new PessoaSemViagensException("Motorista ainda nao realizou nenhuma viagem");
        }
    }
    
    public void mostrarViagensFeitasCliente(Cliente cliente) throws PessoaSemViagensException{
        boolean temViagens = repoViagem.listarViagensFeitasDeCliente(cliente);
        if(!temViagens){
            throw new PessoaSemViagensException("Cliente nao fez nenhuma viagem");
        }
    }
}