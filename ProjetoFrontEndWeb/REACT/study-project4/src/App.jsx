import { BrowserRouter, Routes, Route } from "react-router"; 
import Home from "./Home.jsx";
import Contatos from "./Contatos";
import Menu from "./Menu.jsx";

function App() {
  return (
    <BrowserRouter>
    <Menu/>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/contatos" element={<Contatos />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;