package net.ufjnet.gestaoobra.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.repositories.ProprietarioDAO;

@RestController
@RequestMapping("/gto/proprietarios")
public class ProprietarioController {
	
	@Autowired
	private ProprietarioDAO propDAO;
	
	@GetMapping
	public List<Proprietario> buscarTodos() {
		return propDAO.findAll();
		
	}
	
	//@GetMapping("/{id}")
	//public Proprietario buscarUm(@PathVariable Integer id) {
	//	Optional<Proprietario> obj = propDAO.findById(id);
	//	return obj.orElse(null); 
	//}
	
	@GetMapping("/{id}")
	public ResponseEntity<Proprietario> buscarUm(@PathVariable Integer id) {
		Optional<Proprietario> objOpt = propDAO.findById(id);
		Proprietario obj = objOpt.orElse(null);
		return ResponseEntity.ok(obj);
	}


}
