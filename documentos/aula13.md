# Aula 13 - Desenvolvimento de Aplicações WEB

> Aula 06/07/2021
> 
>  * Estudo de caso: Gestão de Obras *


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Implementação de Obras (Model - Repositório - Serviço - DTO - Controller)

- [Relação entre Obras e Proprietário](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/a07b4badee4940c720a9e62a9fe9e9d3105825c8/documentos/obras_proprietario.png)

### Passo 1: Criar o contexto Obras
- [x] Testar a API na nuvem - com http://gestao-obra-daw.herokuapp.com/swagger-ui.html
- [x] Método de Produtividade BackEnd
- [x] Criar a classe Obra em "Models" - [código](#código-1---obra)
- [x] Criar a interface "repositories" ObraDAO - [código](#código-2---obradao)
- [x] Criar a classe "services" GestaoObra - [código](#codigo-3---gestaoobra)
- [x] Criar a classe "DTOs" ObraDTO - [código](#codigo-4---obradto)
- [x] Criar a classe "Controllers" ObraController - [código](#codigo-5---obracontroller)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula12](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=T3IMjyn1OUA)
-
🥈:[![material complementar aula12](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=XCzhtOriPZc)
-
🥉:[![material complementar aula12](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=REAEchLNRyg)


:shipit: 
### Código 1 - Obra
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
	
	@NotBlank
	@Size(max=255)
	@Column(name = "descricao_obra", nullable = false)
	private String descricao;
	
	@NotBlank
	@Size(max=255)
	@Column(name = "localizacao_obra", nullable = false)
	private String localizacao;
	
	
	@Column(name = "complemento_obra")
	private String complemento;
		
}

```
[voltar](#passo-1-criar-o-contexto-bras)


:shipit: 
### Código 2 - ObraDAO
```
public interface ObraDAO extends JpaRepository<Obra, Integer> {
}
```
[voltar](#passo-1-analisar-e-adequar-a-arquitetura-rest)

:shipit: 
### Código 3 - GestaoObra
```
@AllArgsConstructor
@Service
public class GestaoObra {
	
	private ObraDAO dao;
	
	
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
		Obra entity = new Obra(obj.getCodigo(), obj.getDescricao(), obj.getLocalizacao(), obj.getComplemento());
		
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
[voltar](#passo-3-instalando-e-configurando-o-security)


:shipit: 
### Código 4 - ObraDTO
```
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
@JsonPropertyOrder({"codigo_obra", "descricao_obra", "localizacao_obra", "complemento_obra"})
public class ObraDTO extends RepresentationModel<ObraDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@EqualsAndHashCode.Include
	@JsonProperty("codigo_obra")
	private Integer codigo;
		
	@JsonProperty("descricao_obra")
	private String descricao;
	
	@JsonProperty("localizacao_obra")
	private String localizacao;
		
	@JsonProperty("complemento_obra")
	private String complemento;
	
	public ObraDTO (Obra obj) {
		this.codigo = obj.getCodigo();
		this.descricao = obj.getDescricao();
		this.localizacao = obj.getLocalizacao();
		this.complemento = obj.getComplemento();
		
	}
	
}
```
[voltar](#passo-3-instalando-e-configurando-o-security)

:shipit: 
### Código 5 - ObraController
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
	public ResponseEntity<ObraDTO> incluir(@RequestBody ObraDTO objBody) {
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
[voltar](#passo-3-instalando-e-configurando-o-security)

### Passo 2: Atualizar o github com os códigos atuais (camada com obras)

