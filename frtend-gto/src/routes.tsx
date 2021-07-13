import { Route, BrowserRouter } from 'react-router-dom';
import Home from 'pages/home';
import Login from 'pages/login';
import Proprietario from 'pages/proprietario'

const Routes = () => {
  return (
    <BrowserRouter>
      <Route component={Home} path="/" exact />
      <Route component={Login} path="/login" />
      <Route component={Proprietario} path="/proprietario" />
    </BrowserRouter>
  );
}
export default Routes;