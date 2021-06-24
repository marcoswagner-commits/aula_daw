package net.ufjnet.gestaoobra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ufjnet.gestaoobra.dtos.TotalItemDTO;
import net.ufjnet.gestaoobra.models.Lancamento;

public interface LancamentoDAO extends JpaRepository<Lancamento, Integer> {
	

	@Query("SELECT new net.ufjnet.gestaoobra.dtos.TotalItemDTO(obj.item, SUM(obj.valor)) FROM Lancamento AS obj GROUP BY obj.item")
	List<TotalItemDTO> totalItem();

}
