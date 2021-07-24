import { useEffect, useState } from 'react'
import { Link, useHistory } from 'react-router-dom';
import { FiPower, FiEdit, FiTrash2, FiArrowRightCircle, FiArrowLeftCircle } from 'react-icons/fi'
import { Table } from 'react-bootstrap';

import logoImage from '../../assets/images/builder.png'

import './styles.css'
import API from 'services/api';
import Sidebar from 'components/basics/sidebar';

type titem = {
  codigo_item: number;
  descricao_item: string;
}

interface iSubitens {
  codigo_subitem: number;
  item: titem;
  descricao_subitem: string;
  complemento_subitem: string;
}

const Subitens: React.FC = () => {

  const [itens, setSubitens] = useState<iSubitens[]>([]);
  const [page, setPage] = useState(0);

  const username = localStorage.getItem('username')
  const token = localStorage.getItem('token')

  const history = useHistory();

  async function logout() {
    localStorage.clear();
    history.push('/')
  }

  async function editSubitem(id: any) {
    try {
      history.push(`subitem/${id}`)
    } catch (error) {
      const { data } = error.response;
      alert('Erro na edição do subitem. ' + data.message + 'Tente novamente')
    }

  }

  async function deleteSubitem(id: any) {
    try {
      await API.delete(`v1/gto/subitens/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
      )
      alert('Exclusão com sucesso')
      loadSubitens();
    } catch (error) {
      const { data } = error.response;
      alert('Erro na exclusão do subitem. ' + data.message + 'Tente novamente')
    }
  }

  async function loadSubitens() {
    const response = await API.get('v1/gto/subitens', {
      headers: {
        Authorization: `Bearer ${token}`
      },
      params: {
        page: page,
        limit: 4,
        direction: 'asc'
      }
    });

    setSubitens(response.data._embedded.subItemDTOList)
  }

  useEffect(() => {
    loadSubitens()
    // eslint-disable-next-line
  }, [page]);


  return (
    <div className="container">
      <Sidebar />
      <div className="subitem-container">
        <header>
          <img src={logoImage} alt="Gestão de Obras" />
          <span>Bem-vindo, <strong>{username?.toUpperCase()}</strong>!</span>
          <div className="subheader">
            <button onClick={() => { setPage(page - 1); loadSubitens() }} type="button">
              <FiArrowLeftCircle size={18} color="#251FC5" />
            </button>
            <Link className="button" to="/subitem/0">Novo Subitem!</Link>
            <button onClick={() => { setPage(page + 1); loadSubitens() }} type="button">
              <FiArrowRightCircle size={18} color="#251FC5" />
            </button>
            <button onClick={logout} type="button">
              <FiPower size={18} color="#251FC5" />
            </button>
          </div>
        </header>



        <h1>Subitens Cadastrados</h1>

        <Table striped bordered hover className="text-center">
          <thead>
            <tr>
              <th>ID</th>
              <th>Descrição</th>
              <th>Item</th>
              <th>Complemento</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {
              itens.map(p => (
                <tr key={p.codigo_subitem}>
                  <td>{p.codigo_subitem}</td>
                  <td>{p.descricao_subitem}</td>
                  <td>{p.item.codigo_item}-{p.item.descricao_item}</td>
                  <td>{p.complemento_subitem}</td>
                  <td>
                    <button onClick={() => editSubitem(p.codigo_subitem)} type="button">
                      <FiEdit size={20} color="#251FC5" />
                    </button>

                    <button onClick={() => deleteSubitem(p.codigo_subitem)} type="button">
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
      export default Subitens;
