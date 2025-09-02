package ucb.aplicativo.cli;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.println("*****************************");
        System.out.println("*Bem-Vindo Lista de Tarefas!*");
        System.out.println("*****************************");

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
                    System.out.println("=======================");
                    System.out.println("== Criar Nova Tarefa ==");
                    System.out.println("=======================");

                    System.out.println("ID: ");
                    int id = entrada.nextInt();

                    System.out.println("Nome: ");
                    String nome = entrada.next();

                    System.out.println("Descricao: ");
                    String descricao = entrada.next();

                    System.out.println("Concluida?: (True/False) ");
                    boolean concluida = entrada.nextBoolean();

                    System.out.println("Data de Criacao: (yyyy-mm-dd) ");
                    String dataIniciodaTarefa = entrada.nextLine();

                    //TODO: Colocar as informacoes coletadas no metodo e criar uma task

                    System.out.println("Tarefa criada com sucesso!");

                    //Limpar Scanner
                    entrada.nextLine();

                    break;

                case 2: //Visualizar nova lista de Tarefas
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