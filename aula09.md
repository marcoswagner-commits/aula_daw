# Aula 09 - Desenvolvimento de Aplicações WEB
## Atividades da aula - roteiro

## Implementação do Modelo Conceitual

### Passo 1: Ampliar as regras de negócio relacionadas a classe (entidade) proprietários
- Incluir no repositório ProprietarioDAO outros tipos de acesso a dados
  - Busca de registros por nome
  - Busca de registros por cpf
  - Busca de registros por e-mail
- Ajustar os serviços GestaoProprietario para não permitir inclusão de dados incoerentes (dataIntegrity)
  - Não permissão de cadastro de cpfs iguais
  - Não permissão de cadastro de e-mails iguais
- Vide Códigos 1, 2 e 3 (Repositório, Serviço e Controlador)

### Passo 2: Ampliar e melhorar o tratamento de mensagens de erros
- Criar uma classe em seviços/exceptions para regras de negócio - BusinessException
- Apliar a classe de ExceptionHandler
- Criar um método dataIntegrity (Integridade de dados) para não permitir repetição de cpfs e e-mails
- Verificar os retornos de erros
- Vide Códigos 4 e 5


####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ecbe27da469ec69308caf228b1f5a2d0e4b863a3/Capa_aula08.png)](https://www.youtube.com/watch?v=CncYHx2x-xI)
-
🥈:[![material complementar aula08]https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ecbe27da469ec69308caf228b1f5a2d0e4b863a3/Capa_aula08.png)](https://www.youtube.com/watch?v=qThJM6UPSqE)
-
🥉:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ecbe27da469ec69308caf228b1f5a2d0e4b863a3/Capa_aula08.png)](https://www.youtube.com/watch?v=qGMNf2p6zrk)
-
🥉:[![material complementar aula08](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/ecbe27da469ec69308caf228b1f5a2d0e4b863a3/Capa_aula08.png)](https://www.youtube.com/watch?v=4qkG2kMqmhc)




:shipit: Código 1
```


```

:shipit: Código 2
```

}
```

:shipit: Código 3
```


```

:shipit: Código 4
```


```

:shipit: Código 5
```


```
### Passo 3: Atualizar o github com os códigos atuais
