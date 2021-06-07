# Aula 09 - Desenvolvimento de Aplicações WEB

> Aula 22/06/2021
> 
>  *Estudo de caso: Gestão de Obras*


## Atividades da aula - roteiro

## Implementação do Modelo Conceitual

### Passo 1: Ampliar as regras de negócio relacionadas a classe (entidade) proprietários
- Incluir no repositório ProprietarioDAO outros tipos de acesso a dados
  - Busca de registros por nome
  - Busca de registros por cpf
  - Busca de registros por e-mail
- Ajustar os serviços GestaoProprietario para não permitir inclusão de dados incoerentes (dataIntegrity)
  - Não permissão de cadastro de cpfs iguais
  - Não permissão de cadastro de e-mails iguais
- Vide Códigos 1 e 2  (Repositório e Serviço)

### Passo 2: Ampliar e melhorar o tratamento de mensagens de erros
- Criar uma classe em seviços/exceptions para regras de negócio - BusinessException
- Apliar a classe de ExceptionHandler
- Criar um método dataIntegrity (Integridade de dados) para não permitir repetição de cpfs e e-mails
- Verificar os retornos de erros
- Implementar os endpoints de busca por nome e por cpf
- Vide Códigos 3, 4 e 5 (BusinessException, ExceptionHandler e Controller)


####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula09](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ecbe27da469ec69308caf228b1f5a2d0e4b863a3/Capa_aula08.png)](https://www.youtube.com/watch?v=CncYHx2x-xI)
-
🥈:[![material complementar aula09]https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ecbe27da469ec69308caf228b1f5a2d0e4b863a3/Capa_aula08.png)](https://www.youtube.com/watch?v=qJm_-rD4gb0)
-
🥉:[![material complementar aula09](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ecbe27da469ec69308caf228b1f5a2d0e4b863a3/Capa_aula08.png)](https://www.youtube.com/watch?v=XWIO-29pOJY)





:shipit: Código 1
```
public interface ProprietarioDAO extends JpaRepository<Proprietario, Integer> {
	
	Optional<Proprietario> findByNome (String nome);
	Optional<Proprietario> findByCpf(String cpf);
	Optional<Proprietario> findByEmail(String email);

}

```

:shipit: Código 2
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
	
	public Optional<Proprietario> findByName(String nome) {
		return dao.findByNome(nome);
    }
	
	public Optional<Proprietario> findByCPF(String cpf) {
		return dao.findByCpf(cpf);
    }
	
	public Optional<Proprietario> findByEmail(String email) {
		return dao.findByEmail(email);
}
	
	@Transactional
	public Proprietario save(Proprietario obj) {
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

:shipit: Código 3
```
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public BusinessException(String msg) {
		super(msg);
	}

}


```

:shipit: Código 4
```
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource msg;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<StandardError.Fields> erro_campos = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = msg.getMessage(error, LocaleContextHolder.getLocale());
			
			erro_campos.add(new StandardError.Fields(nome, mensagem));
		}
		
		
		StandardError erro = new StandardError(status.value(),LocalDateTime.now(),"Verifique o preenchimento dos campos!",erro_campos);
		
		return handleExceptionInternal(ex, erro, headers, status, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
	public ResponseEntity<StandardError> dataIntegrity (BusinessException ex) {
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(),
			LocalDateTime.now(),ex.getMessage(),null);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
		
	

}

```

:shipit: Código 5
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
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Proprietario> buscarNome(@PathVariable String nome) {
		return service.findByName(nome)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Proprietario> buscarCpf(@PathVariable String cpf) {
		return service.findByCPF(cpf)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	

```
### Passo 3: Atualizar o github com os códigos atuais
