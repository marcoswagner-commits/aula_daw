# Aula 23 - Desenvolvimento de Aplicações WEB

> 
> 
>   Estudo de caso: Gestão de Obras - Front-End


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Construção do Front-End - React.Js (Conceitos de JSX, TSX, Componentes, Propriedades e Estados)


### Passo 1: Explorando a aplicação
- [x] Interagindo com o React.DOM
  - Render
  - documento.getElementById
- [x] Interagindo com o React
  - Incluindo uma tag <div>
  - Apresentando a sintaxe JSX/TSX
  - Incluindo uma constante com uma tag <strong>
```
React.createElement ('div')
```
  
- [x] Carregando um CSS (index.css)
  - Criando o arquivo
  - Mudando a aparência da página

```
body { background-color: #222, color: #fff } 
```
  
  - Importando o css...
- [x] Criando outros componentes
  - Criando uma pasta "components" - com subpasta "basics"
  - Criando um componente "Primeiro.tsx"

  
```
export default function Primeiro() {
  const msg = 'Proprietário seja bem vindo!'
  return (
  <div>
    <h3>Proprietário da Obra</h3>
    <h4>{ msg }</h4>
  </div>
  )
}
```
  
  - Importando o componente... 
  - Incluindo o JSX no componente 
- [x] Criando componentes com parâmetros
  - Criando uma função anônima
  - Passando parâmetros para a função (propriedades)
  ```
  function Primeiro() { return "Primeiro componente" } export default Primeiro();
  ```
  - Importando o componente... 
  - Incluindo o JSX no componente 
  

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




[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=SoEGwrvXuPg)
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

