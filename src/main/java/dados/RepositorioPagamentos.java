package dados;

import java.io.*;
import java.util.*;
import negocios.basicas.*;

/**
 * @author Maria Luiza Bezerra
 */
public class RepositorioPagamentos implements IRepositorioPagamentos {
    private Map<String, FormaDePagamento> pagamentos;
    private Pix pix;
    
    private static final String PASTA_DADOS = "data/"; 
    private static final String ARQ_PAGAMENTOS = PASTA_DADOS + "pagamentos.ser";

    public RepositorioPagamentos() {
        criarPastaDados();
        this.pagamentos = carregar();
    }
    
    private void criarPastaDados(){
        new File("data/").mkdirs();
    }
    
    @SuppressWarnings("unchecked")
    private Map<String, FormaDePagamento> carregar(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARQ_PAGAMENTOS))){
            return (Map<String, FormaDePagamento>) in.readObject();
        } catch(IOException | ClassNotFoundException e){
            return new HashMap<>();
        }
    }
    
    private void salvar(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQ_PAGAMENTOS))){
            out.writeObject(pagamentos);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
      
    @Override
    public void adicionar(String idViagem, FormaDePagamento pagamento) {
        pagamentos.put(idViagem, pagamento);
        salvar();
    }
    
    public String gerarPix(double valor){
        return UUID.randomUUID().toString()+"DRIVERPOO-COMPANY"+valor;
    }
    
}