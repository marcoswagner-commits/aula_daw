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
  - Comando: yarn build
  - Diretório: build
  - Deploy! (por ser um monorepositório o deploy irá falhar por não encontrar a pasta específica)
    - Site settings -> Build & Deploy: Build settings: Base Directory: (frtend-gto)
    - Site settings -> Domain Management: (colocar o nome que você quiser)
    - Deploys -> Trigger deploy


### Passo 4: Criando a página de Login (Autenticação)
- [x] Criar o arquivo routes.tsx
  - [ ] Detalhar seu uso
  - [ ] Criar a página provisória de login para teste
  - [ ] Relatar uso do "exact" e "link"

- [x] Apresentando o Material-UI
  - Apresentando tema escolhido
  - 
- [x] Criar a estrutura de pastas do projeto:
  - [ ] _mocks_ em "src"
  - [ ] conta; dashboard; proprietario; obra; item; subitem; lancamento; settings em "components"
  - [ ] theme em "src"
  - [ ] utils em "src"
- [x] Criar a estrutura de arquivos do projeto (todos tsx):
  - [ ] Conta; Login; Dashboard; Proprietario; Obra; Item; Subitem; Lancamento; Settings em "pages"
  - [ ] DashboardLayout; DashBoardNavbar; DashBoardSidebar; GlobalStyles; Logo; MainLayout; MainNavbar; MainItem em "components/basics"


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=yckK1zONYCw)

-
🥈:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=3376NU3r-aE)

-
🥉:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=t4N0atc8xi0)



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

### Login.tsx
```
import { Link as RouterLink, useNavigate } from 'react-router-dom';
import { Helmet } from 'react-helmet';
import * as Yup from 'yup';
import { Formik } from 'formik';
import {
  Grid,
  Box,
  Button,
  Container,
  Link,
  TextField,
  Typography
} from '@material-ui/core';

const Login = () => {
  const navigate = useNavigate();

  return (
    <>
      <Helmet>
        <title>Entrada | GESTÃO:OBRAS</title>
      </Helmet>
      <Box
        sx={{
          backgroundColor: 'background.default',
          display: 'flex',
          flexDirection: 'column',
          height: '100%',
          justifyContent: 'center'
        }}
      >
        <Container maxWidth="sm">
          <Formik
            initialValues={{
              email: 'usario@ufj.edu.br',
              password: 'Senha123'
            }}
            validationSchema={Yup.object().shape({
              email: Yup.string().email('É preciso ter um usuário válido').max(255).required('É necessário um usuário'),
              password: Yup.string().max(255).required('Senha é necessária')
            })}
            onSubmit={() => {
              navigate('/app/dashboard', { replace: true });
            }}
          >
            {({
              errors,
              handleBlur,
              handleChange,
              handleSubmit,
              isSubmitting,
              touched,
              values
            }) => (
              <form onSubmit={handleSubmit}>
                <Box align="center">
                  <Grid>
                    <Link
                      component={RouterLink}
                      to="/register"
                      variant="h6"
                    >
                      <Button
                        align="left"
                        color="primary"
                        disabled={isSubmitting}
                        size="medium"
                      >
                        ORÇAMENTOS
                      </Button>
                    </Link>
                    <Link
                      component={RouterLink}
                      to="/register"
                      variant="h6"
                    >
                      <Button
                        align="center"
                        color="primary"
                        disabled={isSubmitting}
                        size="medium"
                      >
                        FORNECEDORES
                      </Button>
                    </Link>
                    <Link
                      component={RouterLink}
                      to="/register"
                      variant="h6"
                    >
                      <Button
                        align="rigth"
                        color="primary"
                        disabled={isSubmitting}
                        size="medium"
                      >
                        DOCUMENTOS
                      </Button>
                    </Link>
                  </Grid>
                </Box>
                <Box align="center" sx={{ mb: 1 }}>
                  <Typography
                    color="textPrimary"
                    variant="h1"
                  >
                    Entrada
                  </Typography>
                </Box>
                <Box align="center"><img alt="GTO" src="/static/images/GTO_LOGO.png" /></Box>
                <Typography
                  align="center"
                  color="textSecondary"
                  variant="body1"
                >
                  Entre com seu usuário!
                </Typography>
                <TextField
                  error={Boolean(touched.email && errors.email)}
                  fullWidth
                  helperText={touched.email && errors.email}
                  label="Email Address"
                  margin="normal"
                  name="email"
                  onBlur={handleBlur}
                  onChange={handleChange}
                  type="email"
                  value={values.email}
                  variant="outlined"
                />
                <TextField
                  error={Boolean(touched.password && errors.password)}
                  fullWidth
                  helperText={touched.password && errors.password}
                  label="Password"
                  margin="normal"
                  name="password"
                  onBlur={handleBlur}
                  onChange={handleChange}
                  type="password"
                  value={values.password}
                  variant="outlined"
                />
                <Box sx={{ py: 2 }}>
                  <Button
                    color="primary"
                    disabled={isSubmitting}
                    fullWidth
                    size="large"
                    type="submit"
                    variant="contained"
                  >
                    ENTRAR
                  </Button>
                </Box>
                <Typography
                  color="textSecondary"
                  variant="body1"
                >
                  Não tem uma conta no GTO?
                  {' '}
                  <Link
                    component={RouterLink}
                    to="/register"
                    variant="h6"
                  >
                    Faça o registro!
                  </Link>
                </Typography>
              </form>
            )}
          </Formik>
        </Container>
      </Box>
    </>
  );
};

export default Login;


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

