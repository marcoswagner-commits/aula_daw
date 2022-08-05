# Aula 26 - Desenvolvimento de Aplicações WEB

> Aula 29/11/2022 
> 
>   Estudo de caso: Gestão de Obras - Front-End


## Atividades da aula - roteiro

## :+1: Implementação do Modelo Conceitual Gestão de Obras - Construção do Front-End - React.Js (Vinculação do Dashboard à API - Outros contextos e conclusão)

[Projeto do Front-End](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/5d19019b2d7f1c8e8bf26b6a55df8f575392101c/documentos/frontend_projeto.png)

### Passo 1: Vinculando o Dashboard à API
- [x] Vinculando o Gráfico de Barras
  - Criar uma interface para total de itens (itemDescricao e total) - vide Json Postman
  - Criar um useState para totalitens
  - Criar um useState para ChartData
  - Criar um vínculo com o token para validar a consulta
  - Criar um useEffect para busca de itens
  - Vincular o gráfico com os dados retornados
- [x] Vinculando o Gráfico de Rosca
  - Repetir os passos do Gráfico de Barras
- [x] Vinculando a Tabela de Dados
  - Repetir os passos dos gráficos, alterando o vínculo para a tabela 

### Passo 2: Ajustes e melhorias
- [x] Criando mensagens nos cadastros/listagens
- [ ] Usando imagens para carregamentos de páginas login/cadastros/listagens

#### loading index.tsx
```
<div className="load">
    Loading...
</div>
```

#### loading styles.css
```
.load {
  position:absolute;
  top:35%;
  left:20%;
  color:blue;
  
}

.load img {
  width:200px;
  height:200px;
}
```



### Passo 3: Criando o contexto de Obras
- [x] Replicando o contexto de proprietários em obras
- [x] Criando um "select" em obras para buscar proprietários
- [x] Criando os outros contextos (itens, subitens, lançamentos)

### Passo 4: Conclusão
- [x] Outras possibilidades


