import { useEffect } from "react";
import { animation } from "./tools/animation";

function Home() {

    useEffect(() => {
        animation(); // Executa a função quando a tela carregar
    }, []);

    return ( 
        <>
        <h1>Título</h1>
        <ul>
            <li><a href="#sub3">Subtitulo 3</a></li>
            <li><a href="#sub10">Subtitulo 10</a></li>
        </ul>

        <h2 className="efeito-sr">Subtítulo 1</h2>
        <p className="efeito-sr">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem nulla optio facere sequi.</p>
        <h2 className="efeito-sr">Subtítulo 2</h2>
        <p className="efeito-sr">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem nulla optio facere sequi.</p>
        <h2 id="sub3" className="efeito-sr">Subtítulo 3</h2>
        <p className="efeito-sr">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem nulla optio facere sequi.</p>
        <h2 className="efeito-sr">Subtítulo 4</h2>
        <p className="efeito-sr">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem nulla optio facere sequi.</p>
        <h2 className="efeito-sr">Subtítulo 5</h2>
        <p className="efeito-sr">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem nulla optio facere sequi.</p>
        <h2 className="efeito-sr">Subtítulo 6</h2>
        <p className="efeito-sr">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem nulla optio facere sequi.</p>
        <h2 className="efeito-sr-esquerda">Subtítulo 7</h2>
        <p className="efeito-sr-esquerda">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem nulla optio facere sequi.</p>
        <h2 className="efeito-sr-esquerda">Subtítulo 8</h2>
        <p className="efeito-sr-esquerda">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem nulla optio facere sequi.</p>
        <h2 className="efeito-sr-esquerda">Subtítulo 9</h2>
        <p className="efeito-sr-esquerda">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem nulla optio facere sequi.</p>
        <h2 id="sub10" className="efeito-sr-esquerda">Subtítulo 10</h2>
        <p className="efeito-sr-esquerda">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem nulla optio facere sequi.</p>
        <h2 className="efeito-sr-esquerda">Subtítulo 11</h2>
        <p className="efeito-sr-esquerda">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem nulla optio facere sequi.</p>
        <h2 className="efeito-sr-esquerda">Subtítulo 12</h2>
        <p className="efeito-sr-esquerda">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem nulla optio facere sequi.</p>

        </>
     );
}

export default Home;