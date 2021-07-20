import { useEffect, useState } from 'react'
import { Link, useHistory } from 'react-router-dom';
import { FiPower, FiEdit, FiTrash2, FiArrowRightCircle, FiArrowLeftCircle } from 'react-icons/fi'
import { Table } from 'react-bootstrap';

import logoImage from '../../assets/images/builder.png'

import './styles.css'
import API from 'services/api';
import Sidebar from 'components/basics/sidebar';

interface iProprietarios {
  codigo_prop: number;
  nome_prop: string;
  email_prop: string;
  cpf_prop: string;
}

const Proprietarios: React.FC = () => {

  const [proprietarios, setProprietarios] = useState<iProprietarios[]>([]);
  const [page, setPage] = useState(0);

  const username = localStorage.getItem('username')
  const token = localStorage.getItem('token')

  const history = useHistory();

  async function logout() {
    localStorage.clear();
    history.push('/')
  }

  async function editProprietario(id: any) {
    try {
      history.push(`proprietario/${id}`)
    } catch (error) {
      const { data } = error.response;
      alert('Erro na edição do proprietário. ' + data.message + 'Tente novamente')
    }

  }

  async function deleteProprietario(id: any) {
    try {
      await API.delete(`v1/gto/proprietarios/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
      )
      alert('Exclusão com sucesso')
      loadProprietarios();
    } catch (error) {
      const { data } = error.response;
      alert('Erro na exclusão do proprietário. ' + data.message + 'Tente novamente')
    }
  }

  async function loadProprietarios() {
    const response = await API.get('v1/gto/proprietarios', {
      headers: {
        Authorization: `Bearer ${token}`
      },
      params: {
        page: page,
        limit: 4,
        direction: 'asc'
      }
    });

    setProprietarios(response.data._embedded.proprietarioDTOList)
  }

  useEffect(() => {
    loadProprietarios()
  }, [page]);


  return (
    <div className="container">
      <Sidebar />
      <div className="proprietario-container">
        <header>
          <img src={logoImage} alt="Gestão de Obras" />
          <span>Bem-vindo, <strong>{username?.toUpperCase()}</strong>!</span>
          <div className="subheader">
            <button onClick={() => { setPage(page - 1); loadProprietarios() }} type="button">
              <FiArrowLeftCircle size={18} color="#251FC5" />
            </button>
            <Link className="button" to="/proprietario/0">Novo Proprietario!</Link>
            <button onClick={() => { setPage(page + 1); loadProprietarios() }} type="button">
              <FiArrowRightCircle size={18} color="#251FC5" />
            </button>
            <button onClick={logout} type="button">
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
              proprietarios.map(p => (
                <tr key={p.codigo_prop}>
                  <td>{p.codigo_prop}</td>
                  <td>{p.nome_prop}</td>
                  <td>{p.email_prop}</td>
                  <td>{p.cpf_prop}</td>
                  <td>
                    <button onClick={() => editProprietario(p.codigo_prop)} type="button">
                      <FiEdit size={20} color="#251FC5" />
                    </button>

                    <button onClick={() => deleteProprietario(p.codigo_prop)} type="button">
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
      export default Proprietarios;
