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
  
  export default function (props) {
  const valor > 100 ? 'Valor Alto' : 'Valor Baixo'
  return (
  <div>
    <h3>Proprietário da Obra</h3>
    <h4>{ valor }</h4>
  </div>
  )
}
  
```

  


### Passo 2: Fragments, Cards, Componentes Filhos
- [x] Criar uma aplicação simples com o título "Gestão de Obras"
- [x] Configuração o VSCode





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

