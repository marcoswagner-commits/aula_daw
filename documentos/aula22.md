# Aula 21 - Desenvolvimento de Aplicações WEB

> Aula 22/08/2021
> 
>   Estudo de caso: Gestão de Obras - Front-End


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Construção do Front-End - React.Js


### Passo 1: Definições e Ferramentas 
- [x] 


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
    image: 18011973/mysql-bckendgto
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
    image: 18011973/bckendgto
    restart: always
    build: ./bckendgto
    working_dir: /bckendgto
    environment:
      TZ: America/Sao_Paulo
      SPRING_BOOT_ENVIRONMENT: Production
    volumes:
      - ./bckendgto:/bckendgto
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
    ufjnet-network:
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
 


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/4f661048665df1d014740d1baf4eb93dfb66fbe0/documentos/Capa_aula21.png)](https://www.youtube.com/watch?v=O4Mm6Pt5hoI)
-
🥈:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/4f661048665df1d014740d1baf4eb93dfb66fbe0/documentos/Capa_aula21.png)](https://www.youtube.com/watch?v=uFD6ZPKdO0Y)

