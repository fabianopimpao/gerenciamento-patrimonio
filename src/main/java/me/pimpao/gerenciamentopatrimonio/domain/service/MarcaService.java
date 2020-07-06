package me.pimpao.gerenciamentopatrimonio.domain.service;

import java.util.List;
import java.util.UUID;

import me.pimpao.gerenciamentopatrimonio.domain.dto.MarcaDto;
import me.pimpao.gerenciamentopatrimonio.domain.entity.Marca;

public interface MarcaService {

    Marca buscarPorId(UUID id);
    List<Marca> buscarTodas();
    Marca criar(MarcaDto marcaDto);
    void atualizar(MarcaDto marcaDto, UUID id);
    void excluir(UUID id);

}
