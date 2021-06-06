package net.ufjnet.gestaoobra.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ufjnet.gestaoobra.models.Proprietario;
import net.ufjnet.gestaoobra.services.GestaoProprietario;

@RestController
@RequestMapping("/gto/proprietarios")
public class ProprietarioController {
	
	@Autowired
	private GestaoProprietario service;
	
	@GetMapping
	public List<Proprietario> buscarTodos() {
		return service.findAll();
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Proprietario> buscarUm(@PathVariable Integer id) {
		return service.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Proprietario> buscarNome(@PathVariable String nome) {
		return service.findByName(nome)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Proprietario> buscarCpf(@PathVariable String cpf) {
		return service.findByCPF(cpf)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	public ResponseEntity<Proprietario> incluir(@Valid @RequestBody Proprietario obj) {
		obj = service.save(obj);
		return ResponseEntity.created(null).body(obj);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Proprietario> atualizar(@PathVariable Integer id, @RequestBody Proprietario obj ) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		obj.setCodigo(id);
		obj = service.save(obj);
		return ResponseEntity.ok(obj);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		service.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
	


}
