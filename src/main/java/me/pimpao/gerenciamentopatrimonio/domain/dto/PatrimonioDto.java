package me.pimpao.gerenciamentopatrimonio.domain.dto;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import me.pimpao.gerenciamentopatrimonio.domain.entity.Patrimonio;

public class PatrimonioDto {

    private UUID id;
    @NotEmpty(message = "O campo marcaId deve ser informado")
    private UUID marcaId;
    @NotEmpty(message = "O campo nome deve ser informado")
    private String nome;
    private String descricao;
    private UUID numeroTombo;

    public PatrimonioDto() {

    }

    public PatrimonioDto(Patrimonio patrimonio) {
        this.id = patrimonio.getId();
        this.marcaId = patrimonio.getMarca().getId();
        this.nome = patrimonio.getNome();
        this.descricao = patrimonio.getDescricao();
        this.numeroTombo = patrimonio.getNumeroTombo();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(UUID marcaId) {
        this.marcaId = marcaId;
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
}
