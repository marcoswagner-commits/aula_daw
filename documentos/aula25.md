# Aula 25 - Desenvolvimento de Aplicações WEB

> Aula 15/09/2021
> 
>   Estudo de caso: Gestão de Obras - Front-End


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Construção do Front-End - React.Js (Criando o contexto de proprietários)

[Projeto do Front-End](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/5d19019b2d7f1c8e8bf26b6a55df8f575392101c/documentos/frontend_projeto.png)

### Passo 1: Preparando o contexto Proprietários (Consulta/Listagem)
- [x] Criando uma pasta proprietarios (para listagem) em src
  - Criar os arquivos index.tsx e styles.css - vide códigos abaixo
- [x] Criando uma pasta proprietario (para inclusão/atualização) em src 
  - Criar os arquivos index.tsx e styles.css - vide códigos abaixo
- [x] Criando uma lista de proprietarios para ser usada com o Hook useState
  - Usar o conceito de interface em TypeScript para configurar a variável de estado 
- [x] Criando constantes para armazenar o "username" e "token"
- [x] Criando um Hook useHistory (acesso de outras instâncias de navegação)
- [ ] Relatando o conceitos de "promises", especialmente async, await, .then()
- [x] Criando a função principal loadProprietarios
- [ ] Criando um Hook useEffect
- [x] Criando a função editProprietario()
- [x] Criando a função deleteProprietario()
- [x] Ajustando tabela de proprietarios e outras opções




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
  
### Menu.tsx
```


```
  
  
### App.tsx
```

```

### Index.tsx
```



```


