package ucb.aplicativo.control;

import ucb.aplicativo.model.Tarefa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TarefaServico {
    private static List<Tarefa> ListadeTarefas = new ArrayList<>();

    private static long ProximoID = 1;

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


        //TODO: EditarTarefa()

        //TODO: ExcluirTarefa()

        //TODO: MarcarTarefa()

}
