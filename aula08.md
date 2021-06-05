# Aula 08 - Desenvolvimento de Aplicações WEB
## Atividades da aula - roteiro

## Implementação do Modelo Conceitual

### Passo 1: Criar funcionalidades (starters e classes) para tratamento de erros
- Adicionar o pacote Spring Validation
- Fazer anotações na classe Proprietario (Model) - @NotBlank - @Size - @Email
- Criar o pacote exceptionhandler
- Criar a classe ExceptionHandler
- Vide Códigos 1 e 2

### Passo 2: Melhorar o tratamento de mensagens
- Sobrescrever o método handleMethodArgumentNotValid
- Criar uma classe StandardError
- Vincular a classe StandardError ao método
- Verificar os retornos de erros
- Atualizar mensagens criando o arquivo messages.properties na pasta "Resources"
- Vide Códigos 3 e 4


####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ba294e3ae0ee3da2378b3c9d5be18c7df419fb2c/Capa_aula07.png)](https://www.youtube.com/watch?v=kwyntQNaGn0)
-
🥈:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ba294e3ae0ee3da2378b3c9d5be18c7df419fb2c/Capa_aula07.png)](https://www.youtube.com/watch?v=qThJM6UPSqE)
-
🥉:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ba294e3ae0ee3da2378b3c9d5be18c7df419fb2c/Capa_aula07.png)](https://www.youtube.com/watch?v=c3BEXOIWSEQ)
-
🥉:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ba294e3ae0ee3da2378b3c9d5be18c7df419fb2c/Capa_aula07.png)](https://www.youtube.com/watch?v=26GoufnXRPM)




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


```
### Passo 3: Atualizar o github com os códigos atuais
