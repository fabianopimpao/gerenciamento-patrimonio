package me.pimpao.gerenciamentopatrimonio.domain.service;

import me.pimpao.gerenciamentopatrimonio.domain.dto.PatrimonioDto;
import me.pimpao.gerenciamentopatrimonio.domain.entity.Patrimonio;

import java.util.List;
import java.util.UUID;

public interface PatrimonioService {
    Patrimonio buscarPorId(UUID id);

    List<Patrimonio> buscarTodos();

    Patrimonio criar(PatrimonioDto patrimonioDto);

    void atualizar(PatrimonioDto patrimonioDto, UUID id);

    void excluir(UUID id);
}
