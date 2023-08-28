package br.com.easyprice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PostoCombustivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String nomePosto;

    private String tipoCombustivel;

    private Double valorCombustivel;

    private String endereco;

    private String formaPagamento;

    private String imagemPath;

    @OneToMany(mappedBy = "postoCombustivel", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comentarios> comentarios = new ArrayList<>();

    public PostoCombustivel(){}

    public PostoCombustivel(Long id, String nome, String nomePosto, String tipoCombustivel, Double valorCombustivel, String endereco, String formaPagamento, String imagemPath, List<Comentarios> comentarios) {
        this.id = id;
        this.nome = nome;
        this.nomePosto = nomePosto;
        this.tipoCombustivel = tipoCombustivel;
        this.valorCombustivel = valorCombustivel;
        this.endereco = endereco;
        this.formaPagamento = formaPagamento;
        this.imagemPath = imagemPath;
        this.comentarios = comentarios;
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

    public String getNomePosto() {
        return nomePosto;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public Double getValorCombustivel() {
        return valorCombustivel;
    }

    public void setValorCombustivel(Double valorCombustivel) {
        this.valorCombustivel = valorCombustivel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getImagemPath() {
        return imagemPath;
    }

    public void setImagemPath(String imagemPath) {
        this.imagemPath = imagemPath;
    }

    public List<Comentarios> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentarios> comentarios) {
        this.comentarios = comentarios;
    }
}
