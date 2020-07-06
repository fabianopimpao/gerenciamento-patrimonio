package me.pimpao.gerenciamentopatrimonio.domain.resource;

import me.pimpao.gerenciamentopatrimonio.domain.dto.PatrimonioDto;
import me.pimpao.gerenciamentopatrimonio.domain.service.PatrimonioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import me.pimpao.gerenciamentopatrimonio.domain.entity.Patrimonio;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/patrimonios")
public class PatrimonioResource {

	@Autowired
	private PatrimonioService patrimonioService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<PatrimonioDto> buscarPorId(@PathVariable("id") UUID id) {
		Patrimonio patrimonio = this.patrimonioService.buscarPorId(id);
		return ResponseEntity.ok().body(new PatrimonioDto(patrimonio));
	}

	@GetMapping
	public ResponseEntity<List<PatrimonioDto>> buscarTodos() {
		List<Patrimonio> patrimonios = this.patrimonioService.buscarTodos();
		List<PatrimonioDto> patrimoniosDtos = patrimonios
					.stream()
					.map(patrimonio -> new PatrimonioDto(patrimonio))
					.collect(Collectors.toList());
 		return ResponseEntity.ok().body(patrimoniosDtos);
	}

	@PostMapping
	public ResponseEntity<Void> criar(@RequestBody PatrimonioDto patrimonioDto) {
		Patrimonio patrimonio = this.patrimonioService.criar(patrimonioDto);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("{id}")
					.buildAndExpand(patrimonio.getId())
					.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@Valid @RequestBody PatrimonioDto patrimonioDto, @PathVariable("id") UUID id) {
		patrimonioService.atualizar(patrimonioDto, id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") UUID id) {
		patrimonioService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
