
import { useState } from 'react'
import { Link, useHistory, useParams } from 'react-router-dom';
import './styles.css'
import logoImage from '../../assets/images/builder.png'
import Load from '../../assets/images/loading4.gif'
import API from 'services/api';
import { useEffect } from 'react';
import Sidebar from 'components/basics/sidebar';

const Proprietario: React.FC = () => {
  const [codigo_prop, setCodigo] = useState(null)
  const [nome_prop, setNome] = useState("")
  const [email_prop, setEmail] = useState("")
  const [cpf_prop, setCpf] = useState("")
  const [load, setLoad] = useState(false)

  const { propId } = useParams() as any

  const token = localStorage.getItem('token');

  const history = useHistory();

  async function getProprietario() {
    try {
      const response = await API.get(`v1/gto/proprietarios/${propId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        })

      setCodigo(response.data.codigo_prop);
      setNome(response.data.nome_prop);
      setEmail(response.data.email_prop);
      setCpf(response.data.cpf_prop);
    } catch (error) {
      alert('Erro na busca do proprietário. Tente novamente')
      history.push('/proprietarios');
    }

  }


  useEffect(() => {
    if (propId === '0') return;
    else getProprietario()
  }, [propId])

  async function saveOrUpdate(e: any) {
    setLoad(true)
    e.preventDefault();

    const data = {
      codigo_prop,
      nome_prop,
      email_prop,
      cpf_prop
    };

    try {
      if (propId === '0') {
        await API.post('v1/gto/proprietarios', data, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
      } else {
        data.codigo_prop = codigo_prop;
        await API.post('v1/gto/proprietarios', data, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
      }
      history.push('/proprietarios');
    } catch (error) {
      const { data } = error.response;
      alert('Erro na inclusão do proprietário. ' + data.message + ' Tente novamente!')
      setLoad(false)
    }

  }


  return (
    <div className="container">
      <Sidebar />
      {load &&
        <div className="load-prop">
          <img src={Load}/>
        </div>
      }
      <div className="new-prop-container">
        <div className="content">
          <section className="form">
            <h2>Cadastro de Proprietários</h2>
            <img src={logoImage} alt="Gestão de Obras" />
            <h3>{propId === '0' ? 'Adicionar' : 'Atualizar'} Proprietário</h3>
            <p>Entre com as informações do proprietário e clique em Adicionar!
              Ou clique em Listar para ver os proprietários cadastrados.
            </p>

          </section>
          <form onSubmit={saveOrUpdate}>
            <input
              placeholder="Nome do Proprietário"
              value={nome_prop}
              onChange={e => setNome(e.target.value)}
            />
            <input
              placeholder="E-mail do Proprietário"
              value={email_prop}
              onChange={e => setEmail(e.target.value)}
            />
            <input
              placeholder="CPF do Proprietário"
              value={cpf_prop}
              onChange={e => setCpf(e.target.value)}
            />
            <button className="button" type="submit">{propId === '0' ? 'Adicionar' : 'Atualizar'}</button>
            <Link to="/proprietarios">
              <button className="button"> Listar</button>
            </Link>
          </form>
        </div>
      </div>
      </div>
      );
}

      export default Proprietario;

