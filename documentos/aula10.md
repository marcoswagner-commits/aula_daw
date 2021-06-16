# Aula 10 - Desenvolvimento de Aplicações WEB

> Aula 24/06/2021
> 
>  *Estudo de caso: Gestão de Obras*


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual - Inclusão dos DTO e Consulta Paginada

### Passo 1: Incluir a camada de DTO's
- [x] Implementar a classe ProprietarioDTO
- [x] Atualizar a classe serviços
- [x] Atualizar a classe  controladores
- [x] Verificar o consumo da API com as alterações
- [x] Vide Códigos 1, 2 e 3


![Modelo de Arquitetura](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/947bf8022b213bb7fe183c39dae8c607a6d60212/modelo_camadas.png)

### Passo 2: Implantar a consulta paginada
  
- [x] Pageable
- [x] page, size, sort
- [x] Vide Códigos 1, 2 e 3 


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula10](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/aeef04d55b02c03d6bcdbb2176e0d6648cc9d2ca/documentos/Capa_aula10.png)](https://www.youtube.com/watch?v=-ecGyf8lIHo)
-
🥈:[![material complementar aula10](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/aeef04d55b02c03d6bcdbb2176e0d6648cc9d2ca/documentos/Capa_aula10.png)](https://www.youtube.com/watch?v=pztrSbH6yew)
-
🥉:[![material complementar aula10](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/aeef04d55b02c03d6bcdbb2176e0d6648cc9d2ca/documentos/Capa_aula10.png)](https://www.youtube.com/watch?v=Sc01PvnylLg)





:shipit: Código 1
```
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class ProprietarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Integer codigo ;
		
	private String nome;
		
	private String cpf;
		
	private String email;
	
	public ProprietarioDTO (Proprietario obj) {
		this.codigo = obj.getCodigo();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		
	}
	
}

```

:shipit: Código 2
```
@AllArgsConstructor
@Service
public class GestaoProprietario {
	
	private ProprietarioDAO dao;
	
	
	@Transactional(readOnly = true)
	public Page<ProprietarioDTO> findAll(Pageable pageable) {
		Page<Proprietario> result = dao.findAll(pageable);
		return result.map(obj -> new ProprietarioDTO(obj));
				
		
	}
	
	
	@Transactional(readOnly = true)
	public Optional<ProprietarioDTO> findById(Integer id) {
		Optional<Proprietario> result = dao.findById(id);
		return result.map(obj -> new ProprietarioDTO(obj));
				
			
	}
	
	@Transactional(readOnly = true)
	public Optional<ProprietarioDTO> findByName(String nome) {
		Optional<Proprietario> result = dao.findByNome(nome);
		return result.map(obj -> new ProprietarioDTO(obj));
		
    }
	
	@Transactional(readOnly = true)
	public Optional<ProprietarioDTO> findByCPF(String cpf) {
		Optional<Proprietario> result = dao.findByCpf(cpf);
		return result.map(obj -> new ProprietarioDTO(obj));
		
    }
	
	@Transactional(readOnly = true)
	public Optional<ProprietarioDTO> findByEmail(String email) {
		Optional<Proprietario> result = dao.findByEmail(email);
		return result.map(obj -> new ProprietarioDTO(obj));
		
}
	
	@Transactional
	public ProprietarioDTO save(Proprietario obj) {
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
		
			return new ProprietarioDTO(dao.save(obj));
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

:shipit: Código 3
```

@RestController
@RequestMapping("/gto/proprietarios")
public class ProprietarioController {
	
	@Autowired
	private GestaoProprietario service;
	
	@GetMapping
	public ResponseEntity<Page<ProprietarioDTO>> buscarTodos(Pageable pageable) {
		Page<ProprietarioDTO> result = service.findAll(pageable);
		return ResponseEntity.ok(result);
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ProprietarioDTO> buscarUm(@PathVariable Integer id) {
		return service.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<ProprietarioDTO> buscarNome(@PathVariable String nome) {
		return service.findByName(nome)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<ProprietarioDTO> buscarCpf(@PathVariable String cpf) {
		return service.findByCPF(cpf)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	public ResponseEntity<ProprietarioDTO> incluir(@Valid @RequestBody Proprietario obj) {
		ProprietarioDTO objDTO = service.save(obj);
		return ResponseEntity.created(null).body(objDTO);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProprietarioDTO> atualizar(@PathVariable Integer id, @RequestBody Proprietario obj ) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		obj.setCodigo(id);
		
		ProprietarioDTO objDTO = service.save(obj); 
		
		return ResponseEntity.ok(objDTO);
		
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


```


### Passo 3: Atualizar o github com os códigos atuais (dtos)
