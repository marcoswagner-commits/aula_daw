# Aula 18 - Desenvolvimento de Aplicações WEB

> 
> 
>  * Estudo de caso: Gestão de Obras *


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Povoamento de Usuários/Permissões - Teste da geração do Token - Envio de E-mails


### Passo 1: Povoar usuários e permissões
- [x] Concluir a classe Permission (anotação ManytoMany)
- [x] Criar a classe PermissionDAO
  - Criar duas permissões (ADMIN e USUARIO)
  - Criar dois usuários
- [x] Povoar as duas classes/tabelas por meio do CommandLineRunner
- [x] Colocar o bcript para gerar as senhas
- [x] Alterar no pacote Config a classe ConfigSecurity para aceitar qualquer acesso
- [x] Criar um método temporário para consulta de usuários e verificação de senhas no Controller
  
 - [ ] [códigos de users e permissions](#código-atualizado)


### Passo 2: Verificar funcionamento do JWT
- [x] Verificar funcionamento por meio do Swagger
- [x] Verificar funcionamento por meio do PostMan
  - Analisar o processo de criação, validação e retorno do token (Figura abaixo)
  - Acessar a página https://jwt.io
 - [ ] [código do JwtTokenProvider](#código-jwttokenprovider)

![JWT](https://user-images.githubusercontent.com/81576640/123691376-ef01d480-d82b-11eb-998c-7507fccddcbf.png)

### Passo 3: Implementar o envio de e-mails
- [x] Inserir a dependência spring-boot-starter-mail
- [x] Inserir a configuração de e-mail no arquivo application.properties
- [x] Criar uma classe EnviarMailService no pacote Services
  - Criar um método "enviar" com parâmetros (destinatário, assunto e conteúdo)
  - Criar um método doFilter

- [ ] [código do EnviarMailService](#código-enviarmailservice)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=FFVQ0pzuh6c)
-
🥈:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=inLcdThy9YM)
-
🥉:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=p35mSdb9BaQ)
-
🥉:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/453a8d1cfb45bc3b0c35c4df91cbe8e8dc89b540/documentos/Capa_Aula16.png)](https://www.youtube.com/watch?v=ej04SL61UOQ)



```
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

```

```
spring.mail.host=smtps.uhserver.com
spring.mail.port=993
spring.mail.protocol=smtp
spring.mail.username=noreply@ufjnet.net
spring.mail.password=????
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.quitwait=false 


```



### Código atualizado
- classe user (na camada models)
```


```
- classe userDAO (na camada Repositories)
```

```
- classe GestaoUser (na camada Services)

```

	
```
- classe UserDTO (na camada Security)

```

```
- classe InvalidAuthenticationException (na camada services.exceptions)

```


```

- método InvalidAuthenticationException (na classe ExcpetionHandler - pacote exceptionhandler)
```


```

[voltar](#passo-1-criar-as-relações-de-usuários-e-permissões)

### Código JwtTokenProvider

```

```
[voltar](#passo-2-criar-jwt---jwttokenprovider)

### Código JwtTokenFilter
```

```
[voltar](#passo-3-criar-jwt---jwttokenfilter-e-jwttokenconfigure)


### Código JwtTokenConfigurer
```

```

[voltar](#passo-3-criar-jwt---jwttokenfilter-e-jwttokenconfigure)

### Código Config
```

```
[voltar](#passo-3-criar-jwt---jwttokenfilter-e-jwttokenconfigure)

### Código AutenticaController
```

```

[voltar](#passo-4-criar-o-método-autenticacontroller)





### Passo 5: Atualizar o github com os códigos atuais (JwtToken completo)

