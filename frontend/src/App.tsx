import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';
import LoginPage from "./LoginPage";
import SecuredPage from "./SecuredPage";

function App() {

  const [username, setUsername] = useState<String>("")

  return (
    <>
      <LoginPage/>
      <SecuredPage/>
    </>
  );
}

export default App;
