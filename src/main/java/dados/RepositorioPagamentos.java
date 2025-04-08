package dados;

import java.io.*;
import java.util.*;
import negocios.basicas.CartaoCredito;
import negocios.basicas.FormaDePagamento;

/**
 * @author Maria Luiza Bezerra
 */
public class RepositorioPagamentos implements IRepositorioPagamentos {

    private Map<String, List<FormaDePagamento>> formasPagamento;
    
    private static final String ARQ_PAGAMENTOS = "pagamentos.dat";

    public RepositorioPagamentos() {
        criarPastaDados();
        this.formasPagamento = carregar();
    }
    
    private void criarPastaDados(){
        new File("data/").mkdirs();
    }
    
    @SuppressWarnings("unchecked")
    private Map<String, List<FormaDePagamento>> carregar(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARQ_PAGAMENTOS))){
            return (Map<String, List<FormaDePagamento>>) in.readObject();
        } catch(IOException | ClassNotFoundException e){
            return new HashMap<>();
        }
    }
    
    private void salvar(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQ_PAGAMENTOS))){
            out.writeObject(formasPagamento);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
      
    @Override
    public void adicionar(String cpfCliente, FormaDePagamento formaPagamento) {
        if(formaPagamento!= null && cpfCliente != null){
            formasPagamento.computeIfAbsent(cpfCliente, k-> new ArrayList<>()).add(formaPagamento);
            salvar();
        }
    }

    @Override
    public void removerCartao(String cpf, String numeroCartao) {
        if(formasPagamento.containsKey(cpf)){
            List<FormaDePagamento> formas = formasPagamento.get(cpf);
            
            List<FormaDePagamento> formasAtualizadas = new ArrayList<>();
            
            for(FormaDePagamento forma : formas){
                if(forma instanceof CartaoCredito){
                    CartaoCredito cartao = (CartaoCredito) forma;
                    
                    if(!cartao.getNumero().equals(numeroCartao)){
                        formasAtualizadas.add(cartao);
                    }
                } else {
                    formasAtualizadas.add(forma);
                }
            }
            formasPagamento.put(cpf, formasAtualizadas);
            
            salvar();
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