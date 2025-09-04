package ucb.aplicativo.control;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ucb.aplicativo.model.Tarefa;

public class TarefaServico {
    private static List<Tarefa> ListadeTarefas = new ArrayList<>();

    private static long ProximoID = 1;

    static Scanner scanner = new Scanner(System.in);

    public static void CriarTarefa() {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Nome: (Deixe em branco para não informar) ");
        String nome = entrada.nextLine();

        System.out.print("Descrição: (Deixe em branco para não informar) ");
        String descricao = entrada.nextLine();

        System.out.print("ID: (Deixe em branco para não informar) ");
        String idInput = entrada.nextLine();
        Long id = 0L;

        if (!idInput.isEmpty()) {
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

        Tarefa tarefa = null;

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
        }

        TarefaServico.AdicionarTarefa(
                tarefa.getId() != null ? tarefa.getId() : 0L,
                tarefa.getTitulo() != null ? tarefa.getTitulo() : "",
                tarefa.getDescricao() != null ? tarefa.getDescricao() : "",
                tarefa.isCompleta()
        );
    }

    //Adicionar Tarefas
    public static void AdicionarTarefa(Long id, String titulo, String descricao, boolean completa) {
        Tarefa tarefa_nova = new Tarefa(); //Colocar os construtores aqui
        if (id == 0L) {
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
        if (ListadeTarefas.isEmpty()) {
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

    public static void EditarTarefa() {

        long IdProcurado;
        int i;
        long TarefaAtualizada = 100;

        if (ListadeTarefas.isEmpty()) {
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

        if (TarefaAtualizada == 100) {   //nao achou
            System.out.println("ID inserido é inexistente!");
            return;
        } else {                     //achou
            //NOME
            System.out.print("Novo nome: (Deixe em branco para não editar) ");
            String NomeNovo = scanner.nextLine();
            if (!NomeNovo.isEmpty()) {
                ListadeTarefas.get(i).setTitulo(NomeNovo);
            }

            //DESCRIÇÃO
            System.out.print("Nova descrição: (Deixe em branco para não editar) ");
            String DescricaoNovo = scanner.nextLine();
            if (!DescricaoNovo.isEmpty()) {
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
        }

        System.out.println();
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║            Tarefa editada com sucesso!         ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println();
    }

    // --- OPÇÃO 4: EXCLUIR TAREFA ---
    public static void excluirTarefa() {
        if (ListadeTarefas.isEmpty()) {
            System.out.println("Não há tarefas para excluir.");
            return;
        }

        System.out.println("Você deseja excluir por: [1] ID  [2] Nome");
        System.out.print("Escolha 1 ou 2: ");
        String escolha = scanner.nextLine().trim();

        switch (escolha) {
            case "1":
                excluirPorId();
                break;
            case "2":
                excluirPorNome();
                break;
            default:
                System.out.println("Opção inválida. Cancelado.");
        }
    }

    private static void excluirPorId() {
        System.out.print("Informe o ID da tarefa: ");
        String idStr = scanner.nextLine().trim();
        long id;

        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Tarefa alvo = null;
        for (Tarefa t : ListadeTarefas) {
            // getId() esperado em Tarefa (Long). Ajuste se seu getter tiver outro nome.
            if (t.getId() != null && t.getId() == id) {
                alvo = t;
                break;
            }
        }

        if (alvo == null) {
            System.out.println("Nenhuma tarefa encontrada com o ID " + id + ".");
            return;
        }

        if (confirmar("Excluir a tarefa \"" + alvo.getTitulo() + "\" (ID " + id + ")? [s/N] ")) {
            ListadeTarefas.remove(alvo);
            System.out.println("Tarefa excluída com sucesso.");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }

    private static void excluirPorNome() {
        System.out.print("Informe parte do nome/título da tarefa: ");
        String termo = scanner.nextLine().trim().toLowerCase();

        List<Tarefa> correspondencias = new ArrayList<>();
        for (Tarefa t : ListadeTarefas) {
            String titulo = (t.getTitulo() == null ? "" : t.getTitulo());
            if (titulo.toLowerCase().contains(termo)) {
                correspondencias.add(t);
            }
        }

        if (correspondencias.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada com esse nome.");
            return;
        }

        if (correspondencias.size() == 1) {
            Tarefa unica = correspondencias.get(0);
            if (confirmar("Excluir a tarefa \"" + unica.getTitulo() + "\" (ID " + unica.getId() + ")? [s/N] ")) {
                ListadeTarefas.remove(unica);
                System.out.println("Tarefa excluída com sucesso.");
            } else {
                System.out.println("Exclusão cancelada.");
            }
            return;
        }

        // Várias correspondências: listar e pedir escolha
        System.out.println("Foram encontradas " + correspondencias.size() + " tarefas:");
        for (int i = 0; i < correspondencias.size(); i++) {
            Tarefa t = correspondencias.get(i);
            System.out.printf("[%d] ID %d - %s%s%n",
                    i + 1,
                    (t.getId() == null ? 0 : t.getId()),
                    (t.getTitulo() == null ? "(sem título)" : t.getTitulo()),
                    (t.isCompleta() ? " (concluída)" : ""));
        }

        System.out.print("Digite o número da tarefa que deseja excluir: ");
        String indiceStr = scanner.nextLine().trim();
        int indice;
        try {
            indice = Integer.parseInt(indiceStr);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }

        if (indice < 1 || indice > correspondencias.size()) {
            System.out.println("Opção fora do intervalo.");
            return;
        }

        Tarefa escolhida = correspondencias.get(indice - 1);
        if (confirmar("Excluir a tarefa \"" + escolhida.getTitulo() + "\" (ID " + escolhida.getId() + ")? [s/N] ")) {
            ListadeTarefas.remove(escolhida);
            System.out.println("Tarefa excluída com sucesso.");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }

    private static boolean confirmar(String pergunta) {
        System.out.print(pergunta);
        String resp = scanner.nextLine().trim().toLowerCase();
        return resp.equals("s") || resp.equals("sim") || resp.equals("y") || resp.equals("yes");
    }


    public static void MarcarTarefaComoConcluida(Long id, String titulo) {
        boolean encontrada = false;

        // Caso o ID seja fornecido (não nulo e diferente de 0), busca por ID
        if (id != null && id != 0L) {
            for (Tarefa tarefa : ListadeTarefas) {
                if (tarefa.getId().equals(id)) {
                    encontrada = true;
                    if (tarefa.isCompleta()) {
                        System.out.println("Tarefa já está marcada como concluída!");
                    } else {
                        tarefa.setCompleta(true);
                        System.out.println("Tarefa '" + tarefa.getTitulo() + "' foi marcada como concluída!");
                    }
                    break;
                }
            }
            if (!encontrada) {
                System.out.println("Tarefa com o ID informado não foi encontrada.");
            }
            return;
        }

        // Caso o título seja fornecido (não nulo e não vazio), busca por nome
        if (titulo != null && !titulo.trim().isEmpty()) {
            for (Tarefa tarefa : ListadeTarefas) {
                if (tarefa.getTitulo() != null && tarefa.getTitulo().equalsIgnoreCase(titulo)) {
                    encontrada = true;
                    if (tarefa.isCompleta()) {
                        System.out.println("Tarefa '" + tarefa.getTitulo() + "' já está marcada como concluída!");
                    } else {
                        tarefa.setCompleta(true);
                        System.out.println("Tarefa '" + tarefa.getTitulo() + "' foi marcada como concluída!");
                    }
                }
            }
            if (!encontrada) {
                System.out.println("Nenhuma tarefa com esse nome foi encontrada.");
            }
            return;
        }

        System.out.println("Informe um ID ou nome válido para marcar tarefa como concluída.");
    }


}
