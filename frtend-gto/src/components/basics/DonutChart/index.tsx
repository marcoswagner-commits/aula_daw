import Chart from 'react-apexcharts'
const DonutChart: React.FC = () => {
  const mockData = {
    series: [477138, 499928, 444867, 220426, 473088],
    labels: ['Material de Acabamento', 'Material de Pintura', 'Mão-de-Obra', 'Material Básico']
  }

  const options = {
    legend: {
      show: true
    }
  }



  return (
    <Chart
      options={{ ...options, labels: mockData.labels }}
      series={mockData.series}
      type="donut"
      height="220" />
  );
}

export default DonutChart;