package ui;

import dados.*;
import java.util.List;
import java.util.Scanner;
import negocios.*;
import negocios.basicas.*;
import negocios.excecoes.*;
/**
 *
 * @author Maria Luiza Bezerra
 */
public class DriverPOO {

    public static void main(String[] args) {
        Scanner ent = new Scanner(System.in);
        int op;
        
        RepositorioPessoa repoPessoa = new RepositorioPessoa();
        RepositorioVeiculo repoVeiculo = new RepositorioVeiculo();
        RepositorioViagem repoViagem = new RepositorioViagem();
        RepositorioPagamentos repoPagamento = new RepositorioPagamentos();
        
        GerenciadorPessoa gerenciadorPessoa = new GerenciadorPessoa(repoPessoa);
        GerenciadorVeiculos gerenciadorVeiculos = new GerenciadorVeiculos(repoVeiculo, gerenciadorPessoa);;
        GerenciadorPagamentos gerenciadorPagamentos = new GerenciadorPagamentos(repoPagamento);
        GerenciadorViagem gerenciadorViagem = new GerenciadorViagem(repoViagem, repoVeiculo, gerenciadorPagamentos, gerenciadorPessoa);
        
        String nome, cpf, cnh, email, telefone;
        
        System.out.println("\n== BEM VINDO AO DRIVERPOO ==");
        
        //Para debug
        repoPessoa.listarClientes();
        repoPessoa.listarMotoristas();
        repoVeiculo.listarVeiculos();
        repoViagem.listarViagens();
        
        do{
            System.out.println("\n= LOGIN =");
            System.out.println("Digite 1 se voce eh CLIENTE");
            System.out.println("Digite 2 se voce eh MOTORISTA");
            System.out.println("0 - Sair");
            op = ent.nextInt();
            ent.nextLine();
            
            try{
                if(op == 1){
                    do{
                        System.out.println("\n= CLIENTE DRIVERPOO =");
                        System.out.println("1 - Cadastro de novo cliente");
                        System.out.println("2 - Atualizar informacoes de cliente");
                        System.out.println("3 - Excluir conta de cliente");
                        System.out.println("4 - Solicitar viagem de entrega");
                        System.out.println("5 - Solicitar viagem de corrida");
                        System.out.println("99 - Voltar para login");
                        op = ent.nextInt();
                        ent.nextLine();

                        switch(op){
                            case 1:
                                System.out.println("CADASTRO DE NOVO CLIENTE");
                                System.out.print("Nome: ");
                                nome = ent.nextLine();
                                System.out.print("CPF (sem pontos ou tracos, apenas numeros: " );
                                cpf = ent.nextLine();
                                System.out.print("Email: ");
                                email = ent.nextLine();
                                System.out.print("Telefone (sem tracos ou espacos, apenas numeros): ");
                                telefone = ent.nextLine();

                                gerenciadorPessoa.cadastrarCliente(nome, email, telefone, cpf);
                                System.out.print("\nSucesso! Conta criada.");
                                repoPessoa.listarClientes();
                                break;
                            case 2:
                                System.out.println("ATUALIZA INFORMACOES");
                                System.out.print("Seu cpf de cadastro: ");
                                cpf = ent.nextLine();
                                System.out.print("Novo email: ");
                                email = ent.nextLine();
                                System.out.print("Novo telefone: ");
                                telefone = ent.nextLine();
                                
                                gerenciadorPessoa.atualizarCliente(cpf, email, telefone);
                                System.out.print("Sucesso! Informacoes atualizadas.");
                                repoPessoa.listarClientes();
                                break;
                            case 3:
                                System.out.println("EXCLUIR CONTA");
                                System.out.print("Seu cpf de cadastro: ");
                                cpf = ent.nextLine();
                                
                                gerenciadorPessoa.removerCliente(cpf);
                                System.out.print("Sucesso! Conta excluida.");
                                repoPessoa.listarClientes();
                                break;
                            case 4:
                                String nomeRuaOrigem, nomeRuaDestino;  
                                int numOrigem, numDestino, id;
                                
                                System.out.println("SOLICITAR ENTREGA");
                                
                                System.out.println("Escolha a categoria de veiculo que voce quer fazer a corrida:");
                                System.out.println("Motocicleta\nEconomico\nSUV\nLuxo");
                                System.out.println("\nATENCAO! Entregas com mais de 5kg nao sao aceitas para moto");
                                String opVeiculo = ent.nextLine();
                                                                
                                System.out.print("Nome da rua de origem: ");
                                nomeRuaOrigem = ent.nextLine();
                                System.out.print("Numero da origem: ");
                                numOrigem = ent.nextInt();
                                ent.nextLine();
                                Origem origemEntrega = new Origem(nomeRuaOrigem, numOrigem);
                                
                                System.out.print("Nome da rua de destino: ");
                                nomeRuaDestino = ent.nextLine();
                                System.out.print("Numero do destino: ");
                                numDestino = ent.nextInt();
                                ent.nextLine();
                                Destino destinoEntrega = new Destino(nomeRuaDestino, numDestino);
                                
                                System.out.println("Qual peso da entrega em Kg: ");
                                double pesoPacoteKg = ent.nextDouble();
                                
                                double valor = gerenciadorViagem.solicitarViagemEntrega(origemEntrega, destinoEntrega, pesoPacoteKg, opVeiculo);
                                System.out.println("Valor total da entrega: "+valor);    
                                repoViagem.listarViagens();
                                break;
                            case 5:
                                System.out.println("SOLICITAR VIAGEM DE CORRIDA");
                                System.out.println("Escolha a categoria de veiculo que voce quer fazer a corrida:");
                                System.out.println("Motocicleta\nEconomico\nSUV\nLuxo");
                                opVeiculo = ent.nextLine();
                                
                                System.out.print("Nome da rua de origem: ");
                                nomeRuaOrigem = ent.nextLine();
                                System.out.print("Numero da origem: ");
                                numOrigem = ent.nextInt();
                                ent.nextLine();
                                Origem origemCorrida = new Origem(nomeRuaOrigem, numOrigem);
                                
                                System.out.print("Nome da rua de destino: ");
                                nomeRuaDestino = ent.nextLine();
                                System.out.print("Numero do destino: ");
                                numDestino = ent.nextInt();
                                ent.nextLine();
                                Destino destinoCorrida = new Destino(nomeRuaDestino, numDestino);
                                
                                valor = gerenciadorViagem.solicitarViagemPassageiro(origemCorrida, destinoCorrida, opVeiculo);
                                
                                System.out.println("Valor total da viagem: "+valor);
                                repoViagem.listarViagens();
                                break;
                            default:   
                                System.out.println("Opcao invalida! Tente novamente");
                        }
                    } while(op!=99);
                }

                if(op == 2){
                    do{
                        System.out.println("\n= MOTORISTA DIVERPOO =");
                        System.out.println("1 - Cadastro de novo motorista");
                        System.out.println("2 - Cadastro de veiculo (apenas se o motorista ja estiver cadastrado no sistema)");
                        System.out.println("3 - Excluir conta de motorista");
                        System.out.println("4 - Excluir veiculo");
                        System.out.println("5 - Validar motorista");
                        System.out.println("6 - Lista de corridas");
                        System.out.println("99 - Voltar para login");
                        op = ent.nextInt();
                        ent.nextLine();
                        
                        switch(op){
                            case 1:
                                System.out.println("CADASTRO DE NOVO MOTORISTA");
                                System.out.print("Nome: ");
                                nome = ent.nextLine();
                                System.out.print("CNH (sem pontos ou tracos, apenas numeros): ");
                                cnh = ent.nextLine();
                                System.out.print("CPF (sem pontos ou tracos, apenas numeros): ");
                                cpf = ent.nextLine();
                                System.out.print("Email: ");
                                email = ent.nextLine();
                                System.out.print("Telefone (apenas numeros): ");
                                telefone = ent.nextLine();
                                
                                gerenciadorPessoa.cadastrarMotorista(nome, email, telefone, cpf, cnh);
                                System.out.println("\nSucesso! Motorista cadastrado!");
                                System.out.println("Nao esqueca de validar seu cadastro para comecar a trabalhar...");
                                repoPessoa.listarMotoristas();
                                break;
                            case 2:
                                System.out.println("CADASTRO DE VEICULO");
                                System.out.println("ATENCAO! Voce deve estar cadastrado como motorista para cadastrar seu veiculo");
                                
                                System.out.print("Digite sua cnh: ");
                                cnh = ent.nextLine();
                                gerenciadorPessoa.buscarMotorista(cnh);
                                
                                System.out.print("Placa do veiculo: ");
                                String placa = ent.nextLine();
                                System.out.print("Modelo: ");
                                String modelo = ent.nextLine();                                
                                System.out.print("Capacidade do veiculo: ");
                                int capacidade = ent.nextInt();
                                ent.nextLine();
                                
                                System.out.println("Digite em qual categoria seu veiculo vai ser cadastrado: ");
                                System.out.println("1 - Moto\n2 - Carro Economico\n3 - Carro Luxo\n4 - Carro SUV");
                                int opVeiculo = ent.nextInt();
                                
                                if(opVeiculo == 1){
                                    gerenciadorVeiculos.cadastrarMotocicleta(cnh, placa, capacidade, modelo);
                                } else if(opVeiculo == 2){
                                    gerenciadorVeiculos.cadastrarEconomico(cnh, placa, capacidade, modelo);
                                } else if(opVeiculo == 3){
                                    gerenciadorVeiculos.cadastrarLuxo(cnh, placa, capacidade, modelo);
                                } else if(opVeiculo == 4){
                                    gerenciadorVeiculos.cadastrarSUV(cnh, placa, capacidade, modelo);
                                } else{
                                    System.out.println("Opcao nao listada!");
                                }
                                
                                System.out.println("\nSucesso! Veiculo cadastrado.");
                                repoVeiculo.listarVeiculos();
                                repoPessoa.listarMotoristas();
                                break;
                            case 3:
                                System.out.println("EXCLUIR CONTA");
                                System.out.print("Digite sua cnh: ");
                                cnh = ent.nextLine();
                                Motorista motorista = gerenciadorPessoa.buscarMotorista(cnh);
                                gerenciadorPessoa.removerMotorista(motorista.getCnh());
                                
                                if(motorista.getIdVeiculo()>0){
                                    System.out.println("Estou no excluir");
                                    Veiculo veiculo = repoVeiculo.buscarPorId(motorista.getIdVeiculo());
                                    gerenciadorVeiculos.excluir(veiculo.getPlaca());
                                }
                                
                                System.out.println("Sucesso! Conta do motorista e veiculo (se existir) excluidos do sistema");
                                repoPessoa.listarMotoristas();
                                repoVeiculo.listarVeiculos();
                                break;
                            case 4:
                                System.out.println("EXCLUIR VEICULO");
                                System.out.println("Digite a placa do veiculo: ");
                                placa = ent.nextLine();
                                gerenciadorVeiculos.excluir(placa);
                                System.out.println("Sucesso! Veiculo excluido.");
                                repoVeiculo.listarVeiculos();
                                repoPessoa.listarMotoristas();
                                break;
                            case 5:
                                System.out.println("VALIDAR MOTORISTA");
                                System.out.println("Digite sua cnh corretamente: ");
                                cnh = ent.nextLine();
                                System.out.println("Digite seu email de cadastro: ");
                                email = ent.nextLine();
                                System.out.println("Digite seu cpf corretamente: ");
                                cpf = ent.nextLine();
                                gerenciadorPessoa.validarMotorista(cnh, email, cpf);
                                System.out.println("Sucesso! Voce ja pode começar a trabalhar");
                                break;
                            case 6:
                                System.out.println("CORRIDAS");
                                System.out.print("Digite sua cnh: ");
                                cnh = ent.nextLine();
                                gerenciadorViagem.mostrarViagensDisponiveisParaMotoristas(cnh);
                                
                                System.out.println("Digite o ID da corrida para aceitar: ");
                                int idCorrida = ent.nextInt();
                                break;
                        }
                    }while(op!=99);
                }
            } catch(MotoristaNaoDisponivelException | MotoristaNaoValidadoException | MotoristaSemVeiculoException e){
                System.out.println("Erro ao iniciar corrida: " + e.getMessage());
            } catch(PagamentoRecusadoException e){
                System.out.println("Erro no pagamento: "+e.getMessage());                
            } catch(PessoaJaCadastradaException | VeiculoJaCadastradoException e){
                System.out.println("Erro no cadastro: "+e.getMessage());
            } catch(PessoaNaoEncontradaException | VeiculoNaoExisteException | ViagemNaoEncontradaException e){
                System.out.println("Erro na busca: "+e.getMessage());
            } catch(VeiculoNaoIdealException e){
                System.out.println("Erro na selecao do veiculo: "+e.getMessage());
            }
        }while(op!=0);
    }
}
