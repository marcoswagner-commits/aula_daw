import { useState } from "react";


interface BodyProps {
  msg: string;
  msg_esp?: string;
}

const Body: React.FC<BodyProps> = (props) => {
  const [contador, setContador] = useState(1);
  const [nome, setNome] = useState('');
  
  function buttonClick1() {
    setContador(contador + 1)
    console.log(contador)
  }

  function buttonClick2() {
    setNome("Fulano de Tal")
    console.log(contador)
  }
  
  return (
    <>
    <h1>{ props.msg} </h1>
    <h2>{ contador }</h2>
    <button onClick={buttonClick1}>ATUALIZAR</button>
    <button onClick={buttonClick2}>NOMEAR</button>
    <h3> {nome} </h3>
    
    </>
  )
}
export default Body;



