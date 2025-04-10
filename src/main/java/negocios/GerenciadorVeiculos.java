package negocios;

import dados.*;
import negocios.basicas.*;
import negocios.excecoes.*;

/**
 * @author Maria Luiza Bezerra
 */
public class GerenciadorVeiculos {
    RepositorioVeiculo repoVeiculos;
    GerenciadorPessoa gerenciadorPessoa;
    
    public GerenciadorVeiculos(RepositorioVeiculo repoVeiculos, GerenciadorPessoa gerenciadorPessoa){
        this.repoVeiculos = repoVeiculos;
        this.gerenciadorPessoa = gerenciadorPessoa;
    }
    
    public void verificarExistencia(Veiculo veiculo) throws VeiculoJaCadastradoException{
        if(repoVeiculos.buscarPorPlaca(veiculo.getPlaca()) != null){
            throw new VeiculoJaCadastradoException("Veiculo ja cadastrado no sistema");
        }
    }
    
    public void cadastrarMotocicleta(String cnh, String placa, int capacidade, String modelo){  
        try{
            Motorista motorista = gerenciadorPessoa.buscarMotorista(cnh);
            Motocicleta moto = new Motocicleta(placa, capacidade, modelo, motorista.getId());
            verificarExistencia(moto);
            
            motorista.setIdVeiculo(moto.getId());
            repoVeiculos.adicionar(moto);
            gerenciadorPessoa.adicionarVeiculoMotorista(motorista, moto.getId());
        } catch(PessoaNaoEncontradaException | VeiculoJaCadastradoException e){
            e.getMessage();
        }        
    }
    
    public void cadastrarSUV(String cnh, String placa, int capacidade, String modelo){  
        try{
            Motorista motorista = gerenciadorPessoa.buscarMotorista(cnh);
            SUV suv = new SUV(placa, capacidade, modelo, motorista.getId());
            verificarExistencia(suv);
            
            motorista.setIdVeiculo(suv.getId());
            repoVeiculos.adicionar(suv);
            gerenciadorPessoa.adicionarVeiculoMotorista(motorista, suv.getId());
        } catch(VeiculoJaCadastradoException e){
            e.getMessage();
        }        
    }
    
    public void cadastrarLuxo(String cnh, String placa, int capacidade, String modelo){  
        try{
            Motorista motorista = gerenciadorPessoa.buscarMotorista(cnh);
            Luxo luxo = new Luxo(placa, capacidade, modelo, motorista.getId());
            verificarExistencia(luxo);
            
            motorista.setIdVeiculo(luxo.getId());
            repoVeiculos.adicionar(luxo);
            gerenciadorPessoa.adicionarVeiculoMotorista(motorista, luxo.getId());
        } catch(PessoaNaoEncontradaException | VeiculoJaCadastradoException e){
            e.getMessage();
        }        
    }
    
    public void cadastrarEconomico(String cnh, String placa, int capacidade, String modelo){  
        try{
            Motorista motorista = gerenciadorPessoa.buscarMotorista(cnh);
            Economico economico = new Economico(placa, capacidade, modelo, motorista.getId());
            verificarExistencia(economico);
            
            motorista.setIdVeiculo(economico.getId());
            repoVeiculos.adicionar(economico);
            gerenciadorPessoa.adicionarVeiculoMotorista(motorista, economico.getId());
        } catch(PessoaNaoEncontradaException | VeiculoJaCadastradoException e){
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