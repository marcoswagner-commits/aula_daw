# Aula 16 - Desenvolvimento de Aplicações WEB

> Aula 05/08/2021
> 
>  * Estudo de caso: Gestão de Obras *


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Testes e Povoamento de Dados; Autenticação com JWT e Spring Security


### Passo 1: Testes, povoamento e buscas agrupadas
- [x] Povoar a tabela de itens
  - Inserir dados - criar collection no PostMan
- [x] Povoar a tabela de subitens
  - Inserir dados - criar collection no PostMan 
- [x] Povoar a tabela de lançamentos
  - Inserir dados - criar collection no PostMan 
- [x] Criar a classe TotalItemDTO (para armazenar o total de gastos com o item)
  - Criar uma busca agrupada no LancamentoDAO
  - Inserir método no GestaoLancamento
  - Inserir método no LancamentoController
- [x] Códigos atualizados (métodos inseridos) - [códigos atualizados](#código-atualizado)

![Relação entre Lancamento - Usuário e Permissões](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/bf5d7c17f9f1096d18524edf67596225abc4e149/documentos/User_Permissions.png)

### Passo 2: Criar as relações de usuários e permissões
- [x] Adicionar dependência para o jwt.io (Acessar https://jwt.io e https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt)
- [x] Criar a classe Permission em "Models"
  - Implementar GrantedAuthority (springframework.security.core.GrantedAuthority) 
- [x] Criar a classe User em "Models"
  - Implementar GrantedAuthority (springframework.security.core.GrantedAuthority)
- [x] Criar a relação de Muitos para Muitos (ManyToMany) para User/Permissions
- [x] Criar o método getRoles
- [x] Verificar a criação das tabelas no Banco de Dados
- [x] Popular as tabelas de usuário e permissão - [códigos de usuário e permissões](#código-final)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula15.png)](https://www.youtube.com/watch?v=Tzy9ryJpFls)
-
🥈:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula15.png)](https://www.youtube.com/watch?v=ljvxBBt5qVc)
-
🥉:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula15.png)](https://www.youtube.com/watch?v=JIh3ahARElA)


### Código atualizado

```
//========================= TotalItemDTO


//========================= TotalSubItemDTO


//========================= LancamentoDAO


//========================= GestaoLancamento


//========================= LancamentoController


```
[voltar](#passo-1-testes-povoamento-e-buscas-agrupadas)

### Código Final

```
//========================= Permission - Model


//========================= User - Model




```
[voltar](#passo-1-criar-a-classe-de-lançamento)


### Passo 3: Atualizar o github com os códigos atuais (camada com obras)

