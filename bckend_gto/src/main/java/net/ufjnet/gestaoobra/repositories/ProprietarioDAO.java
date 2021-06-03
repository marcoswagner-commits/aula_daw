package net.ufjnet.gestaoobra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ufjnet.gestaoobra.models.Proprietario;

public interface ProprietarioDAO extends JpaRepository<Proprietario, Integer> {

}
