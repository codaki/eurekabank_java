document.addEventListener("DOMContentLoaded", () => {
    fetchNews();
});

var imagen = "https://i.pinimg.com/736x/26/91/f2/2691f2fa1a0f078f5f274edf7fea6763.jpg";

async function fetchNews() {
    try {
        const response = await fetch('/api/news');
        const data = await response.json();
        console.log('News data:', data);
        if (data.results) {
            displayNews(data.results);
        } else {
            console.error('No news results found:', data);
            displayNews(null);
        }

    } catch (error) {
        console.error('Error fetching news:', error);
    }
}

function displayNews(newsItems) {
    const newsContainer = document.getElementById('news');
    newsContainer.innerHTML = ''; // Clear existing content

    if (newsItems === null) {
        const newsElement = document.createElement('div');
        newsElement.classList.add('news-item-error');
        newsElement.innerHTML = `
            <p>No hay noticias disponibles</p>
            <p>Inténtalo de nuevo más tarde</p>
        `;
        newsContainer.appendChild(newsElement);
        return;
    }
    newsItems.forEach(item => {
        const newsElement = document.createElement('div');
        newsElement.classList.add('news-item');
        newsElement.innerHTML = `
            <div class="img-container">
                <img src="${item.image != "" ? item.image : imagen}" alt="${item.title}">
            </div>
            <div class="content">
                <h3>${item.title}</h3>
                <p>${item.description}</p>
                <a href="${item.href}" target="_blank">Leer más</a>
            </div>
        `;
        newsContainer.appendChild(newsElement);
    });
}