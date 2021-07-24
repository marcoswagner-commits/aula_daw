import axios from 'axios'

const API = axios.create( {
  baseURL: 'https://gestao-obra-daw.herokuapp.com/'
});
export default API;