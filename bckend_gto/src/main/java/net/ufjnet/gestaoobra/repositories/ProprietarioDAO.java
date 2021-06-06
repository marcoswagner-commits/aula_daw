package net.ufjnet.gestaoobra.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ufjnet.gestaoobra.models.Proprietario;

public interface ProprietarioDAO extends JpaRepository<Proprietario, Integer> {
	
	Optional<Proprietario> findByNome (String nome);
	Optional<Proprietario> findByCpf(String cpf);
	Optional<Proprietario> findByEmail(String email);

}
