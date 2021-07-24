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
