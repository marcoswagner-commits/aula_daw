package net.ufjnet.gestaoobra.services;

import java.util.List;
import java.util.Optional;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.repositories.ProprietarioDAO;

@AllArgsConstructor
@Service
public class GestaoProprietario {
	
	private ProprietarioDAO dao;
	
	@Transactional
	public List<Proprietario> findAll() {
		return dao.findAll();
		
	}
	
	@Transactional
	public Optional<Proprietario> findById(Integer id) {
			return dao.findById(id);
	}
	
	@Transactional
	public Proprietario save(Proprietario obj) {
			return dao.save(obj);
	}
	
	@Transactional
	public void deleteById(Integer id) {
			dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
	
	
}
	
