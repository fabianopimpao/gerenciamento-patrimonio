package me.pimpao.gerenciamentopatrimonio.domain.service;

import java.util.List;
import java.util.UUID;

import me.pimpao.gerenciamentopatrimonio.domain.dto.UsuarioDto;
import me.pimpao.gerenciamentopatrimonio.domain.entity.Usuario;

public interface UsuarioService {

	Usuario buscarPorId(UUID id);

	List<Usuario> buscarTodos();

	Usuario criar(UsuarioDto usuarioDto);
	
	void excluir(UUID id);
	
}
