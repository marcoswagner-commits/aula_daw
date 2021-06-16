package net.ufjnet.gestaoobra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ufjnet.gestaoobra.models.Obra;

public interface ObraDAO extends JpaRepository<Obra, Integer> {
	


}
