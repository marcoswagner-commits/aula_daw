package net.ufjnet.gestaoobra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ufjnet.gestaoobra.models.Lancamento;

public interface LancamentoDAO extends JpaRepository<Lancamento, Integer> {
	


}
