# Aula 21 - Desenvolvimento de Aplicações WEB

> Aula 19/08/2021
> 
>   Estudo de caso: Gestão de Obras 


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Suporte ao Docker


### Passo 1: Upload de múltiplos arquivos
- [x] O que é o Docker
- [x] O que é o Docker Compose
  - Adicionando o suporte ao Docker
  - Analisando a aplicação
  - Testando no Postman
- [x] O que é o Docker Hub
- [x] Enviando imagens para o DockerHub
  

```
if (responseCode.code >= 200 && responseCode.code <= 299) {
	var jsonData = JSON.parse(responseBody);
	postman.setEnvironmentVariable('bearer_token',jsonData.token);

```

 
 ### Passo 4: Demonstrar o uso do e-mail
- [x] Verificar o envio do e-mail no cadastro de "Proprietários"
- [x] Criar uma classe de tratamento de exceção para envio de e-mails
- [x] Adicionar o envio do e-mail na atualização de "Proprietários"


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

