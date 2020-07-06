package me.pimpao.gerenciamentopatrimonio.domain.dto;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import me.pimpao.gerenciamentopatrimonio.domain.entity.Marca;
import me.pimpao.gerenciamentopatrimonio.domain.service.validation.MarcaValidation;

@MarcaValidation
public class MarcaDto {

    private UUID id;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    public MarcaDto() {

    }

    public MarcaDto(Marca marca) {
        this.id = marca.getId();
        this.nome = marca.getNome();
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
}
