package me.pimpao.gerenciamentopatrimonio.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import me.pimpao.gerenciamentopatrimonio.domain.dto.UsuarioDto;
import me.pimpao.gerenciamentopatrimonio.domain.entity.Usuario;
import me.pimpao.gerenciamentopatrimonio.domain.repository.UsuarioRepository;
import me.pimpao.gerenciamentopatrimonio.domain.service.UsuarioService;
import me.pimpao.gerenciamentopatrimonio.domain.service.exceptions.ObjectNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
		
	@Override
	public Usuario buscarPorId(UUID id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado! id: " 
				+ id + " Tipo: " + Usuario.class.getName()));
	}

	@Override
	public List<Usuario> buscarTodos() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}

	@Override
	public Usuario criar(UsuarioDto usuarioDto) {
		Usuario usuario = this.fromDto(usuarioDto);
		return usuarioRepository.save(usuario);
	}

	@Override
	public void excluir(UUID id) {
		Usuario usuario = this.buscarPorId(id);
		usuarioRepository.deleteById(usuario.getId());
	}
	
	private Usuario fromDto(UsuarioDto usuarioDto) {
		return new Usuario(usuarioDto.getNome(), usuarioDto.getEmail(), bCryptPasswordEncoder.encode(usuarioDto.getSenha()));
	}

}
