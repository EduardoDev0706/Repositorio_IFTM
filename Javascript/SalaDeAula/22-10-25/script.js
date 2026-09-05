window.addEventListener("DOMContentLoaded", function () {

    let btnPlay = document.getElementById("btnPlay");
    let btnPause = document.getElementById("btnPause");
    let btnStop = document.getElementById("btnStop");
    let segundosInterface = document.getElementById("segundos");
    let minutosInterface = document.getElementById("minutos");

    let minutos = 0;
    let segundos = 0;
    let idCronometro = null;

    function formatar(valor) {
        return String(valor).padStart(2, '0');
    }

    function zerarCronometro() {
        minutos = 0;
        segundos = 0;
        minutosInterface.innerHTML = formatar(minutos);
        segundosInterface.innerHTML = formatar(segundos);
    }

    function play() {
        segundos++;

        if (segundos === 60) {
            minutos++;
            minutosInterface.innerHTML = formatar(minutos);
            segundos = 0;
        }
        segundosInterface.innerHTML = formatar(segundos);
    }

    function start() {
        if (idCronometro !== null) return;

        idCronometro = setInterval(play, 1000);

        btnPlay.disabled = true;
        btnPause.disabled = false;
        btnStop.disabled = false;
    }

    function pause() {
        if (idCronometro === null) return;

        clearInterval(idCronometro);
        idCronometro = null;

        btnPlay.disabled = false;
        btnPause.disabled = true;
    }

    function stop() {
        clearInterval(idCronometro);
        idCronometro = null;
        zerarCronometro();

        btnPlay.disabled = false;
        btnPause.disabled = true;
        btnStop.disabled = true;
    }

    btnPlay.addEventListener("click", start);
    btnPause.addEventListener("click", pause);
    btnStop.addEventListener("click", stop);

    function main() {
        zerarCronometro();
        btnPause.disabled = true;
        btnStop.disabled = true;
    }

    main();
});

