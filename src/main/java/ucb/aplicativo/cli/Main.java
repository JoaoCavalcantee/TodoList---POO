package ucb.aplicativo.cli;
import ucb.aplicativo.control.TarefaServico;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.println("*****************************************************");
        System.out.println("************ Bem-Vindo Lista de Tarefas *************");
        System.out.println("*****************************************************");

        int opcao = 0;

        do {
            System.out.println();
            System.out.println("1 - Para Adicionar Nova Tarefa");
            System.out.println("2 - Para Visualizar a Lista de Tarefas");
            System.out.println("3 - Para Editar Tarefa");
            System.out.println("4 - para Excluir Tarefa");
            System.out.println("5 - Para Marcar Tarefa como concluida");
            System.out.println("6 - Para Sair");

            opcao = entrada.nextInt();

            switch (opcao) {
                case 1: //Adicionar nova tarefa
                    System.out.println("=====================================================");
                    System.out.println("================= Criar nova Tarefa =================");
                    System.out.println("=====================================================");

                    System.out.println("ID: ");
                    long id = entrada.nextLong();
                    entrada.nextLine();

                    System.out.println("Nome: ");
                    String nome = entrada.nextLine();

                    System.out.println("Descricao: ");
                    String descricao = entrada.nextLine();

                    System.out.println("Concluida?: (s/n) ");
                    String resposta = entrada.next().toLowerCase(); //Correcao do DeepSeek
                    boolean concluida = resposta.equals("s") || resposta.equals("sim"); //Correcao do DeepSeek

                    //TODO: Da maneira que esta o dataCriacao esta criando uma data e horario para o exato momento em que a task eh registrada, precisa trocar para que o usuario coloque manualmente, eu acho!
                    String dataCriacao = java.time.LocalDateTime.now().toString();
                    System.out.println("Data de criacao: " + dataCriacao + " (automatica)");

                    TarefaServico.AdicionarTarefa(id, nome, descricao, concluida, dataCriacao);

                    //Limpar Scanner
                    entrada.nextLine();

                    break;

                case 2: //Visualizar nova lista de Tarefas

                    //Esta pronto aqui
                    System.out.println("=====================================================");
                    System.out.println("================= Lista de Tarefas! =================");
                    System.out.println("=====================================================");

                    TarefaServico.VisualizarTarefa();

                    break;

                case 3: //Editar Tarefa
                    break;

                case 4: //Excluir Tarefa
                    break;

                case 5: //Marcar Tarefa como concluida
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