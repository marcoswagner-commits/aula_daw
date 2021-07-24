
import { useState } from 'react'
import { Link, useHistory, useParams } from 'react-router-dom';
import './styles.css'
import logoImage from '../../assets/images/builder.png'
import Load from '../../assets/images/loading4.gif'
import API from 'services/api';
import { useEffect } from 'react';
import Sidebar from 'components/basics/sidebar';

type tobra = {
  codigo_obra: number;
  descricao_obra: string;
}

type titem = {
  codigo_item: number;
  descricao_item: string;
}

type tsubitem = {
  codigo_subitem: number;
  descricao_subitem: string;
}


const Lancamento: React.FC = () => {
  const [codigo_lanc, setCodigo] = useState(null);
  const [descricao_lanc, setDescricao] = useState("");
  const [valor_lanc, setValor] = useState("");
  const [observacoes_lanc, setObservacoes] = useState("");

  const [obra, setObra] = useState<tobra>({codigo_obra: 0, descricao_obra: ""})
  const [obras, setObras] = useState<tobra[]>([]);

  const [item, setItem] = useState<titem>({codigo_item: 0, descricao_item: ""})
  const [itens, setItens] = useState<titem[]>([]);

  const [subitem, setSubitem] = useState<tsubitem>({codigo_subitem: 0, descricao_subitem: ""})
  const [subitens, setSubitens] = useState<tsubitem[]>([]);

  const [load, setLoad] = useState(false)

  const { lancId } = useParams() as any

  const token = localStorage.getItem('token');

  const history = useHistory();

  async function getObras() {
    try {
      const response = await API.get(`v1/gto/obras`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      setObras([obras, ...response.data._embedded.obraDTOList])
    } catch (error) {
      alert('Erro na busca de obras. Tente novamente')
      history.push('/lancamentos');
    }
  }

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
      alert('Erro na busca de itens. Tente novamente')
      history.push('/lancamentos');
    }
  }

  async function getSubitens() {
    try {
      const response = await API.get(`v1/gto/subitens`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      setSubitens([subitens, ...response.data._embedded.subItemDTOList])
    } catch (error) {
      alert('Erro na busca de subitens. Tente novamente')
      history.push('/lancamentos');
    }
  }

  async function getLancamento() {
    try {
      const response = await API.get(`v1/gto/lancamentos/${lancId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        })

      setCodigo(response.data.codigo_lanc);
      setDescricao(response.data.descricao_lanc);
      setValor(response.data.valor_lanc);
      setObservacoes(response.data.observacoes_lanc);
      
      const _codigo_obra = response.data.obra.codigo_obra;
      const _descricao_obra = response.data.obra.descricao_obra;
      
      setObra({codigo_obra: _codigo_obra, descricao_obra: _descricao_obra});

      const _codigo_item = response.data.item.codigo_item;
      const _descricao_item = response.data.item.descricao_item;
      
      setItem({codigo_item: _codigo_item, descricao_item: _descricao_item});
      
      const _codigo_subitem = response.data.subitem.codigo_subitem;
      const _descricao_subitem = response.data.subitem.descricao_subitem;
      
      setSubitem({codigo_subitem: _codigo_subitem, descricao_subitem: _descricao_subitem});
            
      
    } catch (error) {
      alert('Erro na busca do lançamento. Tente novamente')
      history.push('/lancamentos');
    }

  }


  useEffect(() => {
    getObras();
    getItens();
    getSubitens();
    if (lancId === '0') return;
    else getLancamento()
  }, [lancId])

  async function saveOrUpdate(e: any) {
    setLoad(true)
    e.preventDefault();

    const data = {
      codigo_lanc,
      obra,
      item,
      subitem,
      descricao_lanc,
      valor_lanc,
      observacoes_lanc
    };
    console.log(data)
    try {
      if (lancId === '0') {
        await API.post('v1/gto/lancamentos', data, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
      } else {
        data.codigo_lanc = codigo_lanc;
        await API.post('v1/gto/lancamentos', data, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
      }
      history.push('/lancamentos');
    } catch (error) {
      const { data } = error.response;
      alert('Erro na inclusão do lançamento. ' + data.message + ' Tente novamente!')
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
            <h2>Cadastro de Lancamentos</h2>
            <img src={logoImage} alt="Gestão de Obras" />
            <h3>{lancId === '0' ? 'Adicionar' : 'Atualizar'} Lancamento</h3>
            <p>Entre com as informações do lançamento e clique em Adicionar!
              Ou clique em Listar para ver os obras cadastrados.
            </p>

          </section>
          <form onSubmit={saveOrUpdate}>
            <input
              placeholder="Descrição do Lancamento"
              value={descricao_lanc}
              onChange={e => setDescricao(e.target.value)}
            />
            <select
              value={obra.codigo_obra}
              onChange={e => setObra({codigo_obra: parseInt(e.target.value), descricao_obra: ""})} 
            >
              {obras.map((item, index) => (
                <option value={item.codigo_obra}>
                  {item.descricao_obra}
                </option>))}
            </select>

            <select
              value={item.codigo_item}
              onChange={e => setItem({codigo_item: parseInt(e.target.value), descricao_item: ""})} 
            >
              {itens.map((it, index) => (
                <option value={it.codigo_item}>
                  {it.descricao_item}
                </option>))}
            </select>

            <select
              value={subitem.codigo_subitem}
              onChange={e => setSubitem({codigo_subitem: parseInt(e.target.value), descricao_subitem: ""})} 
            >
              {subitens.map((it, index) => (
                <option value={it.codigo_subitem}>
                  {it.descricao_subitem}
                </option>))}
            </select>

            <input
              placeholder="Valor do Lançamento"
              value={valor_lanc}
              onChange={e => setValor(e.target.value)}
            />
            <input
              placeholder="Observações do Lancamento"
              value={observacoes_lanc}
              onChange={e => setObservacoes(e.target.value)}
            />
            <button className="button" type="submit">{lancId === '0' ? 'Adicionar' : 'Atualizar'}</button>
            <Link to="/obras">
              <button className="button"> Listar</button>
            </Link>
          </form>
        </div>
      </div>
      </div>
      );
}

      export default Lancamento;

