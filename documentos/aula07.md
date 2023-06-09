# Aula 07 - Desenvolvimento de Aplicações WEB

> Aula 04/07/2023
> 
>  *Estudo de caso: Gestão de Obras*
>  
>  *Estudo de Caso – Gestão de Obras – Criando as primeiras classes de serviço e implementando o CRUD  de proprietários*


## Atividades da aula - roteiro

## Implementação do Modelo Conceitual

### Passo 1: Criar as classes de serviços (services)
- [x] Criar os principais métodos de acesso aos dados (busca, inclusão, atualização e exclusão)
- [x] Vincular o repositório com injeção por meio de construtor
- [x] Fazer as anotações @Service e @Transactional
- [x] Vide Código 1

### Passo 2: Criar o CRUD para proprietários
- [x] Criar uma "Collection" para Gestão de Obras no PostMan
  - Criar um "Request" para Consultas
- [x] Criar um endpoint para Salvar
  - Criar um "Request" para Inclusões
- [x] Criar um endpoint para Atualizar
  - Criar um "Request" para Atualizações
- [x] Criar um endpoint para Excluir
  - Criar um "Request" para Exclusões
- [x] Vide Códigos - [código 1 - Service](#código-1) - [Código 2 - Controller](#código-2)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula07](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/83d2901aa0818fadc979900d8959a2ce69435be0/documentos/Capa_aula07.png)](https://www.youtube.com/watch?v=zoL877ckzeU)
-
🥈:[![material complementar aula07](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/83d2901aa0818fadc979900d8959a2ce69435be0/documentos/Capa_aula07.png)](https://www.youtube.com/watch?v=i6brsofWuew)
-
🥉:[![material complementar aula07](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/83d2901aa0818fadc979900d8959a2ce69435be0/documentos/Capa_aula07.png)](https://www.youtube.com/watch?v=c3BEXOIWSEQ)
-
🥉:[![material complementar aula07](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/83d2901aa0818fadc979900d8959a2ce69435be0/documentos/Capa_aula07.png)](https://www.youtube.com/watch?v=26GoufnXRPM)




:shipit: 
### Código 1
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
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
	
	
	
}

```
[voltar](#passo-2-criar-o-crud-para-proprietários)

:shipit: 
### Código 2
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
[voltar](#passo-2-criar-o-crud-para-proprietários)

### Passo 3: Atualizar o github com os códigos atuais


	
