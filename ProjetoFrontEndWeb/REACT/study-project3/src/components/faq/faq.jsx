import { useEffect, useState } from "react";

function Faq() {

    const [nome, setNome] = useState("");

    useEffect(() => {
        console.log(Math.random());
    }, []);

    function mudarNome(e) {
        setNome(e.target.value);
    }

    return ( 
        <section>
            <form action="">
                <label htmlFor="">Nome</label>
                <input type="text" name='' id="" onChange={mudarNome}/>
                <input type="button" value="Testar" />
            </form>
            <p>{nome}</p>
        </section>
     );
}

export default Faq;