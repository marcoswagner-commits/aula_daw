# Aula 25 - Desenvolvimento de Aplicações WEB

> 
> 
>   Estudo de caso: Gestão de Obras - Front-End


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Construção do Front-End - React.Js (Criando o contexto de proprietários)

[Projeto do Front-End](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/5d19019b2d7f1c8e8bf26b6a55df8f575392101c/documentos/frontend_projeto.png)

### Passo 1: Preparando o contexto Proprietários (Consulta/Listagem)
- [x] Criando uma pasta proprietarios (para listagem) em src
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


### Passo 2: Preparando o contexto Proprietários (Cadastro/Atualização)
- [x] Criando uma pasta proprietario (para inclusão/atualização) em src 
  - Criar os arquivos index.tsx e styles.css - vide códigos abaixo
- [x] Criando o conjunto de variáveis que normalmente estão no corpo (body) de inserção (post) com Hook useState
- [x] Criando uma variável (propID) com o Hook useParams que virá de outra página
- [x] Criando constantes para armazenar o "username" e "token"
- [x] Criando um Hook useHistory (acesso de outras instâncias de navegação)
- [ ] Relatando o conceitos de "promises", especialmente async, await, .then()
- [x] Criando a função principal saveOrUpdate
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



## Códigos básicos
### Proprietarios/Index.tsx
```
import { Link } from 'react-router-dom';
import { FiPower, FiEdit, FiTrash2, FiArrowRightCircle, FiArrowLeftCircle } from 'react-icons/fi'
import { Table } from 'react-bootstrap';

import logoImage from '../../assets/images/builder.png'

import './styles.css'



const Proprietarios: React.FC = () => {


  return (
    <div className="proprietario-container">
      <header>
        <img src={logoImage} alt="Gestão de Obras" />
        <span>Bem-vindo, <strong>{"NOME"}</strong>!</span>
        <div className="subheader">
          <button type="button">
            <FiArrowLeftCircle size={18} color="#251FC5" />
          </button>
          <Link className="button" to="/proprietario/0">Novo Proprietario!</Link>
          <button type="button">
            <FiArrowRightCircle size={18} color="#251FC5" />
          </button>
          <button type="button">
            <FiPower size={18} color="#251FC5" />
          </button>
        </div>
      </header>



      <h1>Proprietários Cadastrados</h1>

      <Table striped bordered hover className="text-center">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>E-mail</th>
            <th>CPF</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {
            <tr>

              <td>Valor 1</td>
              <td>Valor 2</td>
              <td>Valor 3</td>
              <td>Valor 4</td>
              <td>
                <button type="button">
                  <FiEdit size={20} color="#251FC5" />
                </button>

                <button type="button">
                  <FiTrash2 size={20} color="#251FC5" />
                </button>
              </td>
            </tr>
          }
        </tbody>
      </Table>
    </div>
  );
}
export default Proprietarios;
```
  
### Proprietarios/Styles.css
```
.proprietario-container {
  width: 100%;
  max-width: 1180px;
  padding: 0 30px;
  margin: 32px auto;
}

.proprietario-container  header{
  display: flex;
  align-items: center;
}

.subheader  {
    margin-left: 260px;
}

.proprietario-container header span{
  font-size: 20px;
  margin-left: 18px;
}

.proprietario-container header span strong{
  color: #251FC5;
}

.proprietario-container header img{
  height: 64px;
}

.proprietario-container header a{
  width: 260px;
  margin-left: 18px;
  margin-top: 0;
}

.proprietario-container header button{
  height: 60px;
  width: 60px;
  border-radius: 4px;
  border: 1px solid #DCDCE6;
  background: transparent;
  margin-left: 16px;
  transition: border-color 0.5s;
}

.proprietario-container header button:hover{
  border-color: #251FC5;
}

.proprietario-container h1 {
  margin-top: 80px;
  margin-bottom: 24px;
}



.proprietario-container tr td{
  background: #ffffff;
  padding: 12px;
  border-radius: 8px;
  position: relative;
}

.proprietario-container tr td button {
  right: 24px;
  top: 24px;
  border: 0;
  background: transparent;  
}

.proprietario-container tr td button + button{
  top: 50px;  
}

.proprietario-container tr td button:hover{
  opacity: 0.8;  
}

.proprietario-container tr td strong{
  display: block;
  margin-bottom: 16px;
  color: #41414D;
}

.proprietario-container  p + strong{
  margin-top: 32px;

}

.proprietario-container   p {
  color: #737380;
  line-height: 21px;
  font-size: 16px;
}

```


### Proprietario/Styles.css
```

import { Link } from 'react-router-dom';
import './styles.css'
import logoImage from '../../assets/images/builder.png'

const Proprietario: React.FC = () => {


  return (
    
    <div className="new-prop-container">
      <div className="content">
        <section className="form">
          <h2>Cadastro de Proprietários</h2>
          <img src={logoImage} alt="Gestão de Obras" />
          <h3> Cadastrar Proprietário</h3>
          <p>Entre com as informações do proprietário e clique em Adicionar! 
             Ou clique em Listar para ver os proprietários cadastrados.
          </p>

        </section>
        <form>
          <input
            placeholder="Nome do Proprietário"
            value={""}
          />
          <input
            placeholder="E-mail do Proprietário"
            value={""}
          />
          <input
            placeholder="CPF do Proprietário"
            value={""}
          />
          <button className="button" type="submit">Adicionar</button>
          <Link to="/proprietarios">
            <button className="button"> Listar</button>
          </Link>
        </form>
      </div>
    </div>
  );
}

export default Proprietario;

```


