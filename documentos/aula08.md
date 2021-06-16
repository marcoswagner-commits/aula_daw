# Aula 08 - Desenvolvimento de Aplicações WEB

> Aula 17/06/2021
> 
>  *Estudo de caso: Gestão de Obras*

## Atividades da aula - roteiro

## Implementação do Modelo Conceitual

### Passo 1: Criar funcionalidades (starters e classes) para tratamento de erros
- [x]  Adicionar o pacote Spring Validation
- [x]  Fazer anotações na classe Proprietario (Model) - @NotBlank - @Size - @Email
- [x]  Criar o pacote exceptionhandler
- [x]  Criar a classe ExceptionHandler (@ControllerAdvice)
- [x]  Vide Códigos 1 e 2

### Passo 2: Melhorar o tratamento de mensagens
- [x]  Sobrescrever o método handleMethodArgumentNotValid
- [x]  Criar uma classe StandardError
- [x]  Vincular a classe StandardError ao método
- [x]  Verificar os retornos de erros
- [x]  Atualizar mensagens criando o arquivo messages.properties na pasta "Resources"
- [x]  Vide Códigos 3 e 4

[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/b29579d6b8583f49c9b1a044288abcb788643119/documentos/Capa_aula08.png)](https://www.youtube.com/watch?v=kwyntQNaGn0)
-
🥈:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/b29579d6b8583f49c9b1a044288abcb788643119/documentos/Capa_aula08.png)](https://www.youtube.com/watch?v=qThJM6UPSqE)
-
🥉:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/b29579d6b8583f49c9b1a044288abcb788643119/documentos/Capa_aula08.png)](https://www.youtube.com/watch?v=qGMNf2p6zrk)
-
🥉:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/b29579d6b8583f49c9b1a044288abcb788643119/documentos/Capa_aula08.png)](https://www.youtube.com/watch?v=4qkG2kMqmhc)




:shipit: Código 1
```
@NoArgsConstructor
@AllArgsConstructor
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
	
	@NotBlank
	@Size(max=60)
	@Column(name = "nome_prop", nullable = false)
	private String nome;
	
	@NotBlank
	@Size(max=14)
	@Column(name = "cpf_prop", nullable = false)
	private String cpf;
	
	@Email
	@Column(name = "email_prop", nullable = false)
	private String email;
	
}

```

:shipit: Código 2
```
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, "Erro em Gestão de Obras", headers, status, request);
	}
}
```

:shipit: Código 3
```
@AllArgsConstructor
@Getter
@Setter
public class StandardError {
	
	private Integer codigo;
	private LocalDateTime momento;
	private String decricao;
	private List<Fields> campos;


	@AllArgsConstructor
	@Setter
	@Getter
	public static class Fields {
	private String nome;
	private String mensagem;
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
	
	

}


```
### Passo 3: Atualizar o github com os códigos atuais
