import { useEffect, useState } from 'react';
import Chart from 'react-apexcharts'
import API from 'services/api';

interface ITotalItens {
  itemDescricao: string;
  total: number;
}

type ChartData = {
  series: number[];
  labels: string[];
  
}

const DonutChart: React.FC = () => {
  
  const [totalitens, setTotalItens] = useState<ITotalItens[]>([]);
  const [chartdata, setChartData] = useState<ChartData>({series: [], labels: []})

  const token = localStorage.getItem('token')

  useEffect(() => {
    API.get('v1/gto/lancamentos/total-por-item', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }).then(response => {
      setTotalItens(response.data)
      const mlabels = totalitens.map(obj => obj.itemDescricao)
      const mseries = totalitens.map(obj => obj.total)
      setChartData({
        series: mseries,
        labels: mlabels
      })
    })
  },[token, totalitens]);

  
    const options = {
    legend: {
      show: true
    }
  }



  return (
    <Chart
      options={{ ...options, labels: chartdata.labels }}
      series={chartdata.series}
      type="donut"
      height="220" />
  );
}

export default DonutChart;