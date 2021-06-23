package net.ufjnet.gestaoobra.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.ufjnet.gestaoobra.dtos.LancamentoDTO;
import net.ufjnet.gestaoobra.services.GestaoLancamento;

@RestController
@RequestMapping("/v1/gto/lancamentos")
@Tag(name = "Endpoint de lancamento")
public class LancamentoController {
	
	@Autowired
	private GestaoLancamento service;
	
	@GetMapping
	@Operation(summary = "Busca todos os lançamentos")
	public ResponseEntity<CollectionModel<LancamentoDTO>> buscarTodos(
				@RequestParam(value="page", defaultValue = "0") int page,
				@RequestParam(value="limit", defaultValue = "12") int limit,
				@RequestParam(value="direction", defaultValue = "asc") String direction) {


			Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "descricao"));
			
			Page<LancamentoDTO> pages = service.findAll(pageable);
			pages
				.stream()
				.forEach(p -> p.add(
						linkTo(methodOn(LancamentoController.class).buscarUm(p.getCodigo())).withSelfRel()
					)
				);
		  	
			return ResponseEntity.ok(CollectionModel.of(pages));
		}

  
	
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Busca um lançamento por id")
	public ResponseEntity<LancamentoDTO> buscarUm(@PathVariable Integer id) {
			LancamentoDTO objDTO = service.findById(id);
			objDTO.add(linkTo(methodOn(LancamentoController.class).buscarUm(id)).withSelfRel());
			return ResponseEntity.ok(objDTO);
		}	

		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere um novo lançamento")
	public ResponseEntity<LancamentoDTO> incluir(@Valid @RequestBody LancamentoDTO objBody) {
		LancamentoDTO objDTO = service.save(objBody);
		objDTO.add(linkTo(methodOn(LancamentoController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@PutMapping
	@Operation(summary = "Atualiza um lançamento")
	public ResponseEntity<LancamentoDTO> atualizar(@RequestBody LancamentoDTO objBody) {
		
		LancamentoDTO objDTO = service.update(objBody);
		objDTO.add(linkTo(methodOn(LancamentoController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	
	


	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um lançamento por id")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}

		service.deleteById(id);

		return ResponseEntity.noContent().build();

	}


	
	


}
