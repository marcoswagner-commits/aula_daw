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
- [x] Customizar as informações da classe OpenApiConfig - [Vide Código 1](### Código 1 - OpenApiConfig)
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
- [x] Organização do JSON na classe DTO (JsonPropertyOrder())
- [x] Definição de nomes de campos com o JsonProperty()
- [x] Ocultação de campos com JsonIgnore


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula12](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=EUlpkjfK4yo)
-
🥈:[![material complementar aula12](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=tC60mjNSJ_w)
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



### Passo 3: Atualizar o github com os códigos atuais (swagger-open-api)
