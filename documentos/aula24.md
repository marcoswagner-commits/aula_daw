# Aula 24 - Desenvolvimento de Aplicações WEB

> Aula 09/09/2021
> 
>   Estudo de caso: Gestão de Obras - Front-End


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Construção do Front-End - React.Js (Páginas Home e Login)


### Passo 1: Preparando a aplicação
- [x] Limpando novamente o projeto - instalando outras extensões
- [x] Importando o bootstrap
- [ ] (index.tsx) => import 'bootstrap/dist/css/bootstrap.css'
  - Verificar no node_modules a existência do bootstrap
- [x] Criação de pastas e arquivos
- [ ] Criar a pasta assets em src
- [ ] Criar a pasta css em assets
- [ ] Criar o arquivo styles.css em css (copiar código abaixo)
- [ ] (index.tsx) => import 'assets/css/styles.css'
- [ ] Escolher as fontes (ubumtu e roboto) em Google Fonts (https://fonts.google.com)
  - Mostrar as opções link e import
  - Verificar as mudanças na aplicação


Extensões:
- Dracula
- Material Icon
- Fira Code (https://github.com/tonsky/FiraCode)
- AutoRenameTag
- ColorHighLight
- SVG Preview (obs.: https://www.flaticon.com/ repositório de ícones)

```
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap');
:root {
    --color-primary: #FF8400;
}

html, body {
    height: 100%;
    font-family: "Ubuntu", sans-serif;
}

#root {
    display: flex;
    flex-direction: column;
    height: 100%;
}

.content {
    flex: 1 0 auto;
}

.footer {
    flex-shrink: 0;
    text-align: center;
}

.bg-primary {
    background-color: var(--color-primary) !important;
}

.text-primary {
    color: var(--color-primary) !important;
}

```

### Passo 2: Criando página inicial (home)
- [x] Criar o primeiro componente do projeto
    - Criar a pasta NavBar em Components/Basics
    - Criar o arquivo Index.tsx em Components/Basics/NavBar 
    - Colocar um item de texte em NavBar/Index.tsx e inserir o componente em App
    - Inserir o código NavBar (código abaixo) no componente
    - Inseri o arquivo (imagem) em assets/images (arquivo no github)
- [x] Criar o segundo primeiro componente do projeto para Footer
    - Seguir os mesmos procedimentos para Footer relaizados em NavBar
- [x] Criar um container para o Body da página principal
- [x] Criar a pasta pages em src
- [x] Criar a pasta home em pages  

Exemplos retirados do site do BootStrap (https://getbootstrap.com)

- NavBar  
```
<div className="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-light border-bottom shadow-sm">
  <div className="container">
    <nav className="my-2 my-md-0 mr-md-3">
      <img src={logo_gto_vf} alt="Gestão de Obras" width="120" />
    </nav>
  </div>
</div>

```

- Footer  
```
<footer className="footer mt-auto py-3 bg-dark">
  <div className="container">
    <p className="text-light">App desenvolvido por <a href="https://github.com/marcoswagner-commits" target="_blank" rel="noreferrer">Prof. Marcos Wagner</a></p>
    <p className="text-light"><small><strong>Desenvolvimento de Aplicações WEB - BCC - UFJ</strong><br/>
      Disciplina de Desenvolvimento de Aplicações WEB: <a href="https://github.com/marcoswagner-commits/gestao_obras_aula_daw" target="_blank" rel="noreferrer"></a></small></p>
  </div>
</footer>


```




[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=SoEGwrvXuPg)

-
🥈:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=3376NU3r-aE)

-
🥉:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=t4N0atc8xi0)



## Códigos finais
### Body.tsx
```

```
  
### Header.tsx
```


```
  
### Menu.tsx
```


```
  
  
### App.tsx
```

```

### Index.tsx
```



```


