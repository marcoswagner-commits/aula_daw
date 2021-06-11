# Aula 12 - Desenvolvimento de Aplicações WEB

> 
> 
>  *Estudo de caso: Gestão de Obras * - Swagger


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras

### 📖 O que é o SWAGGER: 
- Swagger é uma especificação de descrição de interface para descrever APIs RESTful expressas usando JSON. 
- O Swagger é usado junto com um conjunto de ferramentas de software de código aberto para projetar, construir, documentar e usar serviços da Web RESTful.

### Passo 1: Analisar e adequar a arquitetura REST
- [x] Instalar a dependência
- [x] Criar a classe OpenApiConfig dentro de um pacote "config"
- [x] Customizar as informações da classe OpenApiConfig - [Vide Código 1](#código-1---openapiconfig)
- [x] Colocar a anotação @Tag para a classe Controlador
- [x] Colocar as anotações @Operation(summary = "Busca todos os proprietários")
- [x] Testar a API - com http://localhost:8080/swagger-ui.html

✏️ Dependência necessária para uso do SWAGGER
```
<!-- Spring Doc Open API -->
<dependency>
	<groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-ui</artifactId>
	<version>1.4.6</version>
</dependency>

```

✏️ Anotações necessários para descrição dos endpoints e controladores
```
@Tag(name = "Aluno Endpoint") 

@Operation(summary = "Busca todos os proprietários")
@Operation(summary = "Busca um proprietário por id")
@Operation(summary = "Busca um proprietário por nome")
@Operation(summary = "Busca um proprietário por cpf")
@Operation(summary = "Busca um proprietário por email")
@Operation(summary = "Insere um novo proprietário")
@Operation(summary = "Atualiza um proprietário por id")
@Operation(summary = "Exclui um proprietário por id")
```

### Passo 2: Importando uma collection no PostMan via Swagger-api-docs
  
- [x] Clicar no link abaixo do título da aplicação
- [x] Copiar endereço (http://localhost:8080/v3-api-docs)
- [x] Criar ambiente no PostMan
- [x] Realizar os nos dois "clients" - Navegador Swagger / PostMan
- [x] Commit "swagger"

## Passo 3: Instalando e configurando o Security
  
- [x] Instalar a dependência - Vide detalhes abaixo
- [x] Customizar as informações da classe SecurityConfig dentro do pacote Config - [Vide Código 2](#código-2---securityconfig)
- [x] Criar o arquivo "application-dev.properties"
- [x] Fazer vínculo do "application.properties" para "application-dev.properties" - Vide detalhes abaixo
- [x] Commit "security"

✏️ Dependência necessária para uso do SpringSecurity
```
<!-- for Security support -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>

```

✏️ Arquivos application.properties e application-dev.properties
```
#application.properties
spring.jpa.open-in-view= true
spring.profiles.active=dev



#application-dev.properties
# MYSQL
spring.datasource.url= jdbc:mysql://localhost:3306/gestao_obra?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=

#JPA
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql= true
```
## Passo 4: Preparando a aplicação para o Deploy no Heroku
  
- [x] Criar o arquivo "application-prod.properties"
- [x] Alterar o arquivo "application.properties" para permitir a adequação de acordo o local da aplicação (local ou remoto)
- [x] Criar o arquivo system.properties na raiz do projeto
- [x] Dar um commit de homologação "homolog"
- [x] Criar app no Heroku (gestao_obras_daw_api) - Obs.: Criar conta e baixar CLI do Heroku
- [x] Provisionar o MySQL no Heroku - Opção Resources - ClearDB MySql
- [x] Definir variável APP_PROFILE=prod - Opção Settings - Config Var
- [ ] Executar comandos Heroku CLI - vide linhas abaixo

```
heroku login
heroku git:remote -a <nome-do-app>
git remote -v
git subtree push --prefix backend heroku main
```


✏️ Arquivos application.properties e application-prod.properties
```
#application.properties
spring.profiles.active=${APP_PROFILE:dev}
spring.jpa.open-in-view=false


#application-prod.properties
spring.datasource.url=${DATABASE_URL}
```

✏️ Arquivo system.properties
```
java.runtime.version=11




[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula12](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=EUlpkjfK4yo)
-
🥈:[![material complementar aula12](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=rh_-R9-H_2M)
-
🥉:[![material complementar aula12](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=PLzpVCyJyZI)


:shipit: 
### Código 1 - OpenApiConfig
```
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info()
				.title("API RESTful Gestão de Obras construída com Spring Boot 2.5.0")
				.version("V1")
				.description("Sistema de Gerenciamento de Obras de Construção Civil")
				.termsOfService("http://swagger.io/terms")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));
			
	}

	
}

```
[voltar](#passo-1-analisar-e-adequar-a-arquitetura-rest)


:shipit: 
### Código 2 - SecurityConfig
```
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().anyRequest().permitAll();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
```
[voltar](#passo-3-instalando-e-configurando-o-security)


### Passo 3: Atualizar o github com os códigos atuais (swagger-open-api)