### Proprietario/Styles.css
```
.new-prop-container {
  width: 100%;
  max-width: 1120px;
  height: 100vh;
  margin: 0 auto;

  display: flex;
  align-items: center;
  justify-content: space-between;
}

.new-prop-container .content {
  width: 100%;
  padding: 100px;
  background: #F0F0F5;
  box-shadow: 0 0 100px rgba(0, 0, 0, 0.1);
  border-radius: 8px;

  display: flex;
  align-items: center;

}

.new-prop-container .content section {
  width: 100%;
  max-width: 380px;
}

.new-prop-container section.form{
  width: 100%;
  max-width: 350px;
  margin-right: 30px;
  height: 50%;
}

.new-prop-container section.form img {
  width: 35%;
  margin-left: auto;
  margin-right: auto;
  display: block;
}

.new-prop-container .content section h2{
  margin: 64px 0 32px;
  font-size: 36px;
  text-align: center;
}
.new-prop-container .content section h3{
  margin: 64px 0 32px;
  font-size: 22px;
  text-align: center;
}

.new-prop-container .content section p {
  font-size: 12px;
  color: #737380;
  line-height: 32px;
  text-align: center;
}

.new-prop-container .content form {
  width: 100%;
  max-width: 450px;
}

.new-prop-container .content form input {
  margin-top: 8px;
}



```

### Assets/css/styles.css
```
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap');

html, body {
    height: 100%;
    font-family: "Roboto", sans-serif;
}

#root {
    display: flex;
    flex-direction: column;
    height: 100%;
}

.footer {
    flex-shrink: 0;
    text-align: center;
}

* {
    margin: 0;
    padding: 0;
    outline: 0;
    box-sizing: border-box;
}

body {
    font: 400 14px Roboto, sans-serif;
    -webkit-font-smoothing: antialiased;
}

input, button, textarea {
    font: 400 18px Roboto, sans-serif;
}

button {
    cursor: pointer;
}

form input {
    width: 100%;
    height: 60px;
    color: #333333;
    border: 1px solid #DCDCE6;
    border-radius: 8px;
    padding: 0 24px;
    margin-top: 16px;
}

.button {
    width: 100%;
    height: 60px;
    background: #0a0a0a;
    border: 0;
    border-radius: 8px;
    color: #FFF;
    font-weight: 700;
    margin-top: 16px;
    display: inline-block;
    text-align: center;
    text-decoration: none;
    font-size: 18px;
    line-height: 60px;
    transition: filter 0.2s;
}

.button:hover {
    filter: brightness(80%);
}

```
  
### Pages/Login/styles.css
```
.login {
  width: 800px;
  height: 75%;
  max-width: 1100px;
  margin: 0 auto;
  padding: 0 0px;
  display: flex;
  align-items: center;
  background-color: #ffffff;
  color: white;
 }

.login-logo img {
  width: 250px;
  height: 250px;
  
}

.login-right { 
  background-color: #beb8b8;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-left: 50px;
  padding: 30px;
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
  color: #49494d;
}

.login-loginInputUser {
  display: flex;
  text-align: center;
  align-items: center;
  color: gray;
  background-color: #1A1A1D;
  border-radius: 5px;
  padding: 3px;
  width: 98%;
  height: 50px;
}

.login-loginInputUser svg {
  margin-left: 10px;
  font-size: 25px;
}

.login-loginInputUser input {
  background: transparent;
  width: 100%;
  outline-width: 0;
  color: #E1E1E6;
  border: none;
  font-size: 18px;
  margin-left: 10px;
  margin-right: 10px;
}

.login-loginInputPassword {
  display: flex;
  align-items: center;
  color: gray;
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
  color: #E1E1E6;
  border: none;
  font-size: 18px;
  margin-left: 12px;
  margin-right: 10px;
   
}

.login-right button {
  width: 98%;
  background-color: #a0a0aa;
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

### Pages/Home/styles.css
```
.conteudo {
  width: 1100px;
  height: 65%;
  max-width: 1100px;
  margin: 0 auto;
  padding: 0 0px;
  display: flex;
  align-items: flex-start;
 }

.contentl {
  max-width: 700px;
  float:left;
}

.contentr {
  max-width: 450px;
  display: flex;
  flex-direction: column;
  float:left;
}

.contentl h1 {
  font-size: 60px;
  color: rgb(61, 23, 23);
}

.contentl p {
  font-size: 24px;
  margin-top: 18px;
  line-height: 34px;
  color: rgb(61, 23, 23);
}

.contentl a {
  width: 100%;
  max-width: 360px;
  height: 72px;
  background: #0f0f0f;
  border-radius: 8px;
  text-decoration: none;
  display: flex;
  align-items: center;
  overflow: hidden;
  margin-top: 40px;
}


.acesso {
  display: block;
  color: #FFF;
  width: 300px;
  height: 20px;
  flex: 1;
  text-align: center;
  align-items: center;
  transition: background-color 0.2s;
}

.acesso a span {
  color: #FFF;
}

.acesso  span {
  align-items: stretch;
  margin-left: 10px;
}
.acesso  a strong {
  flex: 1;
  text-align: center;
  color: #FFF;
  font-size: 25px;
}

.acesso a:hover {
  background: #575858;
}

@media(max-width: 1000px) {
  #conteudo .contentl {
    align-items: center;
    text-align: center;
  }

.contentl main h1 {
    font-size: 42px;
  }

}


```



