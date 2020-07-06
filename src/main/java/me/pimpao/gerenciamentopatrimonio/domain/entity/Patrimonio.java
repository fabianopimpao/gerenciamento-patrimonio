package me.pimpao.gerenciamentopatrimonio.domain.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Patrimonio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String descricao;

    @Column(name = "numero_tombo")
    private UUID numeroTombo;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    @JsonIgnore
    private Marca marca;

    public Patrimonio() {

    }

    public Patrimonio(String nome, String descricao, UUID numeroTombo, Marca marca) {
        this.nome = nome;
        this.descricao = descricao;
        this.numeroTombo = numeroTombo;
        this.marca = marca;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UUID getNumeroTombo() {
        return numeroTombo;
    }

    public void setNumeroTombo(UUID numeroTombo) {
        this.numeroTombo = numeroTombo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patrimonio that = (Patrimonio) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
