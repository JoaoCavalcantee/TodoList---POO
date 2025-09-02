package ucb.aplicativo.cli;
import java.util.Scanner;

public class java {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.println("*****************************");
        System.out.println("*Bem-Vindo Lista de Tarefas!*");
        System.out.println("*****************************");

        int opcao = 0;

        do {
            System.out.println("1 - Para Adicionar Nova Tarefa");
            System.out.println("2 - Para Visualizar a Lista de Tarefas");
            System.out.println("3 - Para Editar Tarefa");
            System.out.println("4 - para Excluir Tarefa");
            System.out.println("5 - Para Marcar Tarefa como concluida");
            System.out.println("6 - Para Sair");

            opcao = entrada.nextInt();

            switch (opcao) {
                case 1: //Adicionar nova tarefa 
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