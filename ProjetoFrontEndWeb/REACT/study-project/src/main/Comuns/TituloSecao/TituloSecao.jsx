import style from "./TituloSecao.module.css";

function TituloSecao({subtitulo = "", descricao = ""}) {
    return (
        <div className={style.tituloSecao}>
            <h2 className={style.subtitulo}>{subtitulo.toUpperCase()}</h2> 
            <p className={style.paragrafo}>{descricao}</p> 
        </div>
    );
}

export default TituloSecao;