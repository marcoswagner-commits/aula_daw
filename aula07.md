# Aula 15/06/2021 - Desenvolvimento de Aplicações WEB
## Atividades da aula - roteiro

## Implementação do Modelo Conceitual

### Passo 1: Criar as classes de serviços (services)
- Criar os principais métodos de acesso aos dados (busca, inclusão, atualização e exclusão)
- Vincular o repositório com injeção por meio de construtor
- Fazer as anotações @Service e @Transactional
- Vide Código 1

### Passo 2: Criar o CRUD para proprietários
- Criar uma "Collection" para Gestão de Obras no PostMan
  - Criar um "Request" para Consultas
- Criar um endpoint para Salvar
  - Criar um "Request" para Inclusões
- Criar um endpoint para Atualizar
  - Criar um "Request" para Atualizações
- Criar um endpoint para Excluir
  - Criar um "Request" para Exclusões
- Vide Código 2


####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula07](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ba294e3ae0ee3da2378b3c9d5be18c7df419fb2c/Capa_aula07.png)](https://www.youtube.com/watch?v=zoL877ckzeU)
-
🥈:[![material complementar aula07](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ba294e3ae0ee3da2378b3c9d5be18c7df419fb2c/Capa_aula07.png)](https://www.youtube.com/watch?v=i6brsofWuew)
-
🥉:[![material complementar aula07](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ba294e3ae0ee3da2378b3c9d5be18c7df419fb2c/Capa_aula07.png)](https://www.youtube.com/watch?v=c3BEXOIWSEQ)
-
🥉:[![material complementar aula07](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ba294e3ae0ee3da2378b3c9d5be18c7df419fb2c/Capa_aula07.png)](https://www.youtube.com/watch?v=26GoufnXRPM)




:shipit: Código 1
```
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
	
	
}

```

:shipit: Código 2
```
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
	
	@PostMapping
	public ResponseEntity<Proprietario> incluir(@RequestBody Proprietario obj) {
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
```

### Passo 3: Atualizar o github com os códigos atuais


	
