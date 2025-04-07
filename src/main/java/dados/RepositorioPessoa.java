package dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import negocios.basicas.Cliente;
import negocios.basicas.FormaDePagamento;
import negocios.basicas.Motorista;
import negocios.basicas.Pessoa;
import negocios.basicas.Veiculo;

/**
 * @author Maria Luiza Bezerra
 */
public class RepositorioPessoa implements IRepositorioPessoa {
    private List<Cliente> clientes;
    private List<Motorista> motoristas;
    private List<Veiculo> veiculos;
    
    private IRepositorioPagamentos repoPagamentos;
    
    private static final String ARQ_CLIENTES = "clientes.dat";
    private static final String ARQ_MOTORISTAS = "motoristas.dat";
    private static final String ARQ_VEICULOS = "veiculos.dat";

    public RepositorioPessoa(){
        this.clientes = new ArrayList<>();
        this.motoristas = new ArrayList<>();
        this.veiculos = new ArrayList<>();
        carregarDados();
    }
    
    private void carregarDados(){
        try{
            FileInputStream fis = new FileInputStream(ARQ_CLIENTES);
            ObjectInputStream ois = new ObjectInputStream(fis);
            clientes = (List<Cliente>) ois.readObject();
            ois.close();
            
            fis = new FileInputStream(ARQ_MOTORISTAS);
            ois = new ObjectInputStream(fis);
            motoristas = (List<Motorista>) ois.readObject();
            ois.close();
            
            fis = new FileInputStream(ARQ_VEICULOS);
            ois = new ObjectInputStream(fis);
            veiculos = (List<Veiculo>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    private void salvarDados(){
        try{
            FileOutputStream fos = new FileOutputStream(ARQ_CLIENTES);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(clientes);
            oos.close();
            
            fos = new FileOutputStream(ARQ_MOTORISTAS);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(motoristas);
            oos.close();
            
            fos = new FileOutputStream(ARQ_VEICULOS);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(veiculos);
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void adicionarCliente(Cliente cliente) {
        if(cliente!=null && !existePessoa(cliente.getCpf())){
            clientes.add(cliente);
            salvarDados();
        }
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        for (Cliente cl : clientes){
            if(cl.getCpf().equals(cpf)){
                return cl;
            }
        }
        return null;
    }

    @Override
    public void adicionarMotorista(Motorista motorista) {
        if(motorista != null && !existePessoa(motorista.getCpf())){
            motoristas.add(motorista);
            salvarDados();
        }
    }

    @Override
    public Motorista buscarPorCnh(String cnh) {
        for(Motorista mt: motoristas){
            if(mt.getCnh().equals(cnh)){
                return mt;
            }
        }
        return null;
    }

    @Override
    public void adicionarVeiculoMotorista(Veiculo veiculo) {
       if(veiculo != null){
           veiculos.add(veiculo);
           salvarDados();
       }
    }

    @Override
    public boolean atualizar(String cpf, String email, String telefone) {
        Pessoa pessoa = buscarPorCpf(cpf);
        if(pessoa!=null){
            pessoa.setEmail(email);
            pessoa.setTelefone(telefone);
            salvarDados();
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean removerCliente(String cpf) {
        Pessoa pessoa = buscarPorCpf(cpf);
        if(pessoa!=null){
            clientes.remove(pessoa);
            salvarDados();
            return true;
        } else{
            return false;
        }
    }
    
    @Override
    public boolean removerMotorista(String cpf){
        Pessoa pessoa = buscarPorCpf(cpf);
        if(pessoa!=null){
            motoristas.remove(pessoa);
            salvarDados();
            return true;
        } else{
            return false;
        }
    }
    
    private boolean existePessoa(String cpf){
        return buscarPorCpf(cpf)!=null;
    }
    
    public void adicionarFormaPagamentoCliente(FormaDePagamento pagamento, String cpf){
        repoPagamentos.adicionar(cpf ,pagamento);
    }
}