import { useEffect, useState } from 'react'
import { Link, useHistory } from 'react-router-dom';
import { FiPower, FiEdit, FiTrash2, FiArrowRightCircle, FiArrowLeftCircle } from 'react-icons/fi'
import { Table } from 'react-bootstrap';

import logoImage from '../../assets/images/builder.png'

import './styles.css'
import API from 'services/api';
import Sidebar from 'components/basics/sidebar';

interface iItens {
  codigo_item: number;
  descricao_item: string;
  complemento_item: string;
}

const Itens: React.FC = () => {

  const [itens, setItens] = useState<iItens[]>([]);
  const [page, setPage] = useState(0);

  const username = localStorage.getItem('username')
  const token = localStorage.getItem('token')

  const history = useHistory();

  async function logout() {
    localStorage.clear();
    history.push('/')
  }

  async function editItem(id: any) {
    try {
      history.push(`item/${id}`)
    } catch (error) {
      const { data } = error.response;
      alert('Erro na edição do item. ' + data.message + 'Tente novamente')
    }

  }

  async function deleteItem(id: any) {
    try {
      await API.delete(`v1/gto/itens/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
      )
      alert('Exclusão com sucesso')
      loadItens();
    } catch (error) {
      const { data } = error.response;
      alert('Erro na exclusão do item. ' + data.message + 'Tente novamente')
    }
  }

  async function loadItens() {
    const response = await API.get('v1/gto/itens', {
      headers: {
        Authorization: `Bearer ${token}`
      },
      params: {
        page: page,
        limit: 4,
        direction: 'asc'
      }
    });

    setItens(response.data._embedded.itemDTOList)
  }

  useEffect(() => {
    loadItens()
  }, [page]);


  return (
    <div className="container">
      <Sidebar />
      <div className="item-container">
        <header>
          <img src={logoImage} alt="Gestão de Obras" />
          <span>Bem-vindo, <strong>{username?.toUpperCase()}</strong>!</span>
          <div className="subheader">
            <button onClick={() => { setPage(page - 1); loadItens() }} type="button">
              <FiArrowLeftCircle size={18} color="#251FC5" />
            </button>
            <Link className="button" to="/item/0">Novo Item!</Link>
            <button onClick={() => { setPage(page + 1); loadItens() }} type="button">
              <FiArrowRightCircle size={18} color="#251FC5" />
            </button>
            <button onClick={logout} type="button">
              <FiPower size={18} color="#251FC5" />
            </button>
          </div>
        </header>



        <h1>Itens Cadastrados</h1>

        <Table striped bordered hover className="text-center">
          <thead>
            <tr>
              <th>ID</th>
              <th>Descrição</th>
              <th>Complemento</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {
              itens.map(p => (
                <tr key={p.codigo_item}>
                  <td>{p.codigo_item}</td>
                  <td>{p.descricao_item}</td>
                  <td>{p.complemento_item}</td>
                  <td>
                    <button onClick={() => editItem(p.codigo_item)} type="button">
                      <FiEdit size={20} color="#251FC5" />
                    </button>

                    <button onClick={() => deleteItem(p.codigo_item)} type="button">
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
      export default Itens;
