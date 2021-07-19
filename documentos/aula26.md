# Aula 26 - Desenvolvimento de Aplicações WEB

> 
> 
>   Estudo de caso: Gestão de Obras - Front-End


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Construção do Front-End - React.Js (Vinculação do Dashboard à API - Outros contextos e conclusão)

[Projeto do Front-End](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/5d19019b2d7f1c8e8bf26b6a55df8f575392101c/documentos/frontend_projeto.png)

### Passo 1: Vinculando o Dashboard à API
- [x] Vinculando o Gráfico de Barras
  - Criar uma interface para total de itens (itemDescricao e total) - vide Json Postman
  - Criar um useState para totalitens
  - Criar um useState para ChartData
  - Criar um vínculo com o token para validar a consulta
  - Criar um useEffect para busca de itens
  - Vincular o gráfico com os dados retornados
- [x] Vinculando o Gráfico de Rosca
  - Repetir os passos do Gráfico de Barras
- [x] Vinculando a Tabela de Dados
  - Repetir os passos dos gráficos, alterando o vínculo para a tabela 

### Passo 2: Ajustes e melhorias
- [x] Criando mensagens nos cadastros/listagens
- [ ] Usando imagens para carregamentos de páginas login/cadastros/listagens

#### loading index.tsx
```
<div className="load">
    Loading...
</div>
```

#### loading styles.css
```
.load {
  position:absolute;
  top:35%;
  left:20%;
  color:blue;
  
}

.load img {
  width:200px;
  height:200px;
}
```



### Passo 3: Criando o contexto de Obras
- [x] Replicando o contexto de proprietários em obras
- [x] Criando um "select" em obras para buscar proprietários
- [x] Criando os outros contextos (itens, subitens, lançamentos)

### Passo 4: Conclusão
- [x] Outras possibilidades


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula26](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=_FDCRhibEUo)

-
🥈:[![material complementar aula26](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=R0vykfteMb0)

-
🥉:[![material complementar aula26](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=t4N0atc8xi0)



## Códigos finais
### DashBoard/Index.tsx
```

```
  
### DashBoard/Styles.css
```


```
  



