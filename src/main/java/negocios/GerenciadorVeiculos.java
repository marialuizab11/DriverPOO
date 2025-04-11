package negocios;

import dados.*;
import negocios.basicas.*;
import negocios.excecoes.*;

/**
 * Controla as operacoes relacionadas a veiculos no sistema, incluindo cadastro, exclusao e verificacao de categorias.
 * 
 * @author Maria Luiza Bezerra
 */
public class GerenciadorVeiculos {
    RepositorioVeiculo repoVeiculos;
    GerenciadorPessoa gerenciadorPessoa;
    
    /**
     * Constroi um novo gerenciador de veiculos.
     * 
     * @param repoVeiculos
     * @param gerenciadorPessoa 
     */
    public GerenciadorVeiculos(RepositorioVeiculo repoVeiculos, GerenciadorPessoa gerenciadorPessoa){
        this.repoVeiculos = repoVeiculos;
        this.gerenciadorPessoa = gerenciadorPessoa;
    }
    
    /**
     * Verifica se o veiculo ja existe no sistema.
     * 
     * @param veiculo
     * @throws VeiculoJaCadastradoException 
     */
    public void verificarExistencia(Veiculo veiculo) throws VeiculoJaCadastradoException{
        if(repoVeiculos.buscarPorPlaca(veiculo.getPlaca()) != null){
            throw new VeiculoJaCadastradoException("Veiculo ja cadastrado no sistema");
        }
    }
    
    /**
     * Cadastra um veiculo do tipo "Motocicleta"
     * @param motorista
     * @param placa
     * @param capacidade
     * @param modelo 
     */
    public void cadastrarMotocicleta(Motorista motorista, String placa, int capacidade, String modelo){  
        try{
            Motocicleta moto = new Motocicleta(placa, capacidade, modelo, motorista.getId());
            verificarExistencia(moto);
            
            motorista.setIdVeiculo(moto.getId());
            repoVeiculos.adicionar(moto);
            gerenciadorPessoa.adicionarVeiculoMotorista(motorista, moto.getId());
        } catch(PessoaNaoEncontradaException | VeiculoJaCadastradoException e){
            e.getMessage();
        }        
    }
    
    /**
     * * Cadastra um veiculo do tipo "SUV"
     * @param motorista
     * @param placa
     * @param capacidade
     * @param modelo 
     */
    public void cadastrarSUV(Motorista motorista, String placa, int capacidade, String modelo){  
        try{
            SUV suv = new SUV(placa, capacidade, modelo, motorista.getId());
            verificarExistencia(suv);
            
            motorista.setIdVeiculo(suv.getId());
            repoVeiculos.adicionar(suv);
            gerenciadorPessoa.adicionarVeiculoMotorista(motorista, suv.getId());
        } catch(VeiculoJaCadastradoException e){
            e.getMessage();
        }        
    }
    
    /**
     * * Cadastra um veiculo do tipo "Luxo"
     * @param motorista
     * @param placa
     * @param capacidade
     * @param modelo 
     */
    public void cadastrarLuxo(Motorista motorista, String placa, int capacidade, String modelo){  
        try{
            Luxo luxo = new Luxo(placa, capacidade, modelo, motorista.getId());
            verificarExistencia(luxo);
            
            motorista.setIdVeiculo(luxo.getId());
            repoVeiculos.adicionar(luxo);
            gerenciadorPessoa.adicionarVeiculoMotorista(motorista, luxo.getId());
        } catch(PessoaNaoEncontradaException | VeiculoJaCadastradoException e){
            e.getMessage();
        }        
    }
    
    /**
     * * Cadastra um veiculo do tipo "Economico"
     * @param motorista
     * @param placa
     * @param capacidade
     * @param modelo 
     */
    public void cadastrarEconomico(Motorista motorista, String placa, int capacidade, String modelo){  
        try{
            Economico economico = new Economico(placa, capacidade, modelo, motorista.getId());
            verificarExistencia(economico);
            
            motorista.setIdVeiculo(economico.getId());
            repoVeiculos.adicionar(economico);
            gerenciadorPessoa.adicionarVeiculoMotorista(motorista, economico.getId());
        } catch(PessoaNaoEncontradaException | VeiculoJaCadastradoException e){
            e.getMessage();
        }        
    }
    
    /**
     * Exclui um veiculo do sistema.
     * @param placa
     * @throws VeiculoNaoExisteException 
     */
    public void excluir(String placa) throws VeiculoNaoExisteException{
        Veiculo veiculo = repoVeiculos.buscarPorPlaca(placa);
        if(veiculo == null){
            throw new VeiculoNaoExisteException("Placa incompativel. Veiculo nao existe no sistema");
        }
        repoVeiculos.remover(placa);
    }
    
    /**
     * Verifica em qual categoria o veiculo sera inserido
     * @param opVeiculo
     * @return String com a categoria do veiculo
     * @throws CategoriaVeiculoNaoValidaException Se o numero nao for inteiro entre 1 e 4.
     */
    public String verificarCategoria(int opVeiculo) throws CategoriaVeiculoNaoValidaException{
        String categoria;
        switch (opVeiculo) {
            case 1 -> categoria = "MOTOCICLETA";
            case 2 -> categoria = "ECONOMICO";
            case 3 -> categoria = "SUV";
            case 4 -> categoria = "LUXO";
            default -> throw new CategoriaVeiculoNaoValidaException("Categoria de veiculo nao valida.");
        }
        
        return categoria;
    }
    
}