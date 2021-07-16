

const DashTable: React.FC = () => {
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
          <tr>
            <td>Mão de Obra</td>
            <td>1500.00</td>
          </tr>
          <tr>
            <td>Material de Acabamento</td>
            <td>2500.00</td>
          </tr>
          <tr>
            <td>Material Básico</td>
            <td>3500.00</td>
          </tr>
        </tbody>
      </table>
    </div>


  );
}

export default DashTable;