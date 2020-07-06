package me.pimpao.gerenciamentopatrimonio.domain.service.impl;

import me.pimpao.gerenciamentopatrimonio.domain.dto.PatrimonioDto;
import me.pimpao.gerenciamentopatrimonio.domain.entity.Marca;
import me.pimpao.gerenciamentopatrimonio.domain.entity.Patrimonio;
import me.pimpao.gerenciamentopatrimonio.domain.repository.PatrimonioRepository;
import me.pimpao.gerenciamentopatrimonio.domain.service.MarcaService;
import me.pimpao.gerenciamentopatrimonio.domain.service.PatrimonioService;
import me.pimpao.gerenciamentopatrimonio.domain.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatrimonioServiceImpl implements PatrimonioService {

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @Autowired
    private MarcaService marcaService;

    @Override
    public Patrimonio buscarPorId(UUID id) {
        Optional<Patrimonio> patrimonio = patrimonioRepository.findById(id);
        return patrimonio.orElseThrow(() -> new ObjectNotFoundException("Patrimonio não encontrada! id: "
            + id + " Tipo: " + Patrimonio.class.getName()));
    }

    @Override
    public List<Patrimonio> buscarTodos() {
        List<Patrimonio> patrimonios = patrimonioRepository.findAll();
        return patrimonios;
    }

    @Override
    public Patrimonio criar(PatrimonioDto patrimonioDto) {
        Patrimonio patrimonio = this.fromPatrimonioDto(patrimonioDto);
        return patrimonioRepository.save(patrimonio);
    }

    @Override
    public void atualizar(PatrimonioDto patrimonioDto, UUID id) {
        Patrimonio patrimonio = this.buscarPorId(id);
        atualizarDados(patrimonio, patrimonioDto);
        patrimonioRepository.save(patrimonio);
    }

    @Override
    public void excluir(UUID id) {
        Patrimonio patrimonio = this.buscarPorId(id);
        patrimonioRepository.deleteById(patrimonio.getId());
    }

    private Patrimonio fromPatrimonioDto(PatrimonioDto patrimonioDto) {
        Marca marca = marcaService.buscarPorId(patrimonioDto.getMarcaId());
        return new Patrimonio(patrimonioDto.getNome(), patrimonioDto.getDescricao(), UUID.randomUUID(), marca);
    }

    private void atualizarDados(Patrimonio patrimonio, PatrimonioDto patrimonioDto) {
        patrimonio.setNome(patrimonioDto.getNome());
        patrimonio.setDescricao(patrimonioDto.getDescricao());
    }

}
