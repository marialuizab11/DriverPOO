package dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import negocios.basicas.CartaoCredito;
import negocios.basicas.FormaDePagamento;

/**
 * @author Maria Luiza Bezerra
 */
public class RepositorioPagamentos implements IRepositorioPagamentos {

    private Map<String, List<FormaDePagamento>> formasPagamento;
    
    private static final String ARQ_PAGAMENTOS = "pagamentos.dat";

    public RepositorioPagamentos(Map<String, List<FormaDePagamento>> formasPagamento) {
        this.formasPagamento = new HashMap<>();
        carregarDados();
    }
    
    private void carregarDados(){
        try{
            FileInputStream fis = new FileInputStream(ARQ_PAGAMENTOS);
            ObjectInputStream ois = new ObjectInputStream(fis);
            formasPagamento = (Map<String, List<FormaDePagamento>>) ois.readObject();
            ois.close();
        } catch(IOException | ClassNotFoundException e){
            e.getMessage();
        }
    }
    
    private void salvarDados(){
        try{
            FileOutputStream fos = new FileOutputStream(ARQ_PAGAMENTOS);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(formasPagamento);
            oos.close();
        } catch (IOException e){
            e.getMessage();
        }
    }
      
    @Override
    public void adicionar(String cpfCliente, FormaDePagamento formaPagamento) {
        if(formaPagamento!= null && cpfCliente != null){
            formasPagamento.computeIfAbsent(cpfCliente, k-> new ArrayList<>());
            formasPagamento.get(cpfCliente).add(formaPagamento);
            salvarDados();
        }
    }

    @Override
    public void removerCartao(String cpf, String numeroCartao) {
        if(formasPagamento.containsKey(cpf)){
            List<FormaDePagamento> formas = formasPagamento.get(cpf);
            
            formas.removeIf(forma -> forma instanceof CartaoCredito && ((CartaoCredito)forma).getNumero().equals(numeroCartao));
            salvarDados();
        }
        
    }

    @Override
    public List<CartaoCredito> listarCartoes(String cpf) {
        List<CartaoCredito> cartoes = new ArrayList<>();
        
        if(formasPagamento.containsKey(cpf)){
            for(FormaDePagamento forma : formasPagamento.get(cpf)){
                if(forma instanceof CartaoCredito){
                    cartoes.add((CartaoCredito) forma);
                }
            }
        }
        
        return cartoes;
    }
    
}