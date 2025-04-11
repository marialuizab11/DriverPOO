package dados;

import java.io.*;
import java.util.*;
import negocios.basicas.*;

/**
 * Implemetacao concreta de {@link IRepositorioPagamentos} que gerencia as formas de pagamento associadas a uma viagem, com persistencia nos dados.
 * 
 * @author Maria Luiza Bezerra
 */
public class RepositorioPagamentos implements IRepositorioPagamentos {
    /*Criacao do Map (dicionario) em que a chave para o pagamento sera um String que eh o id da viagem.*/
    private Map<String, FormaDePagamento> pagamentos;
    private Pix pix;
    
    private static final String PASTA_DADOS = "data/"; 
    private static final String ARQ_PAGAMENTOS = PASTA_DADOS + "pagamentos.ser";

    /* Constroi um novo repositorio, criando uma pasta de dados, se necessario, para carregar os dados persistentes se existirem.*/
    public RepositorioPagamentos() {
        criarPastaDados();
        this.pagamentos = carregar();
    }
    
    /*Cria o diretorio para armazenamento dos dados se nao existir*/
    private void criarPastaDados(){
        new File("data/").mkdirs();
    }
    
    /* Carrega os pagamentos persistidos do arquivo. Retorna um Map (dicionario) contendo os pagamentos carregador(se existirem)*/
    @SuppressWarnings("unchecked")
    private Map<String, FormaDePagamento> carregar(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARQ_PAGAMENTOS))){
            return (Map<String, FormaDePagamento>) in.readObject();
        } catch(IOException | ClassNotFoundException e){
            return new HashMap<>();
        }
    }
    
    /*Persiste o Map de pagamentos no ARQ_PAGAMENTOS.*/
    private void salvar(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQ_PAGAMENTOS))){
            out.writeObject(pagamentos);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
      
    /* Adiciona a forma de pagamento da viagem. */
    @Override
    public void adicionar(String idViagem, FormaDePagamento pagamento) {
        pagamentos.put(idViagem, pagamento);
        salvar();
    }
    
    /* Gera automaticamente um estilo de qr code de pix para o pagamento da viagem.*/
    public String gerarPix(double valor){
        return UUID.randomUUID().toString()+"DRIVERPOO-COMPANY"+valor;
    }
    
}