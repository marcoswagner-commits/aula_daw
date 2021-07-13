# Aula 24 - Desenvolvimento de Aplicações WEB

> 
> 
>   Estudo de caso: Gestão de Obras - Front-End


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Construção do Front-End - React.Js (Páginas Home e Deploy no Netlify, Página de Login e Routers)

[Projeto do Front-End](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/5d19019b2d7f1c8e8bf26b6a55df8f575392101c/documentos/frontend_projeto.png)

### Passo 1: Preparando a aplicação
- [x] Limpando novamente o projeto - instalando outras extensões (react-icon, react-router-dom)
  - {yarn add react-icons; https://react-icons.github.io/react-icons/ }
  - {yarn add react-router-dom; obs.: instalar as tipagens separadas - yarn add @types/react-router-dom -D}
- [x] Importando o bootstrap
- [ ] (index.tsx) => import 'bootstrap/dist/css/bootstrap.css'
  - Verificar no node_modules a existência do bootstrap
- [x] Criação de pastas e arquivos
- [ ] Criar a pasta assets em src
- [ ] Criar a pasta css em assets
- [ ] Criar o arquivo styles.css em css (copiar código abaixo)
- [ ] (index.tsx) => import 'assets/css/styles.css' [🏴](#assetscssstylescss)
- [ ] Escolher as fontes (Ubuntu e Roboto) em Google Fonts (https://fonts.google.com)
  - Mostrar as opções link e import
  - Verificar as mudanças na aplicação


Extensões:
- Dracula
- Material Icon
- Fira Code (https://github.com/tonsky/FiraCode)
- AutoRenameTag
- ColorHighLight
- SVG Preview (obs.: https://www.flaticon.com/ repositório de ícones)

#### assets/css/styles.css
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
[Voltar](#passo-1-preparando-a-aplicação)


### Passo 2: Criando a página inicial (Página de Acesso)
- [x] Criar o primeiro componente do projeto
    - Criar a pasta NavBar em Components/Basics
    - Criar o arquivo Index.tsx em Components/Basics/NavBar  
    - Colocar um item de "teste" em NavBar/Index.tsx e inserir o componente em App
    - Inserir o código NavBar (código abaixo) no componente [🏴](#componentsbasicsnavbarindextsx)
    - Inseri o arquivo (imagem) em assets/images (arquivo no github)
- [x] Criar o segundo primeiro componente do projeto para Footer
    - Seguir os mesmos procedimentos para Footer realizados em NavBar [🏴](#componentsbasicsfooterindextsx)
- [x] Criar um container para o Body da página principal
- [x] Criar a pasta pages em src
- [x] Criar a pasta home em pages
- [ ] Criar o arquivo Index.tsx em home [🏴](#homeindextsx)
- [ ] Criar o arquivo Styles.css em home [🏴](#homestylescss)


Exemplos retirados do site do BootStrap (https://getbootstrap.com)

#### Components/Basics/NavBar/index.tsx
- apenas o conteúdo do componente
```
<div className="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-light border-bottom shadow-sm">
  <div className="container">
    <nav className="my-2 my-md-0 mr-md-3">
      <img src={logo_gto_vf} alt="Gestão de Obras" width="220" />
    </nav>
  </div>
</div>

```

#### Components/Basics/Footer/index.tsx
- apenas o conteúdo do componente
```
<footer className="footer mt-auto py-3 bg-dark">
  <div className="container">
    <p className="text-light">App desenvolvido por <a href="https://github.com/marcoswagner-commits" target="_blank" rel="noreferrer">Prof. Marcos Wagner</a></p>
    <p className="text-light"><small><strong>Desenvolvimento de Aplicações WEB - BCC - UFJ</strong><br/>
      Disciplina de Desenvolvimento de Aplicações WEB: <a href="https://github.com/marcoswagner-commits/gestao_obras_aula_daw" target="_blank" rel="noreferrer"></a></small></p>
  </div>
</footer>

```
![Pagina_Acesso](https://user-images.githubusercontent.com/81576640/125181703-dacfb700-e1dd-11eb-8cc1-d2b6b1109c23.png)


[Voltar](#passo-2-criando-a-página-inicial-página-de-acesso)

### Passo 3: Implantação no Netlify
- Publicação no Netlify
  - Criar conta/acessar (https://netlify.com)
  - "New Site From Git" 
    - Escolher Projeto / Definir Branch (codigos)   
  - Base Url: frtend-gto
  - Comando: yarn build
  - Diretório: frtend-gto/build
  - Deploy! (Caso exista algum erro por não encontrar a pasta específica)
    - Site settings -> Build & Deploy: Build settings: Base Directory: (frtend-gto)
    - Site settings -> Domain Management: (colocar o nome que você quiser)
    - Deploys -> Trigger deploy


### Passo 4: Criando a página de Login (Autenticação)
- [x] Criar o arquivo routes.tsx
  - [ ] Detalhar seu uso
  - [ ] Criar a página provisória de login para teste
  - [ ] Relatar uso do "exact" e "link"

- [x] Apresentando o Material-UI

- [x] Criando a página de login
  - [ ] Usando uma estrutura pronta "vazia" [🏴](#pagesloginindextsx)
  - [ ] Importando o framework "axios"
  - [ ] Criar uma pasta services em src
  - [ ] Criar um componente API.tsx
  
  
  - [ ] Importar em login o componente API
  - [ ] Fazer os imports de useState e history

#### Pages/Login/index.tsx
```
import { useState } from 'react'
import NavBar from 'components/basics/navbar';
import '../login/styles.css'
import Lock from '../../assets/images/padlock.png'


import { MdLock, MdForum } from "react-icons/md"
import { HiEye, HiEyeOff } from "react-icons/hi"

function Login() {
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")
  const [show, setShow] = useState(false)

  const handleClick = (e: any) => {
    e.preventDefault()
    setShow(!show);
  }

  return (
    <>
      <div className="header">
        <NavBar />
      </div>
      <div className="login">
        <div className="login-logo">
          <img
            src={Lock}
            alt="MdLockLogin App"
          />
        </div>

        <div className="login-right">
          <h1>A U T E N T I C A Ç Ã O</h1>

          <div className="login-loginInputEmail">
            <MdForum />
            <input
              type="user"
              placeholder="Digite um usuário"
              value={username}
              onChange={e => setUsername(e.target.value)}
            />
          </div>

          <div className="login-loginInputPassword">
            <MdLock />
            <input
              placeholder="Digite sua senha"
              type={show ? "text" : "password"}
              value={password}
              onChange={e => setPassword(e.target.value)}
            />
            <div className="login-eye">
              {show ? (
                <HiEye
                  size={20}
                  onClick={handleClick}
                />
              ) : (
                <HiEyeOff
                  size={20}
                  onClick={handleClick}
                />
              )}
            </div>
          </div>

          <button type="submit">
            E N T R A R
          </button>

        </div>
      </div>
    </>
  )
}

export default Login
```

#### Pages/Login/sytles.tsx
```
.login {
  width: 1100px;
  height: 65%;
  max-width: 1100px;
  margin: 0 auto;
  padding: 0 0px;
  display: flex;
  align-items: flex-start;
  background-color: #ffffff;
  color: white;
 }

.login-logo img {
  width: 350px;
  height: 350px;
}

.login-right { 
  background-color: #e6e6f0;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-left: 50px;
  padding: 20px;
  width: 500px;
  height: 400px;
}

.login-right h1 {
  font-family: 'Roboto';
  font-size: 40px;
  margin-bottom: 50px;
}

.login-right h4 {
  font-family: 'Roboto Slab';
  font-weight: 300;
  margin-top: 40px;
  font-size: 15px;
  font-weight: 500;
  color: #333349;
}

.login-loginInputEmail {
  display: flex;
  align-items: center;
  color: gray;
  background-color: #1A1A1D;
  border-radius: 5px;
  padding: 3px;
  width: 98%;
  height: 50px;
}

.login-loginInputEmail svg {
  margin-left: 10px;
  font-size: 25px;
}

.login-loginInputEmail input {
  background: transparent;
  width: 100%;
  outline-width: 0;
  color: #E1E1E6;
  border: none;
  font-size: 17px;
  margin-left: 10px;
  margin-right: 10px;
}

.login-loginInputPassword {
  display: flex;
  align-items: center;
  color: rgb(189, 181, 181);
  background-color: #1e1e20;
  border-radius: 3px;
  padding: 3px;
  margin: 5px;
  width: 98%;
  height: 50px;
  border-radius: 5px;
}

.login-loginInputPassword svg {
  margin-left: 10px;
  font-size: 25px;
}

.login-loginInputPassword input {
  background: transparent;
  width: 100%;
  outline-width: 0;
  color:  #101011;
  border: none;
  font-size: 17px;
  margin-left: 12px;
  margin-right: 10px;
}

.login-right button {
  width: 98%;
  background-color: #4c4c79;
  color: #131414;
  font-weight: 800;
  height: 50px;
  border-radius: 5px;
  font-size: 18px;
  margin-top: 5px;
  border: none;

  outline-width: 0;
}

.login-right button:hover {
  background-color: #4c4c50;
  color: #161718;
  cursor: pointer;
}

.login-eye {
  align-items: center;
  justify-content: center;
  font-size: 30;
  cursor: pointer;
  margin-right: 10px;
}
```


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula24](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=yckK1zONYCw)

-
🥈:[![material complementar aula24](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=y8zYNCPAMUc)

-
🥉:[![material complementar aula24](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=t4N0atc8xi0)



## Códigos finais
### Home/Index.tsx
```
import NavBar from 'components/basics/navbar';
import Footer from 'components/basics/footer';
import { FiLogIn } from 'react-icons/fi';


import './styles.css';
import logo from '../../assets/images/builder.svg';

const Home: React.FC = () => {
  return (
    <>
      <div id="header">
        <NavBar />
      </div>
      <div id="conteudo">
        <div id="contentl">
          <h1>Sistema de Gestão de Obras</h1>
          <p>Controle de gastos de obras de construção civil com classificação de lançamentos e filtros.</p>
          <a href="/autentica">
            <span>
              <FiLogIn />
            </span>
            <strong>Acessar o sistema!</strong>
          </a>
        </div>
        <div id="contentr">
          <img src={logo} alt="Gestão de Obras" />
        </div>
      </div>
      <div id="footer">
        <Footer />
      </div>
    </>
  );
}

export default Home;

```
[Voltar](#passo-2-criando-a-página-inicial-página-de-acesso)
  
### Home/Styles.css
```
#conteudo {
  width: 90%;
  height: 65%;
  max-width: 1100px;
  margin: 0 auto;
  padding: 0 0px;
  display: flex;
  align-items: flex-start;
 }

#conteudo .contentl {
  max-width: 650px;
  float:left;
}

#contentr {
  max-width: 350px;
  display: flex;
  flex-direction: column;
  float:left;
}

#contentl h1 {
  font-size: 64px;
  color: rgb(61, 23, 23);
}

#contentl p {
  font-size: 24px;
  margin-top: 18px;
  line-height: 34px;
  color: rgb(61, 23, 23);
}

#contentl a {
  width: 100%;
  max-width: 360px;
  height: 72px;
  background: #093d0f;
  border-radius: 8px;
  text-decoration: none;
  display: flex;
  align-items: center;
  overflow: hidden;
  margin-top: 40px;
}

#contentl a span {
  display: block;
  background: rgba(206, 202, 202, 0.08);
  width: 72px;
  height: 72px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background-color 0.2s;
}

#contentl a span svg {
  color: #FFF;
  width: 20px;
  height: 20px;
}

#contentl main a strong {
  flex: 1;
  text-align: center;
  color: #FFF;
}

#contentl main a:hover {
  background: #79ab7f;
}

@media(max-width: 1000px) {
  #conteudo .contentl {
    align-items: center;
    text-align: center;
  }

  #contentl main h1 {
    font-size: 42px;
  }

}



```
[Voltar](#passo-2-criando-a-página-inicial-página-de-acesso)

### Pages/Login/Index.tsx
```



```
  
  
### App.tsx
```
import 'react-perfect-scrollbar/dist/css/styles.css';
import { useRoutes } from 'react-router-dom';
import { ThemeProvider } from '@material-ui/core';
import GlobalStyles from 'src/components/GlobalStyles';
import 'src/mixins/chartjs';
import theme from 'src/theme';
import routes from 'src/routes';

const App = () => {
  const routing = useRoutes(routes);

  return (
    <ThemeProvider theme={theme}>
      <GlobalStyles />
      {routing}
    </ThemeProvider>
  );
};

export default App;
```

### GlobalStyles.tsx
```

import { createStyles, makeStyles } from '@material-ui/core';

const useStyles = makeStyles(() => createStyles({
  '@global': {
    '*': {
      boxSizing: 'border-box',
      margin: 0,
      padding: 0,
    },
    html: {
      '-webkit-font-smoothing': 'antialiased',
      '-moz-osx-font-smoothing': 'grayscale',
      height: '100%',
      width: '100%'
    },
    body: {
      backgroundColor: '#f4f6f8',
      height: '100%',
      width: '100%'
    },
    a: {
      textDecoration: 'none'
    },
    '#root': {
      height: '100%',
      width: '100%'
    }
  }
}));

const GlobalStyles = () => {
  useStyles();

  return null;
};

export default GlobalStyles;


```

### Logo.tsx
```

const Logo = (props) => (
  <img
    alt="Logo"
    width="50%"
    src="/static/images/SISPFC_LOGO_BW.png"
    {...props}
  />
);

export default Logo;

```

### routes.tsx
```

import { Navigate } from 'react-router-dom';
import Login from 'src/pages/Login';

const routes = [
  {
    path: 'app',
    element: <DashboardLayout />,
    children: [
      { path: 'proprietarios', element: <AlunoLista /> },
      { path: 'dashboard', element: <Dashboard /> },
      { path: 'products', element: <ProductList /> },
      { path: 'settings', element: <Settings /> },
      { path: '*', element: <Navigate to="/404" /> }
    ]
  },
  {
    path: '/',
    element: <MainLayout />,
    children: [
      { path: 'login', element: <Login /> },
      { path: 'register', element: <Register /> },
      { path: '404', element: <NotFound /> },
      { path: '/', element: <Navigate to="/app/dashboard" /> },
      { path: '*', element: <Navigate to="/404" /> }
    ]
  }
];

export default routes;


```

