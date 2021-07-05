# Aula 21 - Desenvolvimento de Aplicações WEB

> Aula 22/08/2021
> 
>   Estudo de caso: Gestão de Obras - Front-End


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Construção do Front-End - React.Js


### Passo 1: Definições, Ferramentas e Passos Iniciais 
- [x] Consumindo uma API com ReactJS
- [ ] Noções Gerais sobre a arquitetura e o React
- [ ] Instalação (Linux - Mac) - [Vide Códigos](#instalação)
- [ ] Criando o Projeto do Front-End juntamente com o Back-End existente em monorepositório
  - Criar projeto ReactJS com `create-react-app`:
    ```bash
    npx create-react-app frtend-gto --template typescript
    ```
> Lembrete: excluir repositório Git do projeto ReactJS
  - Limpar projeto ReactJS / tsconfig.json
      - Arquivo _redirects
    ```
    /* /index.html 200
    ```
- **COMMIT: Project clean**

### Estrutura de Pastas do Projeto (monorepositório)
![Pastas do Projeto](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/fe5c2cc8a99cd618a16d1f43c0a6d133d321c865/pastas_projeto.png)

### Estruturas de Comunicação
![modelo_spa_front](https://user-images.githubusercontent.com/81576640/124515048-dad25000-ddb4-11eb-87b0-8e1c543e6ff6.png)

![modelo_trad_front](https://user-images.githubusercontent.com/81576640/124515052-ddcd4080-ddb4-11eb-9df9-d2f3cc4d13e6.png)


### Relação entre o Back-End e o Front-End 
![Relação entre o Back-End e o Front-End](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cc080a27531053afe5f67f30a904a3789fb61481/documentos/back-front.png)

### Estrtura do Front-End
![Estrutura do Front-End](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/c263030eb4d8f6573fe98f64d9a158ea38da6548/documentos/front-end-org.png)



🅰️
### Passo 1: criar projetos


### Passo 3: adicionar Bootstrap e CSS ao projeto
- Bootstrap
```
yarn add bootstrap
```



[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=O4Mm6Pt5hoI)
-
🥈:[![material complementar aula17](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=uFD6ZPKdO0Y)

# Instalação
## Linux
### Node & NPM

```
sudo apt update

curl -sL https://deb.nodesource.com/setup_lts.x | sudo -E bash -

sudo apt-get install nodejs
```

### YARN (Caso queira usar o YARN ao invés do NPM)
No Debian e no Ubuntu:
```
 curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
 
 echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list
 
 sudo apt-get update && sudo apt-get install yarn

```
Caso tenha problemas de instalação a própria DOC do Yarn é muito útil: https://classic.yarnpkg.com/pt-BR/docs/install/#debian-stable

### VS Code

```
https://code.visualstudio.com/download

sudo snap install code --classic
```

## Mac
### Node & NPM 
```
$ brew install node
```

### YARN (Caso queira usar o YARN ao invés do NPM)

```
$ brew install yarn
```

### VS Code

1. Download Visual Studio Code for macOS. https://go.microsoft.com/fwlink/?LinkID=534106
2. No Finder abrir a pasta de downloads e localizar o arquivo baixado. 
3. Arrastar o Visual Studio Code.app para a pasta Applications , para que ele fique disponível no macOS Launchpad.
5. Adicionar VS Code na Dock clicando com o botão direito no icone e no menu de contexto selecionar: Options, Keep in Dock.

### Git
```
$ brew install git
```


