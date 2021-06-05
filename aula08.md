# Aula 08 - Desenvolvimento de Aplicações WEB
## Atividades da aula - roteiro

## Implementação do Modelo Conceitual

### Passo 1: Criar funcionalidades (starters e classes) para tratamento de erros
- Adicionar o pacote Spring Validation
- Fazer anotações na classe Proprietario (Model) - @NotBlank - @Size - @Email
- Criar um pacote exceptionHandler
- Criar a classe ExceptionHandler
- Vide Código 1

### Passo 2: Melhorar o tratamento de mensagens
- Criar uma classe StandardError
- Sobrescrever o método handleMethodArgumentNotValid
- Vincular a classe StandardErro ao método
- Verificar os retornos de erros
- Atualizar mensagens criando o arquivo messages.properties na pasta "Resources"
- Vide Código 2


####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ba294e3ae0ee3da2378b3c9d5be18c7df419fb2c/Capa_aula07.png)](https://www.youtube.com/watch?v=zoL877ckzeU)
-
🥈:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ba294e3ae0ee3da2378b3c9d5be18c7df419fb2c/Capa_aula07.png)](https://www.youtube.com/watch?v=i6brsofWuew)
-
🥉:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ba294e3ae0ee3da2378b3c9d5be18c7df419fb2c/Capa_aula07.png)](https://www.youtube.com/watch?v=c3BEXOIWSEQ)
-
🥉:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ba294e3ae0ee3da2378b3c9d5be18c7df419fb2c/Capa_aula07.png)](https://www.youtube.com/watch?v=26GoufnXRPM)




:shipit: Código 1
```
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
