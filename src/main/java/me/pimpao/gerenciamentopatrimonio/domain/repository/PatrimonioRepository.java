package me.pimpao.gerenciamentopatrimonio.domain.repository;

import me.pimpao.gerenciamentopatrimonio.domain.entity.Patrimonio;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, UUID> {
}
