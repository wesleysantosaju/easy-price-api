package br.com.easyprice.model;

import jakarta.persistence.*;

@Entity
public class Comentarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String comentario;

    @Transient
    private Long postoId; // Campo para armazenar o ID do posto

    @ManyToOne
    @JoinColumn(name = "posto_id")
    private PostoCombustivel postoCombustivel;

    public Comentarios() {
    }

    public Comentarios(Long id, String nome, String comentario, Long postoId, PostoCombustivel postoCombustivel) {
        this.id = id;
        this.nome = nome;
        this.comentario = comentario;
        this.postoId = postoId;
        this.postoCombustivel = postoCombustivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getPostoId() {
        return postoId;
    }

    public void setPostoId(Long postoId) {
        this.postoId = postoId;
    }

    public PostoCombustivel getPostoCombustivel() {
        return postoCombustivel;
    }

    public void setPostoCombustivel(PostoCombustivel postoCombustivel) {
        this.postoCombustivel = postoCombustivel;
    }
}