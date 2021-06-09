# Aula 11 - Desenvolvimento de Aplicações WEB

> 
> 
>  *Estudo de caso: Gestão de Obras* Níveis de Maturidade de Richardson - HATEOAS


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual

### Passo 1: Analisar e adequar a arquitetura REST
- [x] Verificar os níveis de maturidade da API em relação ao REST
- [x] Implantar o modelo (nível 3) HATEOAS
  - [x] Atualizar a classe de DTO (classe extends RepresentationModel<>)
  - [x] Colocar as anotações @EnableAutoConfiguration e @ComponentScan (springframework.context.annotation) na classe principal 
  - [x] Atualizar a classe  controladores
    - [x] Colocar suporte ao Hateoas (link de autorelacionamento) e sofisticação final - Vide Códigos
  - [x] Verificar o consumo da API com as alterações


✏️ Dependência necessária para uso do HATEOAS
```
<dependency>
	<groupId>org.springframework.hateoas</groupId>
	<artifactId>spring-hateoas</artifactId>
</dependency>
```

✏️ Imports necessários para uso do HATEOAS
```
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
```


### Passo 2: Versionamento e Serialização
  
- [x] Incluir versão no Path (v1)
- [x] Serialização
- [x] Organização do JSON na classe DTO (JsonPropertyOrder())
- [x] Definição de nomes de campos com o JsonProperty()
- [x] Ocultação de campos com JsonIgnore


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula10](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=-ecGyf8lIHo)
-
🥈:[![material complementar aula10](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=pztrSbH6yew)
-
🥉:[![material complementar aula10](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=Sc01PvnylLg)





:shipit: Código 1 - ProprietarioController (busca)
```
@GetMapping
public ResponseEntity<CollectionModel<ProprietarioDTO>> buscarTodos(
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "12") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction) {


		Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		
		Page<ProprietarioDTO> pages = service.findAll(pageable);
		pages
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(ProprietarioController.class).findById(p.getCodigo())).withSelfRel()
				)
			);
	  	
		return ResponseEntity.ok(CollectionModel.of(pages));
	}

```

:shipit: Código 2 - ProprietarioController (buscas específicas)
```
@GetMapping("/{id}")
public ResponseEntity<ProprietarioDTO> buscarUm(@PathVariable Integer id) {
		ProprietarioDTO objDTO = service.findById(id);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).findById(id)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	
  
@GetMapping("/{nome}")
public ResponseEntity<ProprietarioDTO> buscarNome(@PathVariable String nome) {
		ProprietarioDTO objDTO = service.findByNome(nome);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).findByName(nome)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	
  
@GetMapping("/{cpf}")
public ResponseEntity<ProprietarioDTO> buscarCpf(@PathVariable String cpf) {
		ProprietarioDTO objDTO = service.findByCPF(nome);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).findByCPF(cpf)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	
  
@GetMapping("/{email}")
public ResponseEntity<ProprietarioDTO> buscarEmail(@PathVariable String email) {
		ProprietarioDTO objDTO = service.findByEmail(email);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).findByEmail(email)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	
  
```

:shipit: Código 3 - ProprietarioController (inclusão, atualização e exclusão)
```
  @PostMapping
  @ResponseStatus(HttpStatus.CREATE)
	public ResponseEntity<ProprietarioDTO> incluir(@RequestBody AlunoVO objBody) {
		ProprietarioDTO objDTO = service.create(objBody);
		objDTO.add(linkTo(methodOn(AlunoController.class).findById(objVO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}
	
	@PutMapping
	public ResponseEntity<ProprietarioDTO> atualizar(@PathVariable Integer id, @RequestBody Proprietario obj ) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		obj.setCodigo(id);
    ProprietarioDTO objDTO = service.save(objBody);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).findById(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		
    ProprietarioDTO objDTO = service.deleteById(id);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).findById(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.noContent().build();
		
	}

```

### Passo 3: Atualizar o github com os códigos atuais (dtos)
