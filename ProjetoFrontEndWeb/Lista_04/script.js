// Token da API
const API_TOKEN = 'eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNzQyMzc2MTFiNzNiNGNmYWUyNjZkNGJkYjg2NzFjOCIsIm5iZiI6MTc3NDg5MDA3NS44OTY5OTk4LCJzdWIiOiI2OWNhYWM1YmY1MDQyN2FkMjQxZWQzOTciLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.HiMemRyFtiVO-QJO5mGGAisgj220bpNCyltWHUmD2Dw';

const BASE_URL = 'https://api.themoviedb.org/3';
const IMG_BASE_URL = 'https://image.tmdb.org/t/p/w500';

const options = {
    method: 'GET',
    headers: {
        accept: 'application/json',
        Authorization: `Bearer ${API_TOKEN}` 
    }
};

// 1. Função para buscar e mapear os gêneros (ID -> Nome)
async function fetchGenres() {
    try {
        const response = await fetch(`${BASE_URL}/genre/movie/list?language=pt-BR`, options);
        const data = await response.json();

        // Converte o array de gêneros em um objeto
        const genresMap = {};
        data.genres.forEach(genre => {
            genresMap[genre.id] = genre.name;
        });
        return genresMap;
    } catch (error) {
        console.error('Erro ao buscar gêneros:', error);
        return {};
    }
}

// 2. Função principal para buscar os filmes e renderizar na tela
async function getPopularMovies() {
    try {
        // Buscar o mapa de gêneros primeiro
        const genresMap = await fetchGenres();

        // Buscar os filmes mais populares
        const response = await fetch(`${BASE_URL}/movie/popular?language=pt-BR&page=1`, options);
        const data = await response.json();

        // Pega apenas os 10 primeiros filmes do array retornado
        const top10Movies = data.results.slice(0, 10);

        renderMovies(top10Movies, genresMap);
    } catch (error) {
        console.error('Erro ao buscar os filmes', error);
        document.getElementById('movie-grid').innerHTML = '<p>Erro ao carregar os filmes.</p>';
    }
}

// 3. Função para desenhar o HTML no DOM
function renderMovies(movies, genresMap) {
    const grid = document.getElementById('movie-grid');
    grid.innerHTML = ''; // Limpa o loading ou conteúdo anterior

    movies.forEach(movie => {
        // Converte os IDs dos gêneros deste filme em uma string com os nomes separados
        const genreNames = movie.genre_ids.map(id => genresMap[id]).filter(name => name).join(', ');

        // Formata a data (de YYYY-MM-DD para DD/MM/YYYY)
        const releaseDate = new Date(movie.release_date).toLocaleDateString('pt-BR');

        // Cria o elemento do cartão
        const card = document.createElement('div');
        card.className = 'movie-card';

        // 5 Informações: Imagem, Título, Gêneros, Data, Nota
        card.innerHTML = `
            <img class="movie-poster" src="${IMG_BASE_URL}${movie.poster_path}" alt="Pôster do filme ${movie.title}">
            <div class="movie-info">
                <h2 class="movie-title" title="${movie.title}">${movie.title}</h2>
                <p class="movie-genres">${genreNames}</p>
                <div class="movie-stats">
                    <span>📅 Data de Lançamento: ${releaseDate}</span>
                    <span class="rating">⭐ ${movie.vote_average.toFixed(1)}</span>
                </div>
            </div>
        `;

        // Imagem de Fallback
        const posterUrl = movie.poster_path ? `${IMG_BASE_URL}${movie.poster_path}` : 'img/image_not_found.png';

        grid.appendChild(card);
    });
}

// Inicia o processo quando a página carrega
getPopularMovies();
