# Aula 10/06/2021 - Desenvolvimento de Aplicações WEB
## Atividades da aula - roteiro

## Implementação do Modelo Conceitual

### Passo 1: Criar repositório de Proprietarios (DAO)
- Criar interface ProprietárioDAO
- Vincular herança com JPA
- Vide Código 1

### Passo 2: Criar controlador de Proprietarios 
- Fazer anotações de @RestController, @RequestMapping
- Criar um "endpoint" de consulta (@GetMapping) para proprietário com URI: /gto/proprietarios
- Usar inicialmente ProprietárioDAO para acesso aos dados
- Preencher a tabela de proprietarios (usar o método RUN do CommandLineRunner em BckendGtoApplication) com três registros e fazer novamente a consulta
- Vide Código 2

### Passo 3: Criar uma consulta específica para o ID do proprietário
- Criar um outro "endpoint" de consulta com passagem de parâmentros pelo caminho (path) "/{id}"
- Usar inicialmente a classe Optional para retorno
- Mudar para ResponseEntity o retorno

####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula06](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/f12b022c5b71942326118f1ea9881b9ed3f1b471/Capa_aula05_mod1.png)](https://www.youtube.com/watch?v=VHhqVr3YLpM)
-
🥈:[![material complementar aula06](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/f12b022c5b71942326118f1ea9881b9ed3f1b471/Capa_aula05_mod1.png)](https://www.youtube.com/watch?v=R9_oUikVjAE)
-
🥉:[![material complementar aula06](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/f12b022c5b71942326118f1ea9881b9ed3f1b471/Capa_aula05_mod1.png)](https://www.youtube.com/watch?v=tbziAnjO-34)



:shipit: Código 1
```
public interface ProprietarioDAO extends JpaRepository<Proprietario, Integer> {

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


	
