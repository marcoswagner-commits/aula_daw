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

type tobras = {
  codigo_obra: number;
  descricao_obra: string;
  proprietario: tprops;
}

type titem = {
  codigo_item: number;
  descricao_item: string;
}

type tsubitem = {
  codigo_subitem: number;
  descricao_subitem: string;
}

interface iLancamentos {
  codigo_lanc: number;
  obra: tobras;
  item: titem;
  subitem: tsubitem;
  valor_lanc: number;
  descricao_lanc: string;
  observacoes_lanc: string;
}

const Lancamentos: React.FC = () => {

  const [Lancamentos, setLancamentos] = useState<iLancamentos[]>([]);
  const [page, setPage] = useState(0);

  const username = localStorage.getItem('username')
  const token = localStorage.getItem('token')

  const history = useHistory();

  async function logout() {
    localStorage.clear();
    history.push('/')
  }

  async function editLancamento(id: any) {
    try {
      history.push(`lancamento/${id}`)
    } catch (error) {
      const { data } = error.response;
      alert('Erro na edição do Lançamento. ' + data.message + 'Tente novamente')
    }

  }

  async function deleteLancamento(id: any) {
    try {
      await API.delete(`v1/gto/lancamento/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
      )
      alert('Exclusão com sucesso')
      setPage(0)
      loadLancamentos();
    } catch (error) {
      const { data } = error.response;
      alert('Erro na exclusão do Lancamento. ' + data.message + 'Tente novamente')
    }
  }

  async function loadLancamentos() {
    const response = await API.get('v1/gto/lancamentos', {
      headers: {
        Authorization: `Bearer ${token}`
      },
      params: {
        page: page,
        limit: 4,
        direction: 'asc'
      }
    });

    setLancamentos(response.data._embedded.lancamentoDTOList)
  }

  useEffect(() => {
    loadLancamentos()
    // eslint-disable-next-line
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
            <Link className="button" to="/lancamento/0">Novo Lançamento!</Link>
            <button onClick={() => { setPage(page + 1)}} type="button">
              <FiArrowRightCircle size={18} color="#251FC5" />
            </button>
            <button onClick={logout} type="button">
              <FiPower size={18} color="#251FC5" />
            </button>
          </div>
        </header>



        <h1>Lancamentos Cadastrados</h1>
        {console.log(Lancamentos)}
        <Table striped bordered hover className="text-center">
          <thead>
            <tr>
              <th>ID</th>
              <th>Obra</th>
              <th>Proprietario</th>
              <th>Item</th>
              <th>Subitem</th>
              <th>Valor</th>
              <th>Descrição</th>
              <th>Observações</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {
              Lancamentos.map(p => (
                <tr key={p.codigo_lanc}>
                  <td>{p.codigo_lanc}</td>
                  <td>{p.obra.codigo_obra}-{p.obra.descricao_obra}</td>
                  <td>{p.obra.proprietario.codigo_prop}-{p.obra.proprietario.nome_prop}</td>
                  <td>{p.item.codigo_item}-{p.item.descricao_item}</td>
                  <td>{p.subitem.codigo_subitem}-{p.subitem.descricao_subitem}</td>
                  <td>{Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'}).format(p.valor_lanc)}</td>
                  <td>{p.descricao_lanc}</td>
                  <td>{p.observacoes_lanc}</td>
                <td>
                    <button onClick={() => editLancamento(p.codigo_lanc)} type="button">
                      <FiEdit size={20} color="#251FC5" />
                    </button>

                    <button onClick={() => deleteLancamento(p.codigo_lanc)} type="button">
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
export default Lancamentos;
