import axios from 'axios'

const API = axios.create( {
  baseURL: 'http://gestao-obra-daw.herokuapp.com/'
});
export default API;