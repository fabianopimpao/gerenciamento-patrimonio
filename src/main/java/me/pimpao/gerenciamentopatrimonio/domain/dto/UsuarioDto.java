package me.pimpao.gerenciamentopatrimonio.domain.dto;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import me.pimpao.gerenciamentopatrimonio.domain.entity.Usuario;
import me.pimpao.gerenciamentopatrimonio.domain.service.validation.UsuarioValidation;

@UsuarioValidation
public class UsuarioDto {
		
	private UUID id;
	
	@NotEmpty(message = "O campo nome deve ser informado")
	private String nome;
	
	@NotEmpty(message = "O campo email deve ser informado")
	@Email
	private String email;
	
	@NotEmpty(message = "O campo senha deve ser informada")
	private String senha;
	
	public UsuarioDto() {
	
	}

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
