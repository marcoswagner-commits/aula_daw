# Aula 22 - Desenvolvimento de Aplicações WEB

> Aula 12/09/2023
> 
>   Estudo de caso: Gestão de Obras - Front-End


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Construção do Front-End - React.Js


### Passo 1: Definições, Ferramentas e Passos Iniciais 
- [x] Consumindo uma API com ReactJS
- [ ] Noções Gerais sobre a arquitetura e o React
- [ ] Instalação (Linux - Mac) - [Vide Códigos](#instalação)
  - Node.Js (https://nodejs.org) é uma tecnologia assíncrona de requisições que trabalha em uma única thread de execução. [+](#nodejs)
  - NPM (Node Package Manager - https://www.npmjs.com) é uma ferramenta do Node.Js para gerenciar pacotes. [+](#npm)
  - Yarn (Yet Another Resource Negociator - https://yarnpkg.com) também é um gerenciador de pacotes. [+](#yarn)
  - React.Js ("Uma biblioteca JavaScript para criar interfaces de usuário" - https://pt-br.reactjs.org). [+](#react)
  - Outros conceitos importantes: HTML - CSS - JavaScript - TypeScript. [+](#outros)
  - Visual Studio Code (https://code.visualstudio.com/download) IDE leve e amplamente usada pela comunidade de programadores. [+](#visual-studio-code)
- [ ] Criando o Projeto do Front-End juntamente com o Back-End existente em monorepositório
  - Criar projeto frtend-gto com `create-react-app`:
    ```bash
    npx create-react-app frtend-gto --template typescript
    ```
  - Iniciar o projeto (página padrão React.Js)
  - Analisar o projeto (Pastas Public e Src)
    - Conceitos de SPA (Single Page Application) 
  - Limpar todo o projeto
    - Criar uma primeira estrutura (título, descrição, frases em inglês)
    - Manter apenas em public (favicon.ico e index.html) e em src (Apps.tsx e index.tsx) 
    - Alterar o arquivo tsconfig.json - em compilerOptions inserir "baseUrl": "./src"
    - Criar um arquivo _redirects na pasta Public e colocar em seu conteúdo /* /index.html 200 (Netlyfy)

> Lembrete: excluir repositório Git do projeto frtend-gto

  - Commit - "projeto com front-end inicial"


### Passo 2: Projeto inicial com front-end limpo
- [x] Criar uma aplicação simples com o título "Gestão de Obras"
- [x] Configuração o VSCode
- [x] Instalar o BootStrap (https://getbootstrap.com)
  - yarn add bootstrap
  - (index.tsx) import 'bootstrap/dist/css/bootstrap.css';

### Node.js
Node.js é uma base (apesar de não ser um framework) de aplicação, pela qual são escritos programas com Javascript que serão compilados, otimizados e interpretados pela máquina virtual V8. Essa VM é a mesma que o Google utiliza para executar Javascript no browser Chrome, e foi a partir dela que o criador do Node.js, Ryan Dahl, criou o projeto. O resultado desse processo híbrido é entregue como código de máquina server-side, tornando o Node.js muito eficiente na sua execução e consumo de recursos.
[Veja mais sobre Node.js neste artigo...](https://www.luiztools.com.br/post/o-que-e-nodejs-e-outras-5-duvidas-fundamentais/?utm_source=google&utm_medium=cpc&utm_campaign=902557428&utm_content=127000463008&utm_term=o%20que%20node%20js&gclid=CjwKCAjw_o-HBhAsEiwANqYhp34bPP2KF8u1U-UNM9UWWvJ_m5MiCDXAKwjrLeKSHN0EvVvJItXr_BoCVeoQAvD_BwE)

### NPM
NPM (Node Package Manager) é um gerenciador de pacotes para JavaScript. É o gerenciador de pacotes padrão para ambiente Node.js do JavaScript. Consiste em um cliente de linha de comando (npm), e um banco de dados online de pacotes públicos e privados -pagos, chamado de registro NPM. O npm gera um arquivo ‘package-lock.json’. Cada dependência terá um número de versão exato associado a ela no arquivo de bloqueio de pacote. O NPM é nativo do Node.Js. Comandos: ``` npm install ???. npm init. npm run [script]. npm list. npm test. npm link. npm login # or logout. npm publish. ```. Além do NPM também existe o NPX que é um executor de pacotes.

### YARN
YARN (Yet Another Resource Negotiator) é um gerenciador de pacotes assim como o npm. Ele foi desenvolvido pelo Facebook e é de código aberto. A intenção do desenvolvimento do yarn era corrigir problemas de desempenho e segurança do npm. O YARN gera um yarn.lock. E possui uma leitura mais amigável. Para instalar o YARN: npm install yarn --global. Comandos: ``` yarn add ???. yarn why. yarn licenses list. yarn init. yarn run [script]. yarn list. yarn test. yarn link. yarn login # or logout. yarn publish```

### Visual Studio Code
VSC (Visual Studio Code) é um editor de código leve, multiplatafoma, gratuito e open source. Mantido pela Microsoft, essa ferramenta conta com suporte a várias linguagens, extensões, integração com Git, debug, terminal integrado, entre outros recursos.

### React
O React é uma biblioteca JavaScript de código aberto com foco em criar interfaces de usuário em páginas web. É mantido pelo Facebook, Instagram, outras empresas e uma comunidade de desenvolvedores individuais. 

### Outros
HTML (HyperText Markup Language - Linguagem de Marcação de Hipertextos) - CSS (Cascading Style Sheets - Folhas de Estilos em Cascata) - JS (JavaScript) são considerados a "santíssima trindade" da Internet. HTML não é uma linguagem de programação e sim uma linguagem de formatação de conteúdos. CSS é um aplicador de design (cores, sombras, posicionamento). JS é uma linguagem que permite interações, criação de animações, ou seja, um agregador de funcionalidades. Ainda existe o TypeScript que é um superset JavaScript, uma linguagem código aberto construida em cima do Javascript, adicionando definições de tipagem estáticas.

[VOLTAR](#passo-1-definições-ferramentas-e-passos-iniciais)


### Estrutura de Pastas do Projeto (monorepositório)
![Pastas do Projeto](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/fe5c2cc8a99cd618a16d1f43c0a6d133d321c865/pastas_projeto.png)

### Estruturas de Comunicação
![modelo_spa_front](https://user-images.githubusercontent.com/81576640/124515048-dad25000-ddb4-11eb-87b0-8e1c543e6ff6.png)

![modelo_trad_front](https://user-images.githubusercontent.com/81576640/124515052-ddcd4080-ddb4-11eb-9df9-d2f3cc4d13e6.png)


### Relação entre o Back-End e o Front-End 
![Relação entre o Back-End e o Front-End](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cc080a27531053afe5f67f30a904a3789fb61481/documentos/back-front.png)

### Estrtura do Front-End
![Estrutura do Front-End](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/c263030eb4d8f6573fe98f64d9a158ea38da6548/documentos/front-end-org.png)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=pEqD8L-uaCU)
-
🥈:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=OjABhgBOGIk)

-
🥉:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=u8NF8fVtodA)



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
[VOLTAR](#passo-1-definições-ferramentas-e-passos-iniciais)

