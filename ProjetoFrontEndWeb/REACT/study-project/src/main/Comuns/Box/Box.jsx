import style from './Box.module.css';

function Box({texto}) {
    return (
        <div className={style.box}>
            <p>{texto}</p>
        </div>
    );
}

export default Box;