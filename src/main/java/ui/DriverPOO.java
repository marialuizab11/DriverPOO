package ui;

import java.util.Scanner;
import dados.*;
import negocios.*;
import negocios.basicas.*;
import negocios.excecoes.*;

/**
 * Classe principal que representa a interface do usuario do sistema DriverPOO.
 * 
 * Esta classe implementa o menu interativo para:
 *  Cadastro e login de clientes e motoristas.
 *  Solicitacao e gerenciamento de viagens.
 *  Operacao de pagamentos
 *  Avaliacao das viagens
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
        
        /*Exibe o menu principal e gerencia a navegacao*/
        System.out.println("\n== BEM VINDO AO DRIVERPOO ==");
        
        do{
            System.out.println("\n= LOGIN =");
            System.out.println("1 - Cadastro de novo cliente");
            System.out.println("2 - Cadastro de novo motorista");
            System.out.println("3 - Logar como cliente");
            System.out.println("4 - Logar como motorista");
            System.out.println("0 - Sair");
            op = ent.nextInt();
            ent.nextLine();
            
            try{
                /* Fluxo de cadastro de novo cliente */
                if(op == 1){
                    System.out.println("CADASTRO DE NOVO CLIENTE");
                    System.out.print("Nome: ");
                    nome = ent.nextLine();
                    System.out.print("CPF (apenas numeros): " );
                    cpf = ent.nextLine();
                    System.out.print("Email: ");
                    email = ent.nextLine();
                    System.out.print("Telefone (apenas numeros): ");
                    telefone = ent.nextLine();

                    gerenciadorPessoa.cadastrarCliente(nome, email, telefone, cpf);
                    System.out.print("\nSucesso! Conta criada.");
                }
                /* Fluxo de cadastro de novo motorista */
                if(op == 2){
                    System.out.println("CADASTRO DE NOVO MOTORISTA");
                    System.out.print("Nome: ");
                    nome = ent.nextLine();
                    System.out.print("CNH (apenas numeros): ");
                    cnh = ent.nextLine();
                    System.out.print("CPF (apenas numeros): ");
                    cpf = ent.nextLine();
                    System.out.print("Email: ");
                    email = ent.nextLine();
                    System.out.print("Telefone (apenas numeros): ");
                    telefone = ent.nextLine();

                    gerenciadorPessoa.cadastrarMotorista(nome, email, telefone, cpf, cnh);
                    System.out.println("\nSucesso! Motorista cadastrado!");
                    System.out.println("Nao esqueca de VALIDAR seu cadastro para comecar a trabalhar...");
                }
                
                /* Fluxo de login do cliente */
                if(op == 3){
                    System.out.println("== CLIENTES DRIVERPOO ==");
                    System.out.print("CPF: ");
                    cpf = ent.nextLine();
                    Cliente cliente = gerenciadorPessoa.buscarCliente(cpf);
                    do{
                        System.out.println("\n1 - Atualizar suas informacoes");
                        System.out.println("2 - Excluir conta");
                        System.out.println("3 - Solicitar viagem de entrega");
                        System.out.println("4 - Solicitar corrida");
                        System.out.println("5 - Avaliar corrida");
                        System.out.println("99 - Logout");
                        op = ent.nextInt();
                        ent.nextLine();

                        switch(op){
                            case 1:
                                System.out.println("ATUALIZAR INFORMACOES");
                                System.out.print("Novo email: ");
                                email = ent.nextLine();
                                System.out.print("Novo telefone: ");
                                telefone = ent.nextLine();
                                
                                gerenciadorPessoa.atualizarCliente(cliente, email, telefone);
                                System.out.print("Sucesso! Informacoes atualizadas.");
                                break;
                            case 2:
                                System.out.println("EXCLUIR CONTA");
                                
                                gerenciadorPessoa.removerCliente(cliente);
                                System.out.print("Sucesso! Conta excluida.");
                                break;
                            case 3:
                                String nomeRuaOrigem, nomeRuaDestino;  
                                int numOrigem, numDestino, id;
                                
                                System.out.println("SOLICITAR ENTREGA");
                                
                                System.out.println("Escolha a categoria de veiculo que voce quer fazer a corrida:");
                                System.out.println("1 - Motocicleta\n2 - Economico\n3 - SUV\n4 - Luxo");
                                System.out.println("\n   ATENCAO! Entregas com mais de 5kg nao sao aceitas para moto");
                                int opVeiculo = ent.nextInt();
                                ent.nextLine();
                                String categoria = gerenciadorVeiculos.verificarCategoria(opVeiculo);
                                
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
                                
                                System.out.println("Peso total da entrega (em KG): ");
                                double pesoPacoteKg = ent.nextDouble();
                                
                                double valorTotal = gerenciadorViagem.calcularValorTotal(categoria);
                                
                                System.out.println("Valor total da entrega: "+valorTotal);    
                                
                                System.out.println("Escolha o metodo de pagamento: ");
                                System.out.println("1 - PIX\n2 - Cartao de Credito\n3 - Dinheiro (em especie)");
                                int opPagamento = ent.nextInt();
                                ent.nextLine();
                                 
                                if(opPagamento == 1){
                                    System.out.println("PAGAMENTO POR PIX");
                                    System.out.println(gerenciadorPagamentos.gerarChavePix(valorTotal));
                                    System.out.println("Digite 'S' quando o pix for CONCLUIDO. \nDigite 'N' para CANCELAR o pagamento.");
                                    String opPix = ent.nextLine();
                                    
                                    gerenciadorPagamentos.validarPix(opPix);
                                    Viagem viagemEntrega = gerenciadorViagem.solicitarViagemEntrega(cliente, origemEntrega, destinoEntrega, pesoPacoteKg, categoria);
                                    gerenciadorViagem.adicionarPagamentoViagem(viagemEntrega.getId(), new Pix(viagemEntrega.getValorTotal()));
                                    System.out.println("Viagem paga! Esperando algum motorista aceitar...");
                                } else if(opPagamento == 2){
                                    System.out.println("PAGAMENTO POR CARTAO DE CREDITO");
                                    System.out.println("Numero do cartao: ");
                                    String numeroCartao = ent.nextLine();
                                    System.out.println("Nome do titular: ");
                                    String nomeTitular = ent.nextLine();
                                    System.out.println("Data de vencimento (mm/aa):");
                                    String dataVencimento = ent.nextLine();
                                    System.out.println("CVV: ");
                                    int cvv = ent.nextInt();
                                    ent.nextLine();
                                    System.out.println("Limite atual: ");
                                    double limite = ent.nextDouble();
                                    ent.nextLine();
                                    
                                    gerenciadorPagamentos.debitarCartaoCredito(limite, valorTotal);
                                    Viagem viagemEntrega = gerenciadorViagem.solicitarViagemEntrega(cliente, origemEntrega, destinoEntrega, pesoPacoteKg, categoria);
                                    gerenciadorViagem.adicionarPagamentoViagem(viagemEntrega.getId(), new CartaoCredito(nomeTitular, numeroCartao, cvv, dataVencimento, limite, valorTotal));
                                    System.out.println("Viagem paga com o limite do cartao! Esperando algum motorista aceitar...");
                                } else if(opPagamento == 3){
                                    System.out.println("PAGAMENTO POR DINHEIRO EM ESPECIE");
                                    System.out.println("Ao finalizar a corrida pague R$"+valorTotal+" ao motorista");
                                    
                                    Viagem viagemEntrega = gerenciadorViagem.solicitarViagemEntrega(cliente, origemEntrega, destinoEntrega, pesoPacoteKg, categoria);
                                    gerenciadorViagem.adicionarPagamentoViagem(viagemEntrega.getId(), new Dinheiro(valorTotal));
                                    System.out.println("Viagem solicitada! Esperando algum motorista aceitar...");
                                } else{
                                    System.out.println("Opcao invalida!");
                                    break;
                                }
                                break;
                            case 4:
                                System.out.println("SOLICITAR VIAGEM DE CORRIDA");
                                
                                System.out.println("Escolha a categoria de veiculo que voce quer fazer a corrida:");
                                System.out.println("1 - Motocicleta\n2 - Economico\n3 - SUV\n4 - Luxo");
                                opVeiculo = ent.nextInt();
                                ent.nextLine();
                                categoria = gerenciadorVeiculos.verificarCategoria(opVeiculo);
                                
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
                                
                                valorTotal = gerenciadorViagem.calcularValorTotal(categoria);
                                
                                System.out.println("Valor total da corrida: "+valorTotal);    
                                
                                System.out.println("Escolha o metodo de pagamento: ");
                                System.out.println("1 - PIX\n2 - Cartao de Credito\n3 - Dinheiro (em especie)");
                                opPagamento = ent.nextInt();
                                ent.nextLine();
                                
                                if(opPagamento == 1){
                                    System.out.println("PAGAMENTO POR PIX");
                                    System.out.println(gerenciadorPagamentos.gerarChavePix(valorTotal));
                                    System.out.println("Digite 'S' quando o pix for CONCLUIDO. \nDigite 'N' para CANCELAR o pagamento.");
                                    String opPix = ent.nextLine();
                                    
                                    gerenciadorPagamentos.validarPix(opPix);
                                    Viagem viagemCorrida = gerenciadorViagem.solicitarViagemPassageiro(cliente, origemCorrida, destinoCorrida, categoria, valorTotal);
                                    gerenciadorViagem.adicionarPagamentoViagem(viagemCorrida.getId(), new Pix(viagemCorrida.getValorTotal()));
                                    System.out.println("Viagem paga! Esperando algum motorista aceitar...");
                                } else if(opPagamento == 2){
                                    System.out.println("PAGAMENTO POR CARTAO DE CREDITO");
                                    System.out.println("Numero do cartao: ");
                                    String numeroCartao = ent.nextLine();
                                    System.out.println("Nome do titular: ");
                                    String nomeTitular = ent.nextLine();
                                    System.out.println("Data de vencimento (mm/aa):");
                                    String dataVencimento = ent.nextLine();
                                    System.out.println("CVV: ");
                                    int cvv = ent.nextInt();
                                    ent.nextLine();
                                    System.out.println("Limite atual: ");
                                    double limite = ent.nextDouble();
                                    ent.nextLine();
                                    
                                    gerenciadorPagamentos.debitarCartaoCredito(limite, valorTotal);
                                    Viagem viagemCorrida = gerenciadorViagem.solicitarViagemPassageiro(cliente, origemCorrida, destinoCorrida, categoria, valorTotal);
                                    gerenciadorViagem.adicionarPagamentoViagem(viagemCorrida.getId(), new CartaoCredito(nomeTitular, numeroCartao, cvv, dataVencimento, limite, valorTotal));
                                    System.out.println("Viagem paga com o limite do cartao! Esperando algum motorista aceitar...");
                                } else if(opPagamento == 3){
                                    System.out.println("PAGAMENTO POR DINHEIRO EM ESPECIE");
                                    System.out.println("Ao finalizar a corrida pague R$"+valorTotal+" ao motorista");
                                    
                                    Viagem viagemCorrida = gerenciadorViagem.solicitarViagemPassageiro(cliente, origemCorrida, destinoCorrida, categoria, valorTotal);
                                    gerenciadorViagem.adicionarPagamentoViagem(viagemCorrida.getId(), new Dinheiro(valorTotal));
                                    System.out.println("Viagem solicitada! Esperando algum motorista aceitar...");
                                } else{
                                    System.out.println("Opcao invalida!");
                                    break;
                                }
                                break;
                            case 5:
                                System.out.println("Agora eh hora de avaliar!");
                                gerenciadorViagem.mostrarViagensFeitasCliente(cliente);
                                System.out.println("Digite o ID da viagem a ser avaliada: ");
                                int opID = ent.nextInt();
                                ent.nextLine();
                                
                                System.out.println("Quantas estrelas? (digite um numero de 1 a 5)");
                                int estrelas = ent.nextInt();
                                ent.nextLine();
                                System.out.println("Uma pequena descricao da corrida: ");
                                String descricao = ent.nextLine();
                                
                                gerenciadorViagem.avaliarMotorista(opID, estrelas, descricao);
                                break;
                            case 99:
                                System.out.println("Fazendo logout...");
                                break;
                            default:   
                                System.out.println("Opcao invalida! Tente novamente");
                        }
                    } while(op!=99);
                }

                /* Fluxo de login do motorista */
                if(op == 4){
                    System.out.println("== MOTORISTAS DRIVERPOO ==");
                    System.out.print("CNH: ");
                    cnh = ent.nextLine();
                    Motorista motorista = gerenciadorPessoa.buscarMotorista(cnh);
                    do{
                        System.out.println("\n1 - Cadastro de veiculo");
                        System.out.println("2 - Excluir conta");
                        System.out.println("3 - Excluir veiculo");
                        System.out.println("4 - Validar motorista");
                        System.out.println("5 - Lista de corridas nao aceitas");
                        System.out.println("6 - Todas as suas corridas");
                        System.out.println("99 - Voltar para login");
                        op = ent.nextInt();
                        ent.nextLine();
                        
                        switch(op){
                            case 1:
                                System.out.println("CADASTRO DE VEICULO");
                                if(motorista.getIdVeiculo()>0){
                                    System.out.println("O motorista ja tem um veiculo cadastrado. \nRemova o veiculo atual se quiser vincular um novo.");
                                    break;
                                }
                                
                                System.out.print("Placa do veiculo: ");
                                String placa = ent.nextLine();
                                System.out.print("Modelo: ");
                                String modelo = ent.nextLine();                                
                                System.out.print("Quantas pessoas o veiculo leva na viagem: ");
                                int capacidade = ent.nextInt();
                                ent.nextLine();
                                
                                System.out.println("\n1 - Moto\n2 - Carro Economico\n3 - Carro Luxo\n4 - Carro SUV");
                                System.out.print("Digite em qual categoria seu veiculo vai ser cadastrado: ");
                                int opVeiculo = ent.nextInt();
                                ent.nextLine();
                                
                                if(opVeiculo == 1){
                                    gerenciadorVeiculos.cadastrarMotocicleta(motorista, placa, capacidade, modelo);
                                } else if(opVeiculo == 2){
                                    gerenciadorVeiculos.cadastrarEconomico(motorista, placa, capacidade, modelo);
                                } else if(opVeiculo == 3){
                                    gerenciadorVeiculos.cadastrarLuxo(motorista, placa, capacidade, modelo);
                                } else if(opVeiculo == 4){
                                    gerenciadorVeiculos.cadastrarSUV(motorista, placa, capacidade, modelo);
                                } else{
                                    System.out.println("Opcao nao listada!");
                                }
                                
                                System.out.println("\nSucesso! Veiculo cadastrado.");
                                break;
                            case 2:
                                System.out.println("EXCLUIR CONTA");
                                
                                gerenciadorPessoa.removerMotorista(motorista.getCnh());
                                
                                if(motorista.getIdVeiculo()>0){
                                    Veiculo veiculo = repoVeiculo.buscarPorId(motorista.getIdVeiculo());
                                    gerenciadorVeiculos.excluir(veiculo.getPlaca());
                                }
                                
                                System.out.println("Sucesso! Conta do motorista e veiculo (se existir) excluidos do sistema");
                                break;
                            case 3:
                                System.out.println("EXCLUIR VEICULO");
                                
                                System.out.println("Digite a placa do veiculo: ");
                                placa = ent.nextLine();
                                gerenciadorVeiculos.excluir(placa);
                                System.out.println("Sucesso! Veiculo excluido.");
                                break;
                            case 4:
                                System.out.println("VALIDAR MOTORISTA");
                                System.out.println("Digite sua cnh corretamente: ");
                                cnh = ent.nextLine();
                                System.out.println("Digite seu email de cadastro: ");
                                email = ent.nextLine();
                                System.out.println("Digite seu cpf corretamente: ");
                                cpf = ent.nextLine();
                                gerenciadorPessoa.validarMotorista(motorista, email, cpf);
                                System.out.println("Sucesso! Voce ja pode comecar a trabalhar");
                                break;
                            case 5:
                                System.out.println("CORRIDAS");
                                
                                boolean haViagens = gerenciadorViagem.mostrarViagensDisponiveisParaMotoristas(motorista);
                                
                                if(!haViagens){
                                    break;
                                }
                                
                                System.out.print("Digite o ID da corrida para aceitar: ");
                                int idCorrida = ent.nextInt();
                                
                                Viagem viagem = repoViagem.buscarViagem(idCorrida);
                                gerenciadorViagem.iniciarViagem(viagem, cnh);

                                int encerrou = 1;
                                
                                while(encerrou != 0){
                                    System.out.print("Digite 0 quando chegar ao destino: ");
                                    encerrou = ent.nextInt();
                                    ent.nextLine();
                                    if(encerrou == 0){
                                        System.out.println("Lembre o passageiro de pegar seus pertences no veiculo!");
                                        gerenciadorViagem.encerrarViagem(viagem);
                                        System.out.println("\nAvalie seu passageiro!");
                                        System.out.print("Estrelas (digite um numero de 1 a 5): ");
                                        int estrelas = ent.nextInt();
                                        ent.nextLine();
                                        System.out.print("Digite uma pequena descricao de como foi a viagem: ");
                                        String descricao = ent.nextLine();
                                        gerenciadorViagem.avaliarCliente(viagem, estrelas, descricao);
                                        System.out.println("A DriverPOO agradece sua contribuicao!");
                                        break;
                                    } else{
                                        System.out.println("NUMERO INVALIDO!!");
                                    }
                                }
                                break;
                            case 6:
                                gerenciadorViagem.mostrarViagensDeMotorista(motorista);
                                break;
                            case 99:
                                System.out.println("Fazendo logout...");
                                break;
                            default:
                                System.out.println("Opcao nao listada!");
                                break;
                        }
                    }while(op!=99);
                }
            /*Tratamento de excecoes que ocasionalmente podem ser lancadas */
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
            } catch(CategoriaVeiculoNaoValidaException e){
                System.out.println("Erro ao solicitar viagem: "+e.getMessage());
            } catch(PessoaSemViagensException e){
                System.out.println("Erro ao listar viagens: "+e.getMessage());
            }
        }while(op!=0);
    }
    
}
