# Aula 12 - Desenvolvimento de Aplicações WEB

> Aula 01/07/2021
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
- [x] Customizar as informações da classe OpenApiConfig - Vide Código 1
- [x] Colocar a anotação @Tag para a classe Controlador
- [x] Colocar as anotações @Operation(summary = "Busca todos os proprietários")
- [x] Atualizar a classe  controladores
    - [x] Colocar suporte ao Hateoas (link de autorelacionamento) e sofisticação final - Vide Códigos
  - [x] Verificar o consumo da API com as alterações


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
@Operation(summary = "Busca um proprietário por nome")
@Operation(summary = "Busca um proprietário por id")
@Operation(summary = "Busca um proprietário por cpf")
@Operation(summary = "Busca um proprietário por email")
@Operation(summary = "Insere um novo proprietário")
@Operation(summary = "Atualiza um proprietário por id")
@Operation(summary = "Exclui um proprietário por id")

```


### Passo 2: Versionamento e Serialização
  
- [x] Incluir versão no Path (v1)
- [x] Serialização
- [x] Organização do JSON na classe DTO (JsonPropertyOrder())
- [x] Definição de nomes de campos com o JsonProperty()
- [x] Ocultação de campos com JsonIgnore


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula11](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=LyZ5HdkEwqs)
-
🥈:[![material complementar aula11](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=tC60mjNSJ_w)
-
🥉:[![material complementar aula11](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/de83dfe17ef227404bf91b9dae5666f2ca8ae59a/Capa_aula10.png)](https://www.youtube.com/watch?v=PLzpVCyJyZI)





:shipit: Código 1 - ProprietarioController (busca)
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

:shipit: Código 2 - ProprietarioController (buscas específicas)
```
@GetMapping("/{id}")
public ResponseEntity<ProprietarioDTO> buscarUm(@PathVariable Integer id) {
		ProprietarioDTO objDTO = service.findById(id);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarUm(id)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	

@GetMapping("/{nome}")
public ResponseEntity<ProprietarioDTO> buscarNome(@PathVariable String nome) {
		ProprietarioDTO objDTO = service.findByNome(nome);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarNome(nome)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	

@GetMapping("/{cpf}")
public ResponseEntity<ProprietarioDTO> buscarCpf(@PathVariable String cpf) {
		ProprietarioDTO objDTO = service.findByCPF(cpf);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarCpf(cpf)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	

@GetMapping("/{email}")
public ResponseEntity<ProprietarioDTO> buscarEmail(@PathVariable String email) {
		ProprietarioDTO objDTO = service.findByEmail(email);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarEmail(email)).withSelfRel());
		return ResponseEntity.ok(objDTO);
}		
  
```

:shipit: Código 3 - ProprietarioController (inclusão, atualização e exclusão)
```
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public ResponseEntity<ProprietarioDTO> incluir(@RequestBody Proprietario objBody) {
	ProprietarioDTO objDTO = service.save(objBody);
	objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
	return ResponseEntity.ok(objDTO);
}

@PutMapping
public ResponseEntity<ProprietarioDTO> atualizar(@PathVariable Integer id, @RequestBody Proprietario objBody ) {
	if (!service.existById(id)) {
		return ResponseEntity.notFound().build();
	}
	objBody.setCodigo(id);
	ProprietarioDTO objDTO = service.save(objBody);
	objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
	return ResponseEntity.ok(objDTO);
}	

@DeleteMapping("/{id}")
public ResponseEntity<Void> excluir(@PathVariable Integer id) {
	if (!service.existById(id)) {
		return ResponseEntity.notFound().build();
	}

	service.deleteById(id);

	return ResponseEntity.noContent().build();

}

```
:shipit: Código 4 - GestaoProprietario
```

	@Transactional(readOnly = true)
	public ProprietarioDTO findById(Integer id) {
		Proprietario result = dao.findById(id).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new ProprietarioDTO(result);
			
	}
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findByNome(String nome) {
		Proprietario result = dao.findByNome(nome).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new ProprietarioDTO(result);
		
    	}
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findByCPF(String cpf) {
		Proprietario result = dao.findByCpf(cpf).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new ProprietarioDTO(result);
		
    }
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findByEmail(String email) {
		Proprietario result = dao.findByEmail(email).
				orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new ProprietarioDTO(result);
		
	}
```

### Passo 3: Atualizar o github com os códigos atuais (hateoas)
