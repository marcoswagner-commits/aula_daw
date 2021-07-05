# Aula 21 - Desenvolvimento de Aplicações WEB

> Aula 19/08/2021
> 
>   Estudo de caso: Gestão de Obras 


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Suporte ao Docker


### Passo 1: Upload de múltiplos arquivos
- [x] O que é o Docker
  - Docker é um container - Empacotamento de uma aplicação - Configure uma vez = "Na minha máquina funciona" - Escolha a nuvem
- [x] O que é o Docker Compose
  - Múltiplos containers (API - BD) - Configurar manualmente ou - Usar o Docker Compose (ferramenta)
- [x] Adicionando o suporte ao Docker
  - Fazer uma cópia da pasta do projeto (bckend_gto)
  - Juntamente com a pasta do projeto crie dois arquivos: Dockerfile (opcional - banco de dados) e docker-compose.yml
  - Dentro da pasta do projeto crie um arquivo: Dockerfile (aplicação)
- [x] Executando a aplicação no DOCKER
  - Juntamente com a pasta do projeto crie dois arquivos: Dockerfile (para o banco de dados) e docker-compose.yml
  - Dentro da pasta do projeto crie também um arquivo com o mesmo nome: Dockerfile (para a aplicação) - vide conteúdos
- [x] Instalar o Docker Desktop (Command Line)
  - Comandos: na pasta do arquivo docker-compose.yml - docker-compose build
- [x] Criando uma conta Docker Hub
  - Analisando a aplicação
  - Testando no Postman
- [x] O que é o Docker Hub
- [x] Enviando imagens para o DockerHub
  
### Conceito:
#### Docker server e Docker Daemon

A ideia por trás do Docker, como citado acima, é o fornecimento através da nuvem de containers. O Docker possui uma arquitetura de cliente-servidor, e tais containers são armazenados em um servidor, chamado de Docker host ou Docker server. O servidor do Docker é responsável por todas as ações relacionadas aos containers. O Docker daemon recebe comandos do cliente a partir de Command Line Interfaces ou API’s REST. O Docker host pode ser local ou remoto, e tem a capacidade de criar, iniciar, desligar e excluir vários containers, e pode oferecer para cada cliente um ou mais containers.


#### Imagens

Imagens são arquivos que contém todo o conteúdo e estrutura de sistemas operacionais. Elas são a base de construção de containers no Docker. O servidor do Docker utiliza o AuFS, um file system em camadas que permite a separação do espaço do sistema em uma parte read-only e outra parte read/write. Dessa forma, clientes podem construir templates em cima da parte de escrevível, e estes templates terão todas as configurações desejadas do container a ser construído. Ao executar um container de um cliente, o Docker utiliza a imagem de sistema operacional escolhida como base, e numa camada superior executa as configurações escolhidas pelo cliente.


#### Registros do Docker

O Registro do Docker é uma espécie de repositório para imagens. Com esse registro, um usuário pode construir, salvar e distribuir imagens com outros. O site do Docker fornece um sistema de registro chamado Docker Hub, que funciona como um git, permitindo ao usuário que construa localmente suas imagens em sua máquina, e então realize operações de commit e push.


#### Containers

Containers são os ambientes de execução do Docker, criados a partir de imagens. De forma simplificada, é uma sandbox para processos. Uma imagem é como um template de classe, e containers seriam instâncias dessas classes(instanciados na uma camada escrevível da imagem).

![components_docker](https://user-images.githubusercontent.com/81576640/124472172-2ca6b600-dd74-11eb-8c55-9ddb694ea346.png)

[Um artigo sobre Docker](https://www.gta.ufrj.br/ensino/eel879/trabalhos_v1_2017_2/docker/containers.html)

🅰️
### Código do docker-compose.yml
```
version: '3.8'
services:
  db:
    image: 18011973/mysql-bckend-gto
    command: mysqld --default-authentication-plugin=mysql_native_password
    restart: always
    build:
      context: .
      dockerfile: Dockerfile
    environment:
      TZ: America/Sao_Paulo
      MYSQL_ROOT_PASSWORD: docker
      MYSQL_USER: docker
      MYSQL_PASSWORD: docker
      MYSQL_DATABASE: gestao_obra
    ports:
      - "3308:3306"
    networks:
      - ufjnet-network
  bckend-gto:
    image: 18011973/bckend_gto
    restart: always
    build: ./bckend-gto
    working_dir: /bckend_gto
    environment:
      TZ: America/Sao_Paulo
      SPRING_BOOT_ENVIRONMENT: Production
    volumes:
      - ./bckend-gto:/bckend_gto
      - ~/.m2:/root/.m2
    ports:
      - "8080:8080"
    command: mvn clean spring-boot:run
    links:
      - db
    depends_on:
      - db
    networks:
      - ufjnet-network
networks:
    udemy-network:
        driver: bridge

```

🅰️
### Código do Dockerfile (externo - banco de dados)
```
FROM mysql:8.0.11
EXPOSE 3308
```

🅰️
### Código do Dockerfile (interno - aplicação)
```
FROM maven:3.8.1-jdk-11

```
 


🅰️ - Configurações finais do application.properties
```
#configurações de e-mail
spring.mail.default-encoding= UTF-8
spring.mail.host= smtp.gmail.com
spring.mail.port= 465
spring.mail.username=  
spring.mail.password= 
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.socketFactory.port= 465
spring.mail.properties.mail.smtp.socketFactory.clas= javax.net.ssl.SSLSocketFactory
spring.mail.properties.mail.smtp.socketFactory.fallback= false
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.ssl.enable=true

```

- [ ] [códigos finais](#códigos)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/4f661048665df1d014740d1baf4eb93dfb66fbe0/documentos/Capa_aula21.png)](https://www.youtube.com/watch?v=i_riVI00bog)
-
🥈:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/4f661048665df1d014740d1baf4eb93dfb66fbe0/documentos/Capa_aula21.png)](https://www.youtube.com/watch?v=JtezFJapvx8)
-
🥉:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/4f661048665df1d014740d1baf4eb93dfb66fbe0/documentos/Capa_aula21.png)](https://www.youtube.com/watch?v=MOn_yvN6D0o)




### Códigos
- FileController
```


```

-  classe FileStorageService
```



```



### Passo 2: Atualizar o github com os códigos atuais (Docker)