[![Aulas no Youtube](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/cb3e2ea9547f9ddc831277f07919c3e78451eb92/yt-icon.png)](https://www.youtube.com/channel/UCfO-aJxKLqau0TnL0AfNAvA)
####  Os vídeos abaixo mostram a execução destes dois primeiros passos

🥇:[![material complementar aula26](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=_FDCRhibEUo)

-
🥈:[![material complementar aula26](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=R0vykfteMb0)

-
🥉:[![material complementar aula26](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=DaYhaAYiH1k)

-
🥉:[![material complementar aula26](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/91eb8207965740a8e341b626b250e6869e4d43ad/documentos/Capa_aula_front.png)](https://www.youtube.com/watch?v=hR47EQ3wDIE)



## Códigos finais
### Obra/Index.tsx
```

import { useState } from 'react'
import { Link, useHistory, useParams } from 'react-router-dom';
import './styles.css'
import logoImage from '../../assets/images/builder.png'
import Load from '../../assets/images/loading4.gif'
import API from 'services/api';
import { useEffect } from 'react';
import Sidebar from 'components/basics/sidebar';

type tprops = {
  codigo_prop: number;
  nome_prop: string;
}



const Obra: React.FC = () => {
  const [codigo_obra, setCodigo] = useState(null);
  const [descricao_obra, setDescricao] = useState("");
  const [localizacao_obra, setLocalizacao] = useState("");
  const [complemento_obra, setComplemento] = useState("");
  const [proprietario, setProprietario] = useState<tprops>({codigo_prop: 0, nome_prop: ""})
  const [proprietarios, setProprietarios] = useState<tprops[]>([]);
  const [load, setLoad] = useState(false)

  const { obraId } = useParams() as any

  const token = localStorage.getItem('token');

  const history = useHistory();

  async function getProprietarios() {
    try {
      const response = await API.get(`v1/gto/proprietarios`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      setProprietarios(response.data._embedded.proprietarioDTOList)
    } catch (error) {
      alert('Erro na busca do proprietário. Tente novamente')
      history.push('/obras');
    }
  }

  async function getObra() {
    try {
      const response = await API.get(`v1/gto/obras/${obraId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        })

      setCodigo(response.data.codigo_obra);
      setDescricao(response.data.descricao_obra);
      setLocalizacao(response.data.localizacao_obra);
      setComplemento(response.data.complemento_obra);
      const _codigo_prop = response.data.proprietario.codigo_prop;
      const _nome_prop = response.data.proprietario.nome_prop
      setProprietario({codigo_prop: _codigo_prop, nome_prop: _nome_prop});
      
    } catch (error) {
      alert('Erro na busca da obra. Tente novamente')
      history.push('/obras');
    }

  }


  useEffect(() => {
    getProprietarios()
    if (obraId === '0') return;
    else getObra()
  }, [obraId])

  async function saveOrUpdate(e: any) {
    setLoad(true)
    e.preventDefault();

    const data = {
      codigo_obra,
      proprietario,
      descricao_obra,
      localizacao_obra,
      complemento_obra
    };
    console.log(data)
    try {
      if (obraId === '0') {
        await API.post('v1/gto/obras', data, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
      } else {
        data.codigo_obra = codigo_obra;
        await API.post('v1/gto/obras', data, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
      }
      history.push('/obras');
    } catch (error) {
      const { data } = error.response;
      alert('Erro na inclusão da obra. ' + data.message + ' Tente novamente!')
      setLoad(false)
    }

  }


  return (
    <div className="container">
      <Sidebar />
      {load &&
        <div className="load-prop">
          <img alt="Loading" src={Load}/>
        </div>
      }
      <div className="new-prop-container">
        <div className="content">
          <section className="form">
            <h2>Cadastro de Obras</h2>
            <img src={logoImage} alt="Gestão de Obras" />
            <h3>{obraId === '0' ? 'Adicionar' : 'Atualizar'} Obra</h3>
            <p>Entre com as informações do obra e clique em Adicionar!
              Ou clique em Listar para ver os obras cadastrados.
            </p>

          </section>
          <form onSubmit={saveOrUpdate}>
            <input
              placeholder="Descrição da Obra"
              value={descricao_obra}
              onChange={e => setDescricao(e.target.value)}
            />
            <select
              value={proprietario.codigo_prop}
              onChange={e => setProprietario({codigo_prop: parseInt(e.target.value), nome_prop: ""})} 
            >
              {proprietarios.map((item, index) => (
                <option value={item.codigo_prop}>
                  {item.nome_prop}
                </option>))}
            </select>

            <input
              placeholder="Localização da Obra"
              value={localizacao_obra}
              onChange={e => setLocalizacao(e.target.value)}
            />
            <input
              placeholder="Complemento da Obra"
              value={complemento_obra}
              onChange={e => setComplemento(e.target.value)}
            />
            <button className="button" type="submit">{obraId === '0' ? 'Adicionar' : 'Atualizar'}</button>
            <Link to="/obras">
              <button className="button"> Listar</button>
            </Link>
          </form>
        </div>
      </div>
      </div>
      );
}

      export default Obra;


```
  
### Obra/Styles.css
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

.new-prop-container .content form select {
  width: 100%;
  height: 50px;
  max-width: 450px;
  margin-top: 8px;
  font-size: 18px;
  padding: 15px;
}


.load-prop {
  position:absolute;
  top:15%;
  left:55%;
  color:blue;
  
}

.load-prop img {
  width:100px;
  height:100px;
}







```

### Obras/Index.tsx
```
import { useEffect, useState } from 'react'
import { Link, useHistory } from 'react-router-dom';
import { FiPower, FiEdit, FiTrash2, FiArrowRightCircle, FiArrowLeftCircle } from 'react-icons/fi'
import { Table } from 'react-bootstrap';

import logoImage from '../../assets/images/builder.png'

import './styles.css'
import API from 'services/api';
import Sidebar from 'components/basics/sidebar';

type tprops = {
  codigo_prop: number;
  nome_prop: string;
}

interface iObras {
  codigo_obra: number;
  proprietario: tprops
  descricao_obra: string;
  localizacao_obra: string;
  complemento_obra: string;
}

const Obras: React.FC = () => {

  const [Obras, setObras] = useState<iObras[]>([]);
  const [page, setPage] = useState(0);

  const username = localStorage.getItem('username')
  const token = localStorage.getItem('token')

  const history = useHistory();

  async function logout() {
    localStorage.clear();
    history.push('/')
  }

  async function editObra(id: any) {
    try {
      history.push(`obra/${id}`)
    } catch (error) {
      const { data } = error.response;
      alert('Erro na edição da Obra. ' + data.message + 'Tente novamente')
    }

  }

  async function deleteObra(id: any) {
    try {
      await API.delete(`v1/gto/obras/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
      )
      alert('Exclusão com sucesso')
      setPage(0)
      loadObras();
    } catch (error) {
      const { data } = error.response;
      alert('Erro na exclusão da Obra. ' + data.message + 'Tente novamente')
    }
  }

  async function loadObras() {
    const response = await API.get('v1/gto/obras', {
      headers: {
        Authorization: `Bearer ${token}`
      },
      params: {
        page: page,
        limit: 4,
        direction: 'asc'
      }
    });

    setObras(response.data._embedded.obraDTOList)
  }

  useEffect(() => {
    loadObras()
  }, [page]);


  return (
    <div className="container">
      <Sidebar />
      <div className="Obra-container">
        <header>
          <img src={logoImage} alt="Gestão de Obras" />
          <span>Bem-vindo, <strong>{username?.toUpperCase()}</strong>!</span>
          <div className="subheader">
            <button onClick={() => { setPage(page - 1)}} type="button">
              <FiArrowLeftCircle size={18} color="#251FC5" />
            </button>
            <Link className="button" to="/obra/0">Nova Obra!</Link>
            <button onClick={() => { setPage(page + 1)}} type="button">
              <FiArrowRightCircle size={18} color="#251FC5" />
            </button>
            <button onClick={logout} type="button">
              <FiPower size={18} color="#251FC5" />
            </button>
          </div>
        </header>



        <h1>Obras Cadastrados</h1>
        {console.log(Obras)}
        <Table striped bordered hover className="text-center">
          <thead>
            <tr>
              <th>ID</th>
              <th>Proprietário</th>
              <th>Descrição</th>
              <th>Localização</th>
              <th>Complemento</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {
              Obras.map(p => (
                <tr key={p.codigo_obra}>
                  <td>{p.codigo_obra}</td>
                  <td>{p.proprietario.codigo_prop}-{p.proprietario.nome_prop}</td>
                  <td>{p.descricao_obra}</td>
                  <td>{p.localizacao_obra}</td>
                  <td>{p.complemento_obra}</td>
                  <td>
                    <button onClick={() => editObra(p.codigo_obra)} type="button">
                      <FiEdit size={20} color="#251FC5" />
                    </button>

                    <button onClick={() => deleteObra(p.codigo_obra)} type="button">
                      <FiTrash2 size={20} color="#251FC5" />
                    </button>
                  </td>
                </tr>
              ))

            }
          </tbody>
        </Table>
      </div>
    </div>
  );
}
export default Obras;

