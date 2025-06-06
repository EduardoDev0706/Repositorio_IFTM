document.addEventListener('DOMContentLoaded', function()
{
    const backgroundMusic = document.getElementById('background-music');

    if (backgroundMusic) {
        backgroundMusic.volume = 0.5;
        backgroundMusic.muted = false;
    }
});

