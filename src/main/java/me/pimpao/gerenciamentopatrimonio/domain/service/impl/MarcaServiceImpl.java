package me.pimpao.gerenciamentopatrimonio.domain.service.impl;

import me.pimpao.gerenciamentopatrimonio.domain.dto.MarcaDto;
import me.pimpao.gerenciamentopatrimonio.domain.entity.Marca;
import me.pimpao.gerenciamentopatrimonio.domain.repository.MarcaRepository;
import me.pimpao.gerenciamentopatrimonio.domain.service.MarcaService;
import me.pimpao.gerenciamentopatrimonio.domain.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public Marca buscarPorId(UUID id) {
        Optional<Marca> marca = this.marcaRepository.findById(id);
        return marca.orElseThrow(() -> new ObjectNotFoundException("Marca não encontrada! id: " + id
                + " tipo: " + Marca.class.getName()));
    }

    @Override
    public List<Marca> buscarTodas() {
        List<Marca> marcas = this.marcaRepository.findAll();
        return marcas;
    }

    @Override
    public Marca criar(MarcaDto marcaDto) {
    	Marca marca = this.fromDto(marcaDto);
        return this.marcaRepository.save(marca);
    }    

	@Override
    public void atualizar(MarcaDto marcaDto, UUID id) {
        Marca marca = this.buscarPorId(id);
        atualizarDados(marca, marcaDto);
        this.marcaRepository.save(marca);
    }    

	@Override
    public void excluir(UUID id) {
        Marca marca = this.buscarPorId(id);
        this.marcaRepository.deleteById(marca.getId());
    }

    private Marca fromDto(MarcaDto marcaDto) {
		return new Marca(marcaDto.getNome());
	}
    
    private void atualizarDados(Marca marca, MarcaDto marcaDto) {
		marca.setNome(marcaDto.getNome());		
	}

}
