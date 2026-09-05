import React, { useState } from 'react';
import emailjs from '@emailjs/browser'; 
import style from './Formulario.module.css';

function Formulario() {
    const [dadosFormulario, setDadosFormulario] = useState({
        nome: "",
        email: "",
        mensagem: ""
    });

    const [msgErro, setMsgErro] = useState("");

    function lidarComMudanca(e) {
        const nomeDoCampo = e.target.name;
        const valorDigitado = e.target.value;

        setDadosFormulario({
            ...dadosFormulario,
            [nomeDoCampo]: valorDigitado
        });
    }

    function validar(e) {
        e.preventDefault();

        if (
            dadosFormulario.nome.trim() === "" ||
            dadosFormulario.email.trim() === "" ||
            dadosFormulario.mensagem.trim() === ""
        ) {
            setMsgErro("Todos os campos devem ser preenchidos.");
        } else {
            const templateParams = {
                from_name: dadosFormulario.nome, 
                from_email: dadosFormulario.email,
                from_msg: dadosFormulario.mensagem
            };

            emailjs.send('service_g5vcfnc', 'template_7hrdpgs', templateParams, 'fZuTrzICKo-6JRpmK')
                .then(
                    (response) => {
                        setMsgErro("Email enviado com sucesso!");
                        
                        // Opcional, mas recomendado: Limpar os campos após o sucesso
                        setDadosFormulario({ nome: "", email: "", mensagem: "" });
                    },
                    (error) => {
                        console.error("Erro do EmailJS:", error); 
                        setMsgErro("Erro ao enviar o e-mail. Tente novamente.");
                    }
                );
        }
    }

    function limparErro() {
        setMsgErro("");
    }

    return (
        <form className={style.formulario} onSubmit={validar}>
            <label>Nome:</label>
            <input
                type="text"
                name="nome"
                onChange={lidarComMudanca}
                value={dadosFormulario.nome}
                onFocus={limparErro}
            />

            <label>E-mail:</label>
            <input
                type="text"
                name="email"
                onChange={lidarComMudanca}
                value={dadosFormulario.email}
                onFocus={limparErro}
            />

            <label>Mensagem:</label>
            <input
                type="text"
                name="mensagem"
                onChange={lidarComMudanca}
                value={dadosFormulario.mensagem}
                onFocus={limparErro}
            />

            <button type="submit">Enviar</button>

            <p>{msgErro}</p>
        </form>
    );
}

export default Formulario;