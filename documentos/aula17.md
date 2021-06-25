# Aula 17 - Desenvolvimento de Aplicações WEB

> 
> 
>  * Estudo de caso: Gestão de Obras *


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Autenticação com JWT e Spring Security


#

![Relação entre Lancamento - Usuário e Permissões](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/bf5d7c17f9f1096d18524edf67596225abc4e149/documentos/User_Permissions.png)

### Passo 1: Criar as relações de usuários e permissões
- [x] Concluir a tabela/entidade de usuários (user)
- [x] Verificar a criação das tabelas no Banco de Dados
- [x] Criar a interface de UserDAO
  - Criar uma assinatura para busca do username usando uma JPQL   
- [x] Criar a classe GestaoUsers (services)
  - Implementar a Interface UserDetailsService
  - Fazer o vínculo com o UserDAO
  - Criar um método para busca de userName
- [x] Criar um pacote Security
  - Criar uma classe ContaDTO com username e password
- [x] Criar uma classe AutenticacaoException (similar a BusinessExecption)
  - Anotar com @ResponseStatus(HttpStatus.BAD_REQUEST)
  - Incluir herança (extends) para AuthenticationException
  - Incluir um construtor
- [x] Atualizar a classe ExceptionHandler com a classe AutenticacaoException
  - Criar o método AutenticacaoException
 - [ ] [códigos de users e permissions](#código-atualizado)


### Passo 2: Criar JWT - JwtTokenProvider
- [x] Criar um pacote dentro de Security com o nome JWT
- [x] Criar no pacote JWT a classe JwtTokenProvider
  - Colocar anotação @Service
  - Criar dois atributos: chave_secreta (String = "segredo") e tempo_validade (long = 3.600.000 - 1hora)
  - Anotar o atributo chave_secreta com @Value("${security.jwt.token.secret-key:segredo}")
  - Anotar o atributo tempo_validade com @Value("${security.jwt.token.expire-lenght:3600000}")
  - Injetar a classe UserDetailsService
  - Criar um método init (@PostConstruct) e "encodar" o secretkey
  - Criar um método "createToken" para fazer a certificação "Claims", vincular as "roles" e estabelecer a duração do token
  - Criar um método para autenticar o token (Authentication) / Gerar um método getUserName
  - Criar um método resolveToken para retornar um "bearerToken" com "Header" "Authorization"
  - Criar um método para validar o token (validateToken)
  - Acessar a página https://jwt.io
 - [ ] [códigos de users e permissions](#código-jwttokenprovider)

[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=VqcttlfbeIk)
-
🥈:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=omG0xcaolp4)
-
🥉:[![material complementar aula14](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=yx7FlCdwapE)


### Código atualizado

```


```
[voltar](#passo-1-criar-as-relações-de-usuários-e-permissões)

### Código JwtTokenProvider

```



```
[voltar](#passo-2-criar-jwt---jwttokenprovider)


### Passo 3: Atualizar o github com os códigos atuais (JwtTokenProvider)

