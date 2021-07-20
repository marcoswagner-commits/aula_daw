import { Route, BrowserRouter, Switch } from 'react-router-dom';
import Home from 'pages/home';
import Login from 'pages/login';
import Props from 'pages/proprietarios'
import Prop from 'pages/proprietario';
import Dashboard from 'pages/dashboard';
import Obras from 'pages/obras';
import Obra from 'pages/obra';


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
      </Switch>
    </BrowserRouter>
  );
}
export default Routes;

