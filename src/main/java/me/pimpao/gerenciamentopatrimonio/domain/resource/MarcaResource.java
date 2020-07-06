package me.pimpao.gerenciamentopatrimonio.domain.resource;

import me.pimpao.gerenciamentopatrimonio.domain.dto.MarcaDto;
import me.pimpao.gerenciamentopatrimonio.domain.entity.Marca;
import me.pimpao.gerenciamentopatrimonio.domain.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/marcas")
public class MarcaResource {

    @Autowired
    private MarcaService marcaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MarcaDto> buscarPorId(@PathVariable("id") UUID id) {
        Marca marca = marcaService.buscarPorId(id);
        return ResponseEntity.ok().body(new MarcaDto(marca));
    }

    @GetMapping
    public ResponseEntity<List<MarcaDto>> buscarTodas() {
        List<Marca> marcas = marcaService.buscarTodas();
        List<MarcaDto> marcasDto = marcas.stream().map(marca -> new MarcaDto(marca)).collect(Collectors.toList());
        return ResponseEntity.ok().body(marcasDto);
    }

    @GetMapping(value = "/{id}/patrimonios")
    public ResponseEntity<Marca> buscarTodosPatrimonios(@PathVariable("id") UUID id) {
        Marca marca = marcaService.buscarPorId(id);
        return ResponseEntity.ok().body(marca);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> criar(@Valid @RequestBody MarcaDto marcaDto) {
        Marca novaMarca = marcaService.criar(marcaDto);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("{id}")
                    .buildAndExpand(novaMarca.getId())
                    .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> atualizar(@Valid @RequestBody MarcaDto marcaDto, @PathVariable("id") UUID id) {
        marcaService.atualizar(marcaDto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") UUID id) {
        marcaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
