
import * as ReactDOM from 'react-dom'
import './index.css'
import './components/basics/Header'
import Header from './components/basics/Header'
import Body from './components/basics/Body'
import Menu from './components/basics/Menu'



const tag = <h1>Gestão de Obras</h1>
ReactDOM.render(
  <>
    { tag }
    <Header />
    <Body msg="Você possui duas obras em andamento" />
    <Menu>
      <ul>
        <li>Proprietario</li>
        <li>Obra</li>
        <li>Orçamento</li>
      </ul>
    </Menu>
    
  </>,
  document.getElementById('root')
)