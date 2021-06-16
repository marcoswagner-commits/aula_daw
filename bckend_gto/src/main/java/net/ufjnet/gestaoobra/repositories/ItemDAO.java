package net.ufjnet.gestaoobra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ufjnet.gestaoobra.models.Item;

public interface ItemDAO extends JpaRepository<Item, Integer> {
	


}
