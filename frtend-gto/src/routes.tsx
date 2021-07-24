import { Route, BrowserRouter, Switch } from 'react-router-dom';
import Home from 'pages/home';
import Login from 'pages/login';
import Props from 'pages/proprietarios'
import Prop from 'pages/proprietario';
import Dashboard from 'pages/dashboard';
import Obras from 'pages/obras';
import Obra from 'pages/obra';
import Itens from 'pages/itens';
import Item from 'pages/item';
import Subitens from 'pages/subitens';
import Subitem from 'pages/subitem';
import Lancamentos from 'pages/lancamentos';
import Lancamento from 'pages/lancamento';


const Routes = () => {
  return (
    <BrowserRouter>
      <Switch>
        <Route component={Home} path="/" exact />
        <Route component={Login} path="/login" />
        <Route component={Dashboard} path="/dashboard" />
        <Route component={Props} path="/proprietarios" />
        <Route component={Prop} path="/proprietario/:propId" />
        <Route component={Obras} path="/obras" />
        <Route component={Obra} path="/obra/:obraId" />
        <Route component={Itens} path="/itens" />
        <Route component={Item} path="/item/:itemId" />
        <Route component={Subitens} path="/subitens" />
        <Route component={Subitem} path="/subitem/:subitemId" />
        <Route component={Lancamentos} path="/lancamentos" />
        <Route component={Lancamento} path="/lancamento/:lancId" />
      </Switch>
    </BrowserRouter>
  );
}
export default Routes;

