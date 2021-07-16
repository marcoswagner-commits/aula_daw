import { useState } from 'react'
import { useHistory } from 'react-router-dom';
import NavBar from 'components/basics/navbar';
import '../login/styles.css'
import Lock from '../../assets/images/padlock.png'
import api from 'services/api';

import { MdLock, MdForum } from "react-icons/md"
import { HiEye, HiEyeOff } from "react-icons/hi"

function Login() {
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")

  const history = useHistory();

  async function login(e: any) {
    e.preventDefault();

    const data = {
      username,
      password,
    }

    try {
      const response = await api.post('auth/login', data);

      localStorage.setItem('username', username);
      localStorage.setItem('token', response.data.token)

      history.push('/dashboard')

    } catch (error) {
      alert('Autenticação sem sucesso, tente novamente!');
    }

  }

  const [show, setShow] = useState(false)

  const handleClick = (e: any) => {
    e.preventDefault()
    setShow(!show);
  }

  return (
    <>
      <div className="header">
        <NavBar />

      </div>
      <div className="login">
        <div className="login-logo">
          <img
            src={Lock}
            alt="MdLockLogin App"
          />
        </div>
        <form onSubmit={login}>
          <div className="login-right">
            <h1>A U T E N T I C A Ç Ã O</h1>

            <div className="login-loginInputUser">
              <MdForum />
              <input
                type="user"
                placeholder="Digite um usuário"
                value={username}
                onChange={e => setUsername(e.target.value)}
              />
            </div>

            <div className="login-loginInputPassword">
              <MdLock />
              <input
                placeholder="Digite sua senha"
                type={show ? "text" : "password"}
                value={password}
                onChange={e => setPassword(e.target.value)}
              />
              <div className="login-eye">
                {show ? (
                  <HiEye
                    size={20}
                    onClick={handleClick}
                  />
                ) : (
                  <HiEyeOff
                    size={20}
                    onClick={handleClick}
                  />
                )}
              </div>
            </div>

            <button type="submit">
              E N T R A R
            </button>

          </div>
        </form>
      </div>
    </>
  )
}

export default Login
