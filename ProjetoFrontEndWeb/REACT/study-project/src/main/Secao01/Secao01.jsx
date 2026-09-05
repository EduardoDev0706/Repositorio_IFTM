import TituloSecao from "../Comuns/TituloSecao/TituloSecao";
import Depoimentos from "./Depoimentos/Depoimentos";

function Secao01() {
    return(
        <section>
            <TituloSecao subtitulo="Subtitulo 1" descricao="Sou a descrição do subtitulo 1"/>
            {/* <Depoimentos/> */}
            <Depoimentos/>
        </section>
    );
}


export default Secao01;