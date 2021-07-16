import NavBar from 'components/basics/navbar';
import Footer from 'components/basics/footer';
import { Link } from 'react-router-dom';
import { FiLogIn } from 'react-icons/fi';


import './styles.css';

import logo from '../../assets/images/builder.png';

const Home: React.FC = () => {
  return (
    <>
      <div className="header">
        <NavBar />
      </div>
      <div className="conteudo">
        <div className="contentl">
          <h1>Sistema de Gestão de Obras</h1>
          <p>Controle de gastos de obras de construção civil com classificação de lançamentos e filtros.</p>
          <div className="acesso">
          <Link to="/login">
            <span>
            <FiLogIn size={32} color="#111111" />
            </span>
            <strong>Acessar o sistema!</strong>
          </Link>
          </div>
        </div>
        <div className="contentr">
          <img src={logo} alt="Gestão de Obras" />
        </div>
      </div>
      <div className="footer">
        <Footer />
      </div>
    </>
  );
}

export default Home;

