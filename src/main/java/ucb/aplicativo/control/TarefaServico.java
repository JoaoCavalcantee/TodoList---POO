package ucb.aplicativo.control;

import ucb.aplicativo.model.Tarefa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaServico {
    private static List<Tarefa> ListadeTarefas = new ArrayList<>();

    private static long ProximoID = 1;

    //Adicionar Tarefas
    public static void AdicionarTarefa(String titulo, String descricao, boolean completa) {
        Tarefa tarefa_nova = new Tarefa(); //Colocar os construtores aqui
        tarefa_nova.setId(ProximoID++);
        tarefa_nova.setTitulo(titulo);
        tarefa_nova.setDescricao(descricao);
        tarefa_nova.setCompleta(completa);
        tarefa_nova.setDataAgora(LocalDateTime.now());

        ListadeTarefas.add(tarefa_nova);
        System.out.println("Tarefa '" + tarefa_nova.getTitulo() + "' adicionada com sucesso!");
        return;
    }
    //Visualizar Tarefas
        public static void VisualizarTarefa() {
            if(ListadeTarefas.isEmpty()){
                System.out.println("Nenhuma Tarefa foi adicionada!");
                return;
            }

            System.out.println();
            System.out.println("-----------------------------------------------------");

            for (Tarefa listadeTarefa : ListadeTarefas) {

                System.out.println("ID: " + listadeTarefa.getId());
                System.out.println("Titulo: " + listadeTarefa.getTitulo());
                System.out.println("Descricao: " + listadeTarefa.getDescricao());
                System.out.println("Esta completa?: " + listadeTarefa.isCompleta());
                System.out.println("Data: " + listadeTarefa.getDataAgora());
                System.out.println("-----------------------------------------------------");
            }
            return;
        }


        //TODO: EditarTarefa()

        //TODO: ExcluirTarefa()

        //TODO: MarcarTarefa()

}
