import NavBar from 'components/basics/navbar';
import Footer from 'components/basics/footer';
import { FiLogIn } from 'react-icons/fi';


import './styles.css';
import logo from '../../assets/images/builder.png';

const Home: React.FC = () => {
  return (
    <>
      <div id="header">
        <NavBar />
      </div>
      <div id="conteudo">
        <div id="contentl">
          <h1>Sistema de Gestão de Obras</h1>
          <p>Controle de gastos de obras de construção civil com classificação de lançamentos e filtros.</p>
          <a href="/autentica">
            <span>
              <FiLogIn />
            </span>
            <strong>Acessar o sistema!</strong>
          </a>
        </div>
        <div id="contentr">
          <img src={logo} alt="Gestão de Obras" />
        </div>
      </div>
      <div id="footer">
        <Footer />
      </div>
    </>
  );
}

export default Home;

