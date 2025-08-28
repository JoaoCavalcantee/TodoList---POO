package ucb.aplicativo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa {

    private Long  id;
    private String titulo;
    private String descricao;
    private boolean completa;
    private LocalDateTime dataAgora;

    public Tarefa(){
        this.dataAgora = LocalDateTime.now();
        this.completa = false;
    }

    // Construtor com Titulo

    public Tarefa(String titulo){
        this();
        this.titulo = titulo;
    }

    //Construtor com Titulo e Descricao

    public Tarefa(String titulo, String descricao){
        this(titulo);
        this.descricao = descricao;
    }

    //Construtor completo

    public Tarefa(Long id, String titulo, String descricao, boolean completa){
        this(titulo, descricao);
        this.id = id;
        this.completa = completa;
    }


    public Tarefa(Long id, boolean completa) {
        this.id = id;
        this.completa = completa;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isCompleta() {
        return completa;
    }

    public LocalDateTime getDataAgora() {
        return dataAgora;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCompleta(boolean completa) {
        this.completa = completa;
    }

    public void setDataAgora(LocalDateTime dataAgora) {
        this.dataAgora = dataAgora;
    }
}
