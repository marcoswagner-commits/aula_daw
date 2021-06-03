# Aula 10/06/2021 - Desenvolvimento de Aplicações WEB
## Atividades da aula - roteiro

## Implementação do Modelo Conceitual

### Passo 1: Criar repositório de Proprietarios (DAO)
- Criar interface ProprietárioDAO
- Vincular herança com JPA
- Vide Código 1

### Passo 2: Criar controlador de Proprietarios 
- Fazer anotações de @RestController, @RequestMapping
- Criar um "endpoint" de consulta para proprietário com URI: /gto/proprietarios
- Usar inicialmente ProprietárioDAO para acesso aos dados
- Preencher a tabela de proprietarios (usar o método RUN do CommandLineRunner em BckendGtoApplication) com três registros e fazer novamente a consulta
- Vide Código 2


- Veja o vídeo abaixo que mostra a execução destes dois primeiros passos
[![material complementar aula06](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/f12b022c5b71942326118f1ea9881b9ed3f1b471/Capa_aula05_mod1.png)](https://youtu.be/S58DL42UaUw)

:shipit: Código 1
```

```

:shipit: Código 2
```

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

### Passo 4: Atualizar o github com os primeiros códigos

- Obs.: 
	- Instalar o LOMBOK: Acessar a pasta na sua pasta de usuários as seguintes pastas em sequência: .m2 = repository = org = projectlombok = lombok 
	- abrir o arquivo "lombok-1.??.??.jar" - terminal: java -jar "lombok-1.??.??.jar"
	- caso não detecte automaticamente a IDE (STS4) fazer a localização manual
	- fechar e abrir novamente a IDE
	- testar uma classe verificando se os métodos getters e setters estão presentes na instância do objeto
	
