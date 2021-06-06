package net.ufjnet.gestaoobra.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.repositories.ProprietarioDAO;
import net.ufjnet.gestaoobra.services.exceptions.BusinessException;

@AllArgsConstructor
@Service
public class GestaoProprietario {
	
	private ProprietarioDAO dao;
	
	
	public List<Proprietario> findAll() {
		return dao.findAll();
		
	}
	
	
	public Optional<Proprietario> findById(Integer id) {
			return dao.findById(id);
	}
	
	public Optional<Proprietario> findByName(String nome) {
		return dao.findByNome(nome);
    }
	
	public Optional<Proprietario> findByCPF(String cpf) {
		return dao.findByCpf(cpf);
    }
	
	public Optional<Proprietario> findByEmail(String email) {
		return dao.findByEmail(email);
}
	
	@Transactional
	public Proprietario save(Proprietario obj) {
		boolean cpfExists = dao.findByCpf(obj.getCpf())
				.stream()
				.anyMatch(objResult -> !objResult.equals(obj));
		
		if (cpfExists) {
			throw new BusinessException("CPF já existente!");
		}
		
		boolean emailExists = dao.findByEmail(obj.getEmail())
				.stream()
				.anyMatch(objResult -> !objResult.equals(obj));
		
		if (emailExists) {
			throw new BusinessException("E-mail já existente!");
		}
		
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
	
