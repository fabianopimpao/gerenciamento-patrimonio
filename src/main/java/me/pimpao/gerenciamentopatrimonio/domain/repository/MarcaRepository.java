package me.pimpao.gerenciamentopatrimonio.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.pimpao.gerenciamentopatrimonio.domain.entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, UUID> {
	
	Marca findByNome(String nome);
	
}
