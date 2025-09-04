package ucb.aplicativo.cli;
import ucb.aplicativo.control.TarefaServico;
import ucb.aplicativo.model.Tarefa;
import static ucb.aplicativo.control.TarefaServico.EditarTarefa;

import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        //System.out.println("*****************************************************");
        //System.out.println("*********** Bem-Vindo a Lista de Tarefas! ***********");
        //System.out.println("*****************************************************");

        int opcao = 0; //Resposta do Menu CLI

        do {
                System.out.println();
                System.out.println("╔════════════════════════════════════════════════╗");
                System.out.println("║            Sistema Lista de Tarefas            ║");
                System.out.println("╠════════════════════════════════════════════════╣");
                System.out.println("║ 1. Adicionar nova Tarefa                       ║");
                System.out.println("║ 2. Visualizar Tarefas                          ║");
                System.out.println("║ 3. Editar Tarefa                               ║");
                System.out.println("║ 4. Excluir Tarefa                              ║");
                System.out.println("║ 5. Marcar como Concluída                       ║");
                System.out.println("║ 6. Sair                                        ║");
                System.out.println("╚════════════════════════════════════════════════╝");
                System.out.print("--> Escolha uma opção: ");

            opcao = entrada.nextInt(); //Recebe a resposta de MENU do usuario
            entrada.nextLine(); //Limpa Scanner

            switch (opcao) {
                case 1: //Adicionar nova tarefa
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════╗");
                    System.out.println("║                Criar Nova Tarefa               ║");
                    System.out.println("╚════════════════════════════════════════════════╝");

                    System.out.print("Nome: (Deixe em branco para não informar) ");
                    String nome = entrada.nextLine();

                    System.out.print("Descrição: (Deixe em branco para não informar) ");
                    String descricao = entrada.nextLine();

                    System.out.print("ID: (Deixe em branco para não informar) ");
                    String idInput = entrada.nextLine();
                    Long id = 0L;

                    if(!idInput.isEmpty()) {
                        try {
                            id = Long.parseLong(idInput);
                        } catch (NumberFormatException e) {
                            System.out.println("ID inválido. Será atribuído automaticamente.");
                            id = 0L;
                        }
                    }

                    System.out.print("Concluída? (Sim/Não): ");
                    String concluida = entrada.nextLine();

                    boolean completa = concluida.equalsIgnoreCase("sim") || concluida.equalsIgnoreCase("s");

                    Tarefa tarefa;

                    if (!nome.isEmpty() && !descricao.isEmpty() && id != null) {
                        tarefa = new Tarefa(id, nome, descricao, completa);
                    } else if (!nome.isEmpty() && !descricao.isEmpty()) {
                        tarefa = new Tarefa(nome, descricao);
                        tarefa.setCompleta(completa);
                    } else if (!nome.isEmpty()) {
                        tarefa = new Tarefa(nome);
                        tarefa.setCompleta(completa);
                    } else if (nome.isEmpty() && descricao.isEmpty()) {
                        tarefa = new Tarefa();
                        tarefa.setCompleta(completa);
                    } else {
                        System.out.println("Erro ao criar tarefa. Nome é obrigatório se descrição for fornecida.");
                        break;
                    }

                    TarefaServico.AdicionarTarefa(
                            tarefa.getId() != null ? tarefa.getId() : 0L,
                            tarefa.getTitulo() != null ? tarefa.getTitulo() : "",
                            tarefa.getDescricao() != null ? tarefa.getDescricao() : "",
                            tarefa.isCompleta()
                    );

                    break;


                case 2: //Visualizar nova lista de Tarefas
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════╗");
                    System.out.println("║           Visualizar Lista de Tarefas          ║");
                    System.out.println("╚════════════════════════════════════════════════╝");

                    TarefaServico.VisualizarTarefa();

                    break;


                case 3: //Editar Tarefa
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════╗");
                    System.out.println("║                  Editar Tarefa                 ║");
                    System.out.println("╚════════════════════════════════════════════════╝");

                    EditarTarefa();
                    break;


                case 4: //Excluir Tarefa
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════╗");
                    System.out.println("║                  Excluir Tarefa                ║");
                    System.out.println("╚════════════════════════════════════════════════╝");


                    break;


                case 5: //Marcar Tarefa como concluida
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════╗");
                    System.out.println("║           Marcar Tarefa Como Concluída         ║");
                    System.out.println("╚════════════════════════════════════════════════╝");

                    System.out.println("Você deseja marcar por:");
                    System.out.println("1. ID");
                    System.out.println("2. Nome");
                    int tipoBusca = entrada.nextInt();
                    entrada.nextLine();
                    Long idBusca = null;
                    String nomeBusca = null;
                    if (tipoBusca == 1) {
                        System.out.print("Digite o ID da tarefa: ");
                        idBusca = entrada.nextLong();
                        entrada.nextLine();
                    } else if (tipoBusca == 2) {
                        System.out.print("Digite o nome exato da tarefa: ");
                        nomeBusca = entrada.nextLine();
                    }
                    TarefaServico.MarcarTarefaComoConcluida(idBusca, nomeBusca);

                    break;

                default: //Sair
                    opcao = 6;
                    break;
            }

        } while(opcao != 6);

        System.out.println("Finalizando...");
        entrada.close();

        System.exit(0);
    }
}
