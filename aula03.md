# Aula 25/05/2021 - Desenvolvimento de Aplicações WEB
## Atividades da aula - roteiro

- Acessar o site Spring.io :+1: (https://spring.io) e demonstrar a geração da estrutura de códigos usando a página: https://start.spring.io
- Conceituar MVC ([Conteúdo do Curso](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/main/Conteúdo_Aula_DSW_Módulo_I.pdf))
- Explorar um pouco mais o arquivo POM (Project Object Model) - NÃO é um arquivo SPRING e sim um arquivo MAVEN...
- - Verificar "parents"
- - Hierarquias transitivas 
- - Analisar as dependências MAVEN
- - Analisar a estrutura de pastas do projeto

### Geração de um JAR 
- [x] Servelet Container Tomcat
- [x] Fat Jar
- [x] Sequência de acesso a criação do JAR (clique do botão direito no projeto - run as - maven build - goals: clean package)
- [x] Explorar a pasta target 
- [x] "Rodar" a aplicação de fora da IDE


:shipit:
```
@SpringBootApplication
@RestController
public class TesteAula1Application {

	public static void main(String[] args) {
		SpringApplication.run(TesteAula1Application.class, args);
	}
	
	@GetMapping("/auladaw")
	public String auladaw() {
		return "Olá turma de DAW!;
	}
}
```
### Alterar os primeiros códigos, :+1::
:shipit:
```
@SpringBootApplication
@RestController
public class TesteAula1Application {

	public static void main(String[] args) {
		SpringApplication.run(TesteAula1Application.class, args);
	}
	
	@GetMapping("/auladaw")
	public String auladaw(@RequestParam(value = "nome", defaultValue = "NOME") String nome) {
		return String.format("Olá turma de DAW, meu nome é %s",nome);
	}
}
```

### Conceituar API, REST, HTTP, 
