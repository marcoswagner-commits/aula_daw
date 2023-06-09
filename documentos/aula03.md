# Aula 03 - Desenvolvimento de Aplicações WEB

> Aula 20/06/2023
> 
>  *Estudo de caso: Gestão de Obras*
>  
>  *MVC – Estrutura de Arquivos Spring – Build Maven – Controladores – EndPoints – JSON e ORM*


## Atividades da aula - roteiro

- Acessar o site Spring.io :+1: (https://spring.io) e demonstrar a geração da estrutura de códigos usando a página: https://start.spring.io
- Conceituar MVC [Conteúdo do Curso](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/Conteúdo_Aula_DSW_Módulo_I.pdf)
- Gerar o projeto apenas com o "starter" Spring WEB
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
- [x] Derrubar processos nas portas específicas (lsof -t -i :8080 /// kill -9 ????)


### Modificação do código (controle) - código 1
- [x] Criar um pacote Controller
- [x] Criar uma classe AlunoController
- [x] Criar um EndPoint com uma mensagem inicial
- [x] Testar a aplicação


### Modificação do código (modelo) - código 2
- [X] Criar um pacote model
- [x] Criar uma classe Aluno (código, nome e e-mail)
- [x] Adicionar a dependência Lombok
- [x] Ver o resultado apenas do "objeto" Aluno
- [x] Usar o Postman para ver o resultado no formato JSON
- [x] Analisar os códigos de resposta HTTP 
	- [ ] [Códigos Http](https://iana.org/assignments/http-status-codes/http-status-codes.xhtml)
	- [ ] [Códigos Http](https://httpstatuses.com)
	- [ ] [Códigos Http](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status)
- [x] Conceituar endpoint e JSON [Conteúdo do Curso](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/Conteúdo_Aula_DSW_Módulo_I.pdf)


### Modificação do código (repositório) - código 3
- [x] Gerar a entidade no banco de dados
- [x] Conceituar ORM/JAKARTA - [Conteúdo do Curso](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/Conteúdo_Aula_DSW_Módulo_I.pdf)
- [x] Acessar site:[JAKARTA Persistence] (https://jakarta.ee/specifications/persistence/3.0/)
- [x] Configurar o banco de dados em memória H2 (adicionar a dependência H2)
- [x] Acessar a tabela criada 


- [X] Criar um pacote repository
- [x] Criar uma interface AlunoRepository
- [x] Conceituar Injeção de Dependências/Inversão de Controle - [Conteúdo do Curso](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/Conteúdo_Aula_DSW_Módulo_I.pdf)


### Modificação do código (cadastrar registros) - código 4
- [X] Implementar na classe principal uma interface (CommandLineRunner)
- [x] Injetar AlunoRepository
- [x] Criar um objeto/registro de aluno


### Modificação do código (retornar alunos) - código final
- [X] Alterar a classe AlunoController
- [x] Criar um método findAll
- [x] Retomar conceito de JSON [Conteúdo do Curso](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/Conteúdo_Aula_DSW_Módulo_I.pdf)


### Uso do repositório Github
- [X] Criar um repositório novo "AulaTesteDAW"
- [x] Owner " " / Repository name " "
- [x] Description " " - Public/Private - Add README file - 
- [x] Inicializar um repositório na própria máquina (computador)

#### Para acesso específico a este conjunto de códigos, acesse o repositório: https://github.com/marcoswagner-commits/testeAula1

```
echo "# AulaTeste" >> README.md
git init
git add README.md
git commit -m "primeira versão"
git branch -M main
git remote add origin https://github.com/marcoswagner-commits/AulaTeste.git
git push -u origin main
```
ou
```
git remote add origin https://github.com/marcoswagner-commits/AulaTeste.git
git branch -M main
git push -u origin main
```

:shipit: Código 1
```
@RequestMapping("/auladaw)
@RestController
public class AlunoController {

	
	@GetMapping
	public String auladaw() {
		return "Olá turma de DAW!";
	}
	
	@GetMapping("/{nome}")
	public String auladaw(@PathVariable(value = "nome") String nome) {
		return String.format("Olá turma de DAW, meu nome é %s",nome);
	}
}
```

- Verificar se o H2 está nas dependências MAVEN, caso afirmativo é necessário configurar o H2 adicionando as seguintes propriedades no arquivo de configurações application.properties.

:shipit: - Configurações no arquivo application.properties
```
# DATASOURCE
spring.datasource.url=jdbc:h2:file:./data/aula1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=aula1
spring.datasource.password=aula1

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

:shipit: Código 2
```
@Entity
@Table(name = "ALUNOS")
public class Aluno {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer codigo;

    @Column(name = "NOME_ALUNO", length = 50, nullable = false)
    private String nome;
    
    @Column(name = "EMAIL_ALUNO", length = 40, nullable = false)
    private String email;
	
	....
```

:shipit: Código 3
```
public interface AlunoDAO extends JpaRepository<Aluno, Integer> {
}
```

:shipit: Código 4
```
public class TesteAula1Application implements CommandLineRunner {

	@AutoWired
	AlunoDAO alunoDAO;
	
	...
	
	@Override
	public void run(String... args) throws Exception {
		Aluno aluno = new Aluno(1,"Arthur","arthur@email.com");
		alunoDAO.save(aluno);
		
	}
	
}
```

:shipit: Código Final
```
@RestController
@RequestMapping("/auladaw")
public class AlunoController {
	
	@Autowired
	AlunoDAO dao;
	
	@GetMapping
	public List<Aluno> auladaw() {
		return dao.findAll();
	}
	
	@GetMapping("/{nome}")
	public String auladaw(@PathVariable(value = "nome") String nome) {
		return String.format("Olá turma de DAW, meu nome é %s",nome);
	}
	

}
```
