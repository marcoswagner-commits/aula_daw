# Aula 24 - Desenvolvimento de Aplicações WEB

> 
> 
>   Estudo de caso: Gestão de Obras - Front-End


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Construção do Front-End - React.Js (Páginas Home e Deploy no Netlify, Página de Login e Routers)

[Projeto do Front-End](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/5d19019b2d7f1c8e8bf26b6a55df8f575392101c/documentos/frontend_projeto.png)

### Passo 1: Preparando a aplicação
- [x] Limpando novamente o projeto - instalando outras extensões
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
- [x] Criar em componentes a pasta styles
  - Criar na pasta styles os componentes (Logo.tsx e GlobalStyles.tsx)
- [x] Criar a pasta login em pages
- [x] Criar o arquivo routes.tsx na raiz scr
- [x] Criar em componentes a pasta layouts 
  - Criar na pasta layouts os componentes (MainLayout.tsx e DashboardLayout.tsx)


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=SoEGwrvXuPg)

-
🥈:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=3376NU3r-aE)

-
🥉:[![material complementar aula22](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=t4N0atc8xi0)



## Códigos finais
### Home/Index.tsx
```
import { FiLogIn } from 'react-icons/fi';
import { Link } from 'react-router-dom';

import './styles.css';
import logo from '../../assets/images/builder.svg';

const Home: React.FC = () => {
    return (
        <div id="page-home">
            <div className="content">
                <header>
                    <img src={logo} alt="Gestão de Obras"/>
                </header>

                <main>
                    <h1>Sistema de Gestão de Obras</h1>
                    <p>Controle de gastos de obras de construção civil com classificação de lançamentos e filtros.</p>

                    <Link to="/autentica">
                        <span>
                        <FiLogIn />
                        </span>
                        <strong>Acessar o sistema!</strong>
                    </Link>
                </main>
            </div>
        </div>
    );
}

export default Home;
```
[Voltar](#passo-2-criando-a-página-inicial-página-de-acesso)
  
### Home/Styles.css
```
#page-home {
    height: 100vh;
  
    background: url('../../assets/images/constructor-hand-drawn-worker.svg') no-repeat;
  }
  
  #page-home .content {
    width: 100%;
    height: 100%;
    max-width: 1100px;
    margin: 0 auto;
    padding: 0 30px;
  
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }
  
  #page-home .content header {
    margin: 48px 0 0;
  }
  
  #page-home .content main {
    flex: 1;
    max-width: 660px;
  
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
  
  #page-home .content main h1 {
    font-size: 64px;
    color: #333;
  }
  
  #page-home .content main p {
    font-size: 24px;
    margin-top: 24px;
    line-height: 38px;
  }
  
  #page-home .content main a {
    width: 100%;
    max-width: 360px;
    height: 72px;
    background: #79ab7f;
    border-radius: 8px;
    text-decoration: none;
  
    display: flex;
    align-items: center;
    overflow: hidden;
  
    margin-top: 40px;
  }
  
  #page-home .content main a span {
    display: block;
    background: rgba(0, 0, 0, 0.08);
    width: 72px;
    height: 72px;
  
    display: flex;
    align-items: center;
    justify-content: center;
    transition: background-color 0.2s;
  }
  
  #page-home .content main a span svg {
    color: #FFF;
    width: 20px;
    height: 20px;
  }
  
  #page-home .content main a strong {
    flex: 1;
    text-align: center;
    color: #FFF;
  }
  
  #page-home .content main a:hover {
    background: #79ab7f;
  }
  
  @media(max-width: 900px) {
    #page-home .content {
      align-items: center;
      text-align: center;
    }
  
    #page-home .content header {
      margin: 48px auto 0;
    }
  
    #page-home .content main {
      align-items: center;
    }
  
    #page-home .content main h1 {
      font-size: 42px;
    }
  
    #page-home .content main p {
      font-size: 24px;
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

