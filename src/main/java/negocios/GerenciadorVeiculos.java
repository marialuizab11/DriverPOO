package negocios;

import dados.RepositorioVeiculo;
import negocios.basicas.*;
import negocios.excecoes.*;

/**
 * @author Maria Luiza Bezerra
 */
public class GerenciadorVeiculos {
    RepositorioVeiculo repoVeiculos = new RepositorioVeiculo();
    GerenciadorPessoa gerenciadorPessoa;
    
    public void verificarExistencia(Veiculo veiculo) throws VeiculoJaCadastradoException{
        if(repoVeiculos.buscarPorPlaca(veiculo.getPlaca()) == null){
            throw new VeiculoJaCadastradoException("Veiculo ja cadastrado no sistema");
        }
    }
    
    public void cadastrarMotocicleta(String cnh, String placa, int capacidade, String modelo){  
        try{
            Motocicleta moto = new Motocicleta(placa, capacidade, modelo);
            verificarExistencia(moto);
            Motorista motorista = gerenciadorPessoa.buscarMotorista(cnh);
            repoVeiculos.adicionar(motorista, moto);
        } catch(PessoaNaoEncontradaException e){
            e.getMessage();
        }        
    }
    
    public void cadastrarSUV(String cnh, String placa, int capacidade, String modelo){  
        try{
            SUV suv = new SUV(placa, capacidade, modelo);
            verificarExistencia(suv);
            Motorista motorista = gerenciadorPessoa.buscarMotorista(cnh);
            repoVeiculos.adicionar(motorista, suv);
        } catch(PessoaNaoEncontradaException e){
            e.getMessage();
        }        
    }
    
    public void cadastrarLuxo(String cnh, String placa, int capacidade, String modelo){  
        try{
            Luxo luxo = new Luxo(placa, capacidade, modelo);
            verificarExistencia(luxo);
            Motorista motorista = gerenciadorPessoa.buscarMotorista(cnh);
            repoVeiculos.adicionar(motorista, luxo);
        } catch(PessoaNaoEncontradaException e){
            e.getMessage();
        }        
    }
    
    public void cadastrarEconomico(String cnh, String placa, int capacidade, String modelo){  
        try{
            Economico economico = new Economico(placa, capacidade, modelo);
            verificarExistencia(economico);
            Motorista motorista = gerenciadorPessoa.buscarMotorista(cnh);
            repoVeiculos.adicionar(motorista, economico);
        } catch(PessoaNaoEncontradaException e){
            e.getMessage();
        }        
    }
    
    public void excluir(String placa) throws VeiculoNaoExisteException{
        Veiculo veiculo = repoVeiculos.buscarPorPlaca(placa);
        if(veiculo == null){
            throw new VeiculoNaoExisteException("Placa incompativel. Veiculo nao existe no sistema");
        }
        repoVeiculos.remover(placa);
    }
    
}