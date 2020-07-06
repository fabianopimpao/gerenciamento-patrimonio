package me.pimpao.gerenciamentopatrimonio.domain.resource;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.pimpao.gerenciamentopatrimonio.domain.dto.UsuarioDto;
import me.pimpao.gerenciamentopatrimonio.domain.entity.Usuario;
import me.pimpao.gerenciamentopatrimonio.domain.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable("id") UUID id) {
		Usuario usuario = usuarioService.buscarPorId(id);
		return ResponseEntity.ok().body(new UsuarioDto(usuario));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDto>> buscarTodos() {
		List<Usuario> usuarios = usuarioService.buscarTodos();
		List<UsuarioDto> usuariosDto = usuarios
					.stream()
					.map(usuario -> new UsuarioDto(usuario))
					.collect(Collectors.toList());
		return ResponseEntity.ok().body(usuariosDto);
	}
	
	@PostMapping()
	public ResponseEntity<Void> criar(@Valid @RequestBody UsuarioDto usuarioDto) {
		Usuario usuario = usuarioService.criar(usuarioDto);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("{id}")
					.buildAndExpand(usuario.getId())
					.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") UUID id) {
		usuarioService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