```
  
### Obras/Styles.css
```
.Obra-container {
  width: 100%;
  max-width: 1180px;
  padding: 0 30px;
  margin: 32px auto;
}

.Obra-container  header{
  display: flex;
  align-items: center;
}

.subheader  {
    margin-left: 200px;
}

.Obra-container header span{
  font-size: 20px;
  margin-left: 18px;
}

.Obra-container header span strong{
  color: #251FC5;
}

.Obra-container header img{
  height: 64px;
}

.Obra-container header a{
  width: 260px;
  margin-left: 18px;
  margin-top: 0;
}

.Obra-container header button{
  height: 60px;
  width: 60px;
  border-radius: 4px;
  border: 1px solid #DCDCE6;
  background: transparent;
  margin-left: 16px;
  transition: border-color 0.5s;
}

.Obra-container header button:hover{
  border-color: #251FC5;
}

.Obra-container h1 {
  margin-top: 80px;
  margin-bottom: 24px;
}



.Obra-container tr td{
  background: #ffffff;
  padding: 12px;
  border-radius: 8px;
  position: relative;
}

.Obra-container tr td button {
  right: 24px;
  top: 24px;
  border: 0;
  background: transparent;  
}

.Obra-container tr td button + button{
  top: 50px;  
}

.Obra-container tr td button:hover{
  opacity: 0.8;  
}

.Obra-container tr td strong{
  display: block;
  margin-bottom: 16px;
  color: #41414D;
}

.Obra-container  p + strong{
  margin-top: 32px;

}

.Obra-container   p {
  color: #737380;
  line-height: 21px;
  font-size: 16px;
}



```
  



