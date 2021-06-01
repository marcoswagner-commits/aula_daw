# Aula 08/06/2021 - Desenvolvimento de Aplicações WEB
## Atividades da aula - roteiro

## Início da implementação do Modelo Conceitual

### Passo 1: criar projeto
#### Criar uma estrutura de monorepositório para GitHub 
- Criar pasta Gestao_Obras_Projeto dentro do Workspace STS - Criar duas subpastas: backend (bckend_gto) e frontend (frtend_gto):

![Pastas do Projeto](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/fe5c2cc8a99cd618a16d1f43c0a6d133d321c865/pastas_projeto.png)


- Acessar página http://start.spring.io e criar projeto Spring Boot no `Spring Initializr` com as seguintes informações:
  - Name/Artifact: bckend_gto 
  - Group/Package: net.ufjnet.gestaobra
  - Description: API de Gestão de Obras
  - Dependências (aba direita)
    - Web (JSON/TOMACAT/WEB/MVC)
    - Data (DATA_JPA/HIBERNATE/JAKARTA)


- Caso o arquivo pom.xml tenha algum erro::
  - Botão direito no projeto -> Maven -> Update project (force update)
  - Menu Project -> Clean
  
- Baixar arquivo gerado e descompactar dentro da pasta Gestao_Obras_Projeto/bckend_gto.

### Passo 2: criar estruturas de pastas no projeto seguindo o modelo de arquitetura abaixo:
![Modelo de Arquitetura](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/947bf8022b213bb7fe183c39dae8c607a6d60212/modelo_camadas.png)

- Veja o vídeo abaixo que mostra a execução destes dois primeiros passos
[![material complementar aula05](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/f12b022c5b71942326118f1ea9881b9ed3f1b471/Capa_aula05_mod1.png)](https://youtu.be/S58DL42UaUw)

:shipit: Código 1
```
@Entity
@Table(name = "PROPRIETARIOS")
public class Proprietario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_prop")
	private Integer codigo ;
	
	@Column(name = "nome_prop", nullable = false)
	private String nome;
	
	@Column(name = "cpf_prop", nullable = false)
	private String cpf;
	
	@Column(name = "email_prop", nullable = false)
	private String email;
	
	....
```


### Passo 3: criar as estruturas necessárias para criar o banco de dados e inserir a dependêcia LOMBOK



:shipit: Configuração do MySQL no arquivo application.properties
```
# MYSQL
spring.datasource.url= jdbc:mysql://localhost:3306/gestao_obra?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=

#JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql= true
spring.jpa.open-in-view= true
```

- Veja o vídeo abaixo que mostra a execução do terceiro passo
[![material complementar aula05](https://raw.githubusercontent.com/marcoswagner-commits/gestao_obras_aula_daw/Documentos/Capa_aula05_mod2.png)](https://youtu.be/gDDe2jvv3fk)

### Passo 4: Atualizar o github com a criação de um branch "codigos"

