import Chart from 'react-apexcharts'
const BarChart: React.FC = () => {
  
  const options = {
    plotOptions: {
        bar: {
            horizontal: true,
        }
    },
};

const mockData = {
    labels: {
        categories: ['Material de Acabamento', 'Material de Pintura', 'Mão-de-Obra', 'Material Básico']
    },
    series: [
        {
            name: "% Sucesso",
            data: [43.6, 67.1, 67.7, 45.6, 71.1]                   
        }
    ]
};

  return (

    <Chart 
      options={{...options, xaxis: mockData.labels}}
      series={mockData.series}
      type="bar"
      height="220" />

  );
}

export default BarChart;