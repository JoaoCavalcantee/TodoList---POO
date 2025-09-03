package ucb.aplicativo.control;

import ucb.aplicativo.model.Tarefa;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TarefaServico {
    private static List<Tarefa> ListadeTarefas = new ArrayList<>();

    private static long ProximoID = 1;

    static Scanner scanner = new Scanner(System.in);

    //Adicionar Tarefas
    public static void AdicionarTarefa(Long id, String titulo, String descricao, boolean completa) {
        Tarefa tarefa_nova = new Tarefa(); //Colocar os construtores aqui
        if(id == 0L){
            tarefa_nova.setId(ProximoID++);
        } else {
            tarefa_nova.setId(id);
        }
        tarefa_nova.setTitulo(titulo);
        tarefa_nova.setDescricao(descricao);
        tarefa_nova.setCompleta(completa);
        tarefa_nova.setDataAgora(LocalDateTime.now());

        ListadeTarefas.add(tarefa_nova);
        System.out.println();
        System.out.println("Tarefa '" + tarefa_nova.getTitulo() + "' adicionada com sucesso!");
        return;
    }
    //Visualizar Tarefas
        public static void VisualizarTarefa() {
            if(ListadeTarefas.isEmpty()){
                System.out.println("Nenhuma Tarefa foi adicionada!");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            System.out.println();
            System.out.println("-----------------------------------------------------");

            for (Tarefa listadeTarefa : ListadeTarefas) {

                System.out.println("ID: " + listadeTarefa.getId());
                System.out.println("Titulo: " + listadeTarefa.getTitulo());
                System.out.println("Descrição: " + listadeTarefa.getDescricao());
                System.out.println("Está completa?: " + (listadeTarefa.isCompleta() ? "Sim" : "Não"));
                System.out.println("Data: " + listadeTarefa.getDataAgora().format(formatter));
                System.out.println("-----------------------------------------------------");
            }
            return;
        }


        public static void EditarTarefa(){

            long IdProcurado;
            int i ;
            long TarefaAtualizada = 100;

            if(ListadeTarefas.isEmpty()){
                System.out.println("Nenhuma Tarefa foi adicionada!");
                return;
            }

            System.out.println("Digite o ID da tarefa que deseja editar:");
               IdProcurado = scanner.nextLong();
               scanner.nextLine(); //ARRUMAR BUG DE BUFFER


                //PROCURA O ID
                 for (i = 0; i < ListadeTarefas.size(); i++) {
                     if (IdProcurado == ListadeTarefas.get(i).getId()) {
                         TarefaAtualizada = i;
                         break;
                     }
                 }

            if(TarefaAtualizada == 100){   //nao achou
                    System.out.println("ID inserido é inexistente!");
                    return;
                }else{                     //achou
                    //NOME
                    System.out.print("Novo nome: (Deixe em branco para não editar) ");
                           String NomeNovo = scanner.nextLine();
                           if(!NomeNovo.isEmpty()){
                               ListadeTarefas.get(i).setTitulo(NomeNovo);
                           }

                    //DESCRIÇÃO
                    System.out.print("Nova descrição: (Deixe em branco para não editar) ");
                           String DescricaoNovo = scanner.nextLine();
                           if(!DescricaoNovo.isEmpty()){
                               ListadeTarefas.get(i).setDescricao(DescricaoNovo);
                           }
                    //ID
                    System.out.print("Novo ID: (Deixe em branco para não editar) ");
                           String IdNovo = scanner.nextLine();
                           if (!IdNovo.isEmpty()) {
                                try {
                                    long id = Long.parseLong(IdNovo);
                                    ListadeTarefas.get(i).setId(id);
                                } catch (NumberFormatException e) {
                                    System.out.println("ID inválido! Digite apenas números inteiros.");
                                }
                           }

                    //STATUS
                    System.out.print("Concluída? (Sim/Não): ");                }
                            String NovoStatus = scanner.nextLine();
                            if(NovoStatus.equalsIgnoreCase("sim") || NovoStatus.equalsIgnoreCase("s") ){
                                ListadeTarefas.get(i).setCompleta(true);
                            }
                            if( NovoStatus.equalsIgnoreCase("nao") || NovoStatus.equalsIgnoreCase("n")){
                                ListadeTarefas.get(i).setCompleta(false);
                            }

            System.out.println();
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║            Tarefa editada com sucesso!         ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println();
        }




        //TODO: ExcluirTarefa()

        //TODO: MarcarTarefa()

}
