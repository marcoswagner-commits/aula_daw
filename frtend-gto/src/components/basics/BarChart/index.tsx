import { useEffect, useState } from 'react';
import Chart from 'react-apexcharts'
import API from 'services/api';

interface ITotalItens {
  itemDescricao: string;
  total: number;
}

type SeriesData = {
  name: string;
  data: number[];
}

type ChartData = {
  labels: {
    categories: string[]
  },
  series: SeriesData[]
}


const BarChart: React.FC = () => {


  const [totalitens, setTotalItens] = useState<ITotalItens[]>([]);
  const [chartdata, setChartData] = useState<ChartData>({
    labels: {
      categories: []
    },
    series: [
      {
        name: "",
        data: []
      }
    ]
  })

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
        labels: {
          categories: mlabels
        },
        series: [
          {
            name: "Total de lançamentos",
            data: mseries
          }
        ]
      })
    })
  },[token, totalitens]);

  const options = {
    plotOptions: {
      bar: {
        horizontal: true,
      }
    },
  };

  
  return (

    <Chart
      options={{ ...options, xaxis: chartdata.labels }}
      series={chartdata.series}
      type="bar"
      height="220" />

  );
}

export default BarChart;