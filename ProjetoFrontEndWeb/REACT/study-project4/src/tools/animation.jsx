import ScrollReveal from "scrollreveal";

export function animation() {
    
    // Animação padrão (de baixo para cima)
    ScrollReveal().reveal('.efeito-sr', {
        duration: 1000,
        distance: '50px',
        origin: 'bottom',
        reset: true,
        delay: 200
    });

    ScrollReveal().reveal('.efeito-sr-esquerda', {
        duration: 1000,
        distance: '80px',
        origin: 'left', // Vem da esquerda
        reset: true,
    });
}