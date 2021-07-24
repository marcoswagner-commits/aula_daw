
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
      setProprietarios([proprietarios, ...response.data._embedded.proprietarioDTOList])
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
    // eslint-disable-next-line
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

