# Aula 14 - Desenvolvimento de Aplicações WEB

> Aula 08/07/2021
> 
>  * Estudo de caso: Gestão de Obras *


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Implementação de Obras (Model - Repositório - Serviço - DTO - Controller)

![Relação entre Obras e Proprietário](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/a07b4badee4940c720a9e62a9fe9e9d3105825c8/documentos/obras_proprietario.png)

### Passo 1: Construir as relações entre as classes Proprietario e Obra
- [x] Criar em obra o vínculo com proprietário - [código](#código-1---obra-proprietario)
- [x] Criar em proprietário o vínculo com obra - [código](#código-2---proprietario-obra)
- [x] Adequar as outras camadas a partir da relação (DAO - DTO - Service - Controller) - [códigos](#código-3---camadas)
- [x] Readequar as validações (inserir validações em grupo) 

![Relação entre Item e Sub-Item](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/0c6016346d60abc8fc93f74d83e9e4c0d90fcbee/documentos/item_subitem.png)

### Passo 2: Criar as classes de item e subitem
- [x] Método de Produtividade BackEnd
- [x] Criar as classes Item e Subitem em "Models"
- [x] Criar as interfaces "repositories" ItemDAO e SubItemDAO
- [x] Criar as classes "DTOs" ItemDTO e SubItemDTO
- [x] Criar as classes "services" GestaoItem e GestaoSubItem
- [x] Criar as classes "Controllers" ItemController e SubItemController - [códigos de Item e SubItem](#código-final---item-e-subitem)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/27a04caaa2d7675820eb14a0ec76d0c4a60575d5/documentos/Capa_Aula14.png)](https://www.youtube.com/watch?v=TCSLJU4TNlo)
-
🥈:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/27a04caaa2d7675820eb14a0ec76d0c4a60575d5/documentos/Capa_Aula14.png)](https://www.youtube.com/watch?v=hLZkcOfJvNc)
-
🥉:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/27a04caaa2d7675820eb14a0ec76d0c4a60575d5/documentos/Capa_Aula14.png)](https://www.youtube.com/watch?v=sjmjxv4HqEE)
-
🥉:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/27a04caaa2d7675820eb14a0ec76d0c4a60575d5/documentos/Capa_Aula14.png)](https://www.youtube.com/watch?v=jJCiOM1JigM)


:shipit: 
### Código 1 - obra proprietario
```
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "OBRAS")
public class Obra implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_obra")
	private Integer codigo ;
	
	
	@Column(name = "descricao_obra", nullable = false)
	private String descricao;
	
	
	@Column(name = "localizacao_obra", nullable = false)
	private String localizacao;
	
	
	@Column(name = "complemento_obra")
	private String complemento;
	
	
	@ManyToOne()
	private Proprietario proprietario;
	
}

```
[voltar](#passo-1-construir-as-relações-entre-as-classes-proprietario-e-obra)

### Código 2 - Proprietario Obra
```
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PROPRIETARIOS")
public class Proprietario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_prop")
	private Integer codigo ;
	
	
	@Column(name = "nome_prop", nullable = false)
	private String nome;
	
	
	@Column(name = "cpf_prop", nullable = false)
	private String cpf;
	
	
	@Column(name = "email_prop", nullable = false)
	private String email;
	
	@OneToMany(mappedBy = "proprietario")
	private List<Obra> obras = new ArrayList<>();
	
	
	public Proprietario(Integer codigo, String nome, String cpf, String email) {
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		
	}
	
}

```
[voltar](#passo-1-construir-as-relações-entre-as-classes-proprietario-e-obra)



:shipit: 
### Código 3 - Camadas
```
public interface ObraDAO extends JpaRepository<Obra, Integer> {
}

```
[voltar](#passo-1-construir-as-relações-entre-as-classes-proprietario-e-obra)

:shipit: 
### Código 3 - Camadas
```
@AllArgsConstructor
@Service
public class GestaoObra {
	
	private ObraDAO dao;
	
	private ProprietarioDAO propDAO;
	
	
	@Transactional(readOnly = true)
	public Page<ObraDTO> findAll(Pageable pageable) {
		Page<Obra> result = dao.findAll(pageable);
		return result.map(obj -> new ObraDTO(obj));
				
		
	}
	
	

	@Transactional(readOnly = true)
	public ObraDTO findById(Integer id) {
		Obra result = dao.findById(id).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new ObraDTO(result);
			
	}
	
	

	@Transactional
	public ObraDTO update(ObraDTO obj) {
		Obra entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		
		entity.setDescricao(obj.getDescricao());
		entity.setLocalizacao(obj.getLocalizacao());
		entity.setComplemento(obj.getComplemento());
		
		return new ObraDTO(dao.save(entity));
		
		
	}	
	
	
	@Transactional
	public ObraDTO save(ObraDTO obj) {
				
        Optional<Proprietario> prop = propDAO.findById(obj.getProprietario().getCodigo());
        		
        Obra entity = new Obra(obj.getCodigo(), obj.getDescricao(), 
				obj.getLocalizacao(), obj.getComplemento(),
				new Proprietario(prop.get().getCodigo(),
						prop.get().getNome(),
						prop.get().getCpf(),
						prop.get().getEmail()));

        entity.setProprietario(prop.orElse(null));
		
		
		
		
		return new ObraDTO(dao.save(entity));
	}
	

	
	@Transactional
	public void deleteById(Integer id) {
			dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
	
}
	
```
[voltar](#passo-1-construir-as-relações-entre-as-classes-proprietario-e-obra)


:shipit: 
### Código 3 - Camadas
```
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
@JsonPropertyOrder({"codigo_obra", "proprietario",  "descricao_obra", "localizacao_obra", "complemento_obra"})
public class ObraDTO extends RepresentationModel<ObraDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@EqualsAndHashCode.Include
	@JsonProperty("codigo_obra")
	private Integer codigo;
		
	@NotBlank
	@Size(max=255)
	@JsonProperty("descricao_obra")
	private String descricao;
	
	@NotBlank
	@Size(max=255)
	@JsonProperty("localizacao_obra")
	private String localizacao;
		
	@JsonProperty("complemento_obra")
	private String complemento;
	
	
	
	@NotNull
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationsGroups.ProprietarioId.class)
	private ProprietarioDTO proprietario;
	
	public ObraDTO (Obra obj) {
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		localizacao = obj.getLocalizacao();
		complemento = obj.getComplemento();
		proprietario = new ProprietarioDTO(obj.getProprietario());
	}
	
}

```
[voltar](#passo-1-construir-as-relações-entre-as-classes-proprietario-e-obra)

:shipit: 
### Código 5 - ObrasController
```
@RestController
@RequestMapping("/v1/gto/obras")
@Tag(name = "Endpoint de Obra")
public class ObraController {
	
	@Autowired
	private GestaoObra service;
	
	@GetMapping
	@Operation(summary = "Busca todas os obras")
	public ResponseEntity<CollectionModel<ObraDTO>> buscarTodos(
				@RequestParam(value="page", defaultValue = "0") int page,
				@RequestParam(value="limit", defaultValue = "12") int limit,
				@RequestParam(value="direction", defaultValue = "asc") String direction) {


			Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "descricao"));
			
			Page<ObraDTO> pages = service.findAll(pageable);
			pages
				.stream()
				.forEach(p -> p.add(
						linkTo(methodOn(ObraController.class).buscarUm(p.getCodigo())).withSelfRel()
					)
				);
		  	
			return ResponseEntity.ok(CollectionModel.of(pages));
		}

  
	
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Busca uma obra por id")
	public ResponseEntity<ObraDTO> buscarUm(@PathVariable Integer id) {
			ObraDTO objDTO = service.findById(id);
			objDTO.add(linkTo(methodOn(ObraController.class).buscarUm(id)).withSelfRel());
			return ResponseEntity.ok(objDTO);
		}	

		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere uma nova obra")
	public ResponseEntity<ObraDTO> incluir(@Valid @RequestBody ObraDTO objBody) {
		ObraDTO objDTO = service.save(objBody);
		objDTO.add(linkTo(methodOn(ObraController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@PutMapping
	@Operation(summary = "Atualiza uma obra")
	public ResponseEntity<ObraDTO> atualizar(@RequestBody ObraDTO objBody) {
		
		ObraDTO objDTO = service.update(objBody);
		objDTO.add(linkTo(methodOn(ObraController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	
	


	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui uma obra por id")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}

		service.deleteById(id);

		return ResponseEntity.noContent().build();

	}
}

```
[voltar](#passo-1-construir-as-relações-entre-as-classes-proprietario-e-obra)


### Código Final - Item e SubItem

```
//========================= Item

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ITENS")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_item")
	private Integer codigo ;
	
	
	@Column(name = "descricao_item", nullable = false)
	private String descricao;
	
	
	@Column(name = "complemento_item")
	private String complemento;
	

	@OneToMany(mappedBy = "item")
	private List<SubItem> subitens = new ArrayList<>();
	
	
	public Item(Integer codigo, String descricao, String complemento) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.complemento = complemento;
		
		
	}
	
}

//========================= SubItem

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "SUBITENS")
public class SubItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_subitem")
	private Integer codigo ;
	
	
	@Column(name = "descricao_subitem", nullable = false)
	private String descricao;
	
		
	@Column(name = "complemento_subitem")
	private String complemento;
	
	
	@ManyToOne()
	private Item item;
	
}

//========================= ItemDTO

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
@JsonPropertyOrder({"codigo_item",  "descricao_item", "complemento_obra"})
public class ItemDTO extends RepresentationModel<ItemDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@EqualsAndHashCode.Include
	@JsonProperty("codigo_item")
	private Integer codigo;
		
	@NotBlank
	@Size(max=255)
	@JsonProperty("descricao_item")
	private String descricao;
	
		
	@JsonProperty("complemento_item")
	private String complemento;

	
	public ItemDTO (Item obj) {
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		complemento = obj.getComplemento();
		
		
		
		
	}
	
}

//========================= SubItemDTO

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
@JsonPropertyOrder({"codigo_subitem", "item",  "descricao_subitem", "complemento_subitem"})
public class SubItemDTO extends RepresentationModel<SubItemDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@EqualsAndHashCode.Include
	@JsonProperty("codigo_subitem")
	private Integer codigo;
		
	@NotBlank
	@Size(max=255)
	@JsonProperty("descricao_subitem")
	private String descricao;
	
			
	@JsonProperty("complemento_subitem")
	private String complemento;
		
	
	@NotNull
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationsGroups.ProprietarioId.class)
	private ItemDTO item;
	
	public SubItemDTO (SubItem obj) {
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		complemento = obj.getComplemento();
		item = new ItemDTO(obj.getItem());
		
		
		
	}
	
}

//========================= ItemDAO

public interface ItemDAO extends JpaRepository<Item, Integer> {
}

//========================= SubItemDAO

public interface SubItemDAO extends JpaRepository<SubItem, Integer> {
}

//========================= GestaoItem

@AllArgsConstructor
@Service
public class GestaoItem {
	
	private ItemDAO dao;
	
	@Transactional(readOnly = true)
	public Page<ItemDTO> findAll(Pageable pageable) {
		Page<Item> result = dao.findAll(pageable);
		return result.map(obj -> new ItemDTO(obj));
				
		
	}
	
	

	@Transactional(readOnly = true)
	public ItemDTO findById(Integer id) {
		Item result = dao.findById(id).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new ItemDTO(result);
			
	}
	
	

	@Transactional
	public ItemDTO update(ItemDTO obj) {
		Item entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		
		entity.setDescricao(obj.getDescricao());
		entity.setComplemento(obj.getComplemento());
		
		return new ItemDTO(dao.save(entity));
		
		
	}	
	
	
	@Transactional
	public ItemDTO save(ItemDTO obj) {
		        		
        Item entity = new Item(obj.getCodigo(), obj.getDescricao(),  obj.getComplemento());
				
		return new ItemDTO(dao.save(entity));
	}
	

	
	@Transactional
	public void deleteById(Integer id) {
			dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
	
	
}


//========================= GestaoSubItem

@AllArgsConstructor
@Service
public class GestaoSubItem {
	
	private SubItemDAO dao;
	
	private ItemDAO itemDAO;
	
	
	@Transactional(readOnly = true)
	public Page<SubItemDTO> findAll(Pageable pageable) {
		Page<SubItem> result = dao.findAll(pageable);
		return result.map(obj -> new SubItemDTO(obj));
				
		
	}
	
	

	@Transactional(readOnly = true)
	public SubItemDTO findById(Integer id) {
		SubItem result = dao.findById(id).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new SubItemDTO(result);
			
	}
	
	

	@Transactional
	public SubItemDTO update(SubItemDTO obj) {
		SubItem entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		
		entity.setDescricao(obj.getDescricao());
		entity.setComplemento(obj.getComplemento());
		
		return new SubItemDTO(dao.save(entity));
		
		
	}	
	
	
	@Transactional
	public SubItemDTO save(SubItemDTO obj) {
				
        Optional<Item> item = itemDAO.findById(obj.getItem().getCodigo());
        		
        SubItem entity = new SubItem(obj.getCodigo(), obj.getDescricao(), 
        		obj.getComplemento(),
				new Item(item.get().getCodigo(),
						item.get().getDescricao(),
						item.get().getComplemento()));

        entity.setItem(item.orElse(null));
		
		
		
		
		return new SubItemDTO(dao.save(entity));
	}
	

	
	@Transactional
	public void deleteById(Integer id) {
			dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
	
	
}

//========================= ItemController
@RestController
@RequestMapping("/v1/gto/itens")
@Tag(name = "Endpoint de Item")
public class ItemController {
	
	@Autowired
	private GestaoItem service;
	
	@GetMapping
	@Operation(summary = "Busca todos os itens")
	public ResponseEntity<CollectionModel<ItemDTO>> buscarTodos(
				@RequestParam(value="page", defaultValue = "0") int page,
				@RequestParam(value="limit", defaultValue = "12") int limit,
				@RequestParam(value="direction", defaultValue = "asc") String direction) {


			Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "descricao"));
			
			Page<ItemDTO> pages = service.findAll(pageable);
			pages
				.stream()
				.forEach(p -> p.add(
						linkTo(methodOn(ItemController.class).buscarUm(p.getCodigo())).withSelfRel()
					)
				);
		  	
			return ResponseEntity.ok(CollectionModel.of(pages));
		}

  
	
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Busca um item por id")
	public ResponseEntity<ItemDTO> buscarUm(@PathVariable Integer id) {
			ItemDTO objDTO = service.findById(id);
			objDTO.add(linkTo(methodOn(ItemController.class).buscarUm(id)).withSelfRel());
			return ResponseEntity.ok(objDTO);
		}	

		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere um novo item")
	public ResponseEntity<ItemDTO> incluir(@Valid @RequestBody ItemDTO objBody) {
		ItemDTO objDTO = service.save(objBody);
		objDTO.add(linkTo(methodOn(ItemController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@PutMapping
	@Operation(summary = "Atualiza um item")
	public ResponseEntity<ItemDTO> atualizar(@RequestBody ItemDTO objBody) {
		
		ItemDTO objDTO = service.update(objBody);
		objDTO.add(linkTo(methodOn(ItemController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	
	


	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um item por id")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}

		service.deleteById(id);

		return ResponseEntity.noContent().build();

	}
}


//========================= SubItemController
@RestController
@RequestMapping("/v1/gto/subsubitens")
@Tag(name = "Endpoint de subitem")
public class SubItemController {
	
	@Autowired
	private GestaoSubItem service;
	
	@GetMapping
	@Operation(summary = "Busca todos os subitens")
	public ResponseEntity<CollectionModel<SubItemDTO>> buscarTodos(
				@RequestParam(value="page", defaultValue = "0") int page,
				@RequestParam(value="limit", defaultValue = "12") int limit,
				@RequestParam(value="direction", defaultValue = "asc") String direction) {


			Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "descricao"));
			
			Page<SubItemDTO> pages = service.findAll(pageable);
			pages
				.stream()
				.forEach(p -> p.add(
						linkTo(methodOn(SubItemController.class).buscarUm(p.getCodigo())).withSelfRel()
					)
				);
		  	
			return ResponseEntity.ok(CollectionModel.of(pages));
		}

  
	
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Busca um subitem por id")
	public ResponseEntity<SubItemDTO> buscarUm(@PathVariable Integer id) {
			SubItemDTO objDTO = service.findById(id);
			objDTO.add(linkTo(methodOn(SubItemController.class).buscarUm(id)).withSelfRel());
			return ResponseEntity.ok(objDTO);
		}	

		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere um novo subitem")
	public ResponseEntity<SubItemDTO> incluir(@Valid @RequestBody SubItemDTO objBody) {
		SubItemDTO objDTO = service.save(objBody);
		objDTO.add(linkTo(methodOn(SubItemController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@PutMapping
	@Operation(summary = "Atualiza um subitem")
	public ResponseEntity<SubItemDTO> atualizar(@RequestBody SubItemDTO objBody) {
		
		SubItemDTO objDTO = service.update(objBody);
		objDTO.add(linkTo(methodOn(SubItemController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	
	


	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um subitem por id")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}

		service.deleteById(id);

		return ResponseEntity.noContent().build();

	}
}
```
[voltar](#passo-2-criar-as-classes-de-item-e-subitem)

### Passo 2: Atualizar o github com os códigos atuais (camada com obras)

