# Aula 06 - Desenvolvimento de Aplicações WEB

> Aula 30/08/2022
> 
>  *Estudo de caso: Gestão de Obras* 
>  
>  *Criando as primeiras classes (repositórios e controladores): Proprietário* 



## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual - Criação do Repositório e do Controlador

### Passo 1: Criar repositório de Proprietarios (DAO)
- [x] Criar interface ProprietárioDAO
- [x] Vincular herança com JPA
- [x] Vide Código 1

### Passo 2: Criar controlador de Proprietarios 
- [x] Fazer anotações de @RestController, @RequestMapping
- [x] Criar um "endpoint" de consulta (@GetMapping) para proprietário com URI: /gto/proprietarios
- [x] Usar inicialmente ProprietárioDAO para acesso aos dados
- [x] Preencher a tabela de proprietarios (usar o método RUN do CommandLineRunner em BckendGtoApplication) com três registros e fazer novamente a consulta
- [x] Vide Código 2

### Passo 3: Criar uma consulta específica para o ID do proprietário
- [x] Criar um outro "endpoint" de consulta com passagem de parâmentros pelo caminho (path) "/{id}"
- [x] Usar inicialmente a classe Optional para retorno
- [x] Mudar para ResponseEntity o retorno


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula06](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/d7dfaab16fc7aa70a05cac9ff55b3cd58ce0d385/documentos/Capa_aula06.png)](https://www.youtube.com/watch?v=VHhqVr3YLpM)
-
🥈:[![material complementar aula06](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/d7dfaab16fc7aa70a05cac9ff55b3cd58ce0d385/documentos/Capa_aula06.png)](https://www.youtube.com/watch?v=R9_oUikVjAE)
-
🥉:[![material complementar aula06](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/d7dfaab16fc7aa70a05cac9ff55b3cd58ce0d385/documentos/Capa_aula06.png)](https://www.youtube.com/watch?v=tbziAnjO-34)



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


	
