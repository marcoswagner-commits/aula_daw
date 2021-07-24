
import { useState } from 'react'
import { Link, useHistory, useParams } from 'react-router-dom';
import './styles.css'
import logoImage from '../../assets/images/builder.png'
import Load from '../../assets/images/loading4.gif'
import API from 'services/api';
import { useEffect } from 'react';
import Sidebar from 'components/basics/sidebar';

type titem = {
  codigo_item: number;
  descricao_item: string;
}


const Subitem: React.FC = () => {
  const [codigo_subitem, setCodigo] = useState(null)
  const [descricao_subitem, setDescricao] = useState("")
  const [complemento_subitem, setComplemento] = useState("")
  const [item, setItem] = useState<titem>({codigo_item: 0, descricao_item: ""})
  const [itens, setItens] = useState<titem[]>([]);

  const [load, setLoad] = useState(false)

  const { subitemId } = useParams() as any

  const token = localStorage.getItem('token');

  const history = useHistory();

  async function getItens() {
    try {
      const response = await API.get(`v1/gto/itens`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      setItens([itens, ...response.data._embedded.itemDTOList])
    } catch (error) {
      alert('Erro na busca do item. Tente novamente')
      history.push('/obras');
    }
  }

  async function getSubitem() {
    try {
      const response = await API.get(`v1/gto/subitens/${subitemId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        })

      setCodigo(response.data.codigo_subitem);
      setDescricao(response.data.descricao_subitem);
      setComplemento(response.data.complemento_subitem);
      const _codigo_item = response.data.item.codigo_item;
      const _descricao_item = response.data.item.descricao_item;
      setItem({codigo_item: _codigo_item, descricao_item: _descricao_item});
      
    } catch (error) {
      alert('Erro na busca do subitem. Tente novamente')
      history.push('/subitens');
    }

  }

  useEffect(() => {
    getItens();
    if (subitemId === '0') return;
    else getSubitem();
    // eslint-disable-next-line
  }, [subitemId])

  async function saveOrUpdate(e: any) {
    setLoad(true)
    e.preventDefault();

    const data = {
      codigo_subitem,
      descricao_subitem,
      item,
      complemento_subitem
    };

    try {
      if (subitemId === '0') {
        await API.post('v1/gto/subitens', data, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
      } else {
        data.codigo_subitem = codigo_subitem;
        await API.post('v1/gto/subitens', data, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
      }
      history.push('/subitens');
    } catch (error) {
      const { data } = error.response;
      alert('Erro na inclusão do subitem. ' + data.message + ' Tente novamente!')
      setLoad(false)
    }

  }


  return (
    <div className="container">
      <Sidebar />
      {load &&
        <div className="load-prop">
          <img alt="loading..." src={Load}/>
        </div>
      }
      <div className="new-prop-container">
        <div className="content">
          <section className="form">
            <h2>Cadastro de Itens</h2>
            <img src={logoImage} alt="Gestão de Obras" />
            <h3>{subitemId === '0' ? 'Adicionar' : 'Atualizar'} Subitem</h3>
            <p>Entre com as informações do subitem e clique em Adicionar!
              Ou clique em Listar para ver os subitens cadastrados.
            </p>

          </section>
          <form onSubmit={saveOrUpdate}>
            <input
              placeholder="Descrição do Subitem"
              value={descricao_subitem}
              onChange={e => setDescricao(e.target.value)}
            />
            <select
              value={item.codigo_item}
              onChange={e => setItem({codigo_item: parseInt(e.target.value), descricao_item: ""})} 
            >
              
              {itens.map((item, index) => (
              <option value={item.codigo_item} >
                  {item.descricao_item}
                </option>))}
            </select>
            <input
              placeholder="Complemento do Subitem"
              value={complemento_subitem}
              onChange={e => setComplemento(e.target.value)}
            />
            <button className="button" type="submit">{subitemId === '0' ? 'Adicionar' : 'Atualizar'}</button>
            <Link to="/subitens">
              <button className="button"> Listar</button>
            </Link>
          </form>
        </div>
      </div>
      </div>
      );
}

      export default Subitem;

