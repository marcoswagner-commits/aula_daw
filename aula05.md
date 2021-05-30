# Aula 08/06/2021 - Desenvolvimento de Aplicações WEB
## Atividades da aula - roteiro

## Início da implementação do Modelo Conceitual

### Passo 1: criar projeto
#### Criar pasta Gestao_Obras_Projeto dentro do Workspace STS - Criar duas subpastas: backend (bckend_gto) e frontend (frtend_gto):

![Pastas do Projeto](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/fe5c2cc8a99cd618a16d1f43c0a6d133d321c865/pastas_projeto.png)


- Acessar página http://start.spring.io e criar projeto Spring Boot no `Spring Initializr` com as seguintes informações:
  - Name/Artifact: GestaoObra-Api 
  - Group/Package: net.ufjnet.gestaobra
  - Description: API de Gestão de Obras
  - Dependências (aba direita)
    - Web (JSON/TOMACAT/WEB/MVC)
    - Data (DATA_JPA/HIBERNATE/JAKARTA)


- Caso o arquivo pom.xml tenha algum erro::
  - Botão direito no projeto -> Maven -> Update project (force update)
  - Menu Project -> Clean
  
- Baixar arquivo gerado e descompactar dentro da pasta Gestao_Obras/BackEnd.

### Passo 2: criar estruturas de pastas no projeto seguindo o modelo de arquitetura abaixo:
![Modelo de Arquitetura](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/947bf8022b213bb7fe183c39dae8c607a6d60212/modelo_camadas.png)

