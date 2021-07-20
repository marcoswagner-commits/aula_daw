import { useEffect, useState } from 'react'
import API from 'services/api';

interface ITotalItens {
  itemDescricao: string;
  total: number;
}
const DashTable: React.FC = () => {

  const [totalitens, setTotalItens] = useState<ITotalItens[]>([]);


  const token = localStorage.getItem('token')

  useEffect(() => {
    API.get('v1/gto/lancamentos/total-por-item', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }).then(response => {
      setTotalItens(response.data)
    })
  }, [token, totalitens]);

  return (
    <div className="table-responsive">
      <table className="table table-striped table-sm">
        <thead>
          <tr>
            <th>Descrição do Item</th>
            <th>Valor</th>
          </tr>
        </thead>
        <tbody>
          {
            totalitens.map(obj => (
              <tr key={obj.itemDescricao}>
                <td>{obj.itemDescricao}</td>
                <td>{obj.total}</td>
              </tr>
            ))
          }

        </tbody>
      </table>
    </div>


  );
}

export default DashTable;