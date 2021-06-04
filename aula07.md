# Aula 07 - Desenvolvimento de Aplicações WEB
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

🥇:[![material complementar aula06](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/200468edba69a88b063abe444459fa9e09a8d41e/Capa_aula06.png)](https://www.youtube.com/watch?v=zoL877ckzeU)
-
🥈:[![material complementar aula06](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/200468edba69a88b063abe444459fa9e09a8d41e/Capa_aula06.png)](https://www.youtube.com/watch?v=R9_oUikVjAE)
-
🥉:[![material complementar aula06](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/200468edba69a88b063abe444459fa9e09a8d41e/Capa_aula06.png)](https://www.youtube.com/watch?v=tbziAnjO-34)



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
```

### Passo 4: Atualizar o github com os códigos atuais


	
