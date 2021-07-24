
import { useState } from 'react'
import { Link, useHistory, useParams } from 'react-router-dom';
import './styles.css'
import logoImage from '../../assets/images/builder.png'
import Load from '../../assets/images/loading4.gif'
import API from 'services/api';
import { useEffect } from 'react';
import Sidebar from 'components/basics/sidebar';

const Item: React.FC = () => {
  const [codigo_item, setCodigo] = useState(null)
  const [descricao_item, setDescricao] = useState("")
  const [complemento_item, setComplemento] = useState("")
 const [load, setLoad] = useState(false)

  const { itemId } = useParams() as any

  const token = localStorage.getItem('token');

  const history = useHistory();

  async function getItem() {
    try {
      const response = await API.get(`v1/gto/itens/${itemId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        })

      setCodigo(response.data.codigo_item);
      setDescricao(response.data.descricao_item);
      setComplemento(response.data.complemento_item);
      
    } catch (error) {
      alert('Erro na busca do item. Tente novamente')
      history.push('/itens');
    }

  }


  useEffect(() => {
    if (itemId === '0') return;
    else getItem()
  }, [itemId])

  async function saveOrUpdate(e: any) {
    setLoad(true)
    e.preventDefault();

    const data = {
      codigo_item,
      descricao_item,
      complemento_item
    };

    try {
      if (itemId === '0') {
        await API.post('v1/gto/itens', data, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
      } else {
        data.codigo_item = codigo_item;
        await API.post('v1/gto/itens', data, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
      }
      history.push('/itens');
    } catch (error) {
      const { data } = error.response;
      alert('Erro na inclusão do item. ' + data.message + ' Tente novamente!')
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
            <h2>Cadastro de Itens</h2>
            <img src={logoImage} alt="Gestão de Obras" />
            <h3>{itemId === '0' ? 'Adicionar' : 'Atualizar'} Item</h3>
            <p>Entre com as informações do item e clique em Adicionar!
              Ou clique em Listar para ver os itens cadastrados.
            </p>

          </section>
          <form onSubmit={saveOrUpdate}>
            <input
              placeholder="Descrição do Item"
              value={descricao_item}
              onChange={e => setDescricao(e.target.value)}
            />
            <input
              placeholder="Complemento do Item"
              value={complemento_item}
              onChange={e => setComplemento(e.target.value)}
            />
            <button className="button" type="submit">{itemId === '0' ? 'Adicionar' : 'Atualizar'}</button>
            <Link to="/itens">
              <button className="button"> Listar</button>
            </Link>
          </form>
        </div>
      </div>
      </div>
      );
}

      export default Item;

