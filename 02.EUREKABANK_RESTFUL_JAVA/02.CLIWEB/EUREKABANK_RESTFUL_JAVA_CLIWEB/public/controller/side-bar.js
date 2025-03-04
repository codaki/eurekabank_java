document.addEventListener("DOMContentLoaded", () => {
    fetchNews();
    fetchExchangeRates();
    document.getElementById('convertButton').addEventListener('click', convertCurrency);
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

async function fetchExchangeRates() {
    try {
        const response = await fetch(`/api/exchange?base=USD`);
        const data = await response.json();
        console.log('Exchange rates data:', data);
        displayExchangeRates(data.rates);
    } catch (error) {
        console.error('Error fetching exchange rates:', error);
    }
}

function displayExchangeRates(rates) {
    const exchangeContainer = document.getElementById('exchangeRatesContainer');
    exchangeContainer.innerHTML = ''; // Clear existing content

    for (const [currency, rate] of Object.entries(rates)) {
        const exchangeElement = document.createElement('div');
        exchangeElement.classList.add('exchange-item');
        exchangeElement.innerHTML = `
            <p>${currency}: ${rate}</p>
        `;
        exchangeContainer.appendChild(exchangeElement);
    }
}

function convertCurrency() {
    const amount = parseFloat(document.getElementById('amountInput').value);
    const selectedCurrency = "USD";
    const exchangeContainer = document.getElementById('exchangeRatesContainer');
    const conversionResult = document.getElementById('conversionResult');
    conversionResult.innerHTML = ''; // Clear existing content

    if (isNaN(amount)) {
        conversionResult.innerHTML = '<p>Please enter a valid amount.</p>';
        return;
    }

    const rates = Array.from(exchangeContainer.children).reduce((acc, item) => {
        const [currency, rate] = item.textContent.split(': ');
        acc[currency] = parseFloat(rate);
        return acc;
    }, {});

    const result = Object.entries(rates).map(([currency, rate]) => {
        return `<p>${amount} ${selectedCurrency} = ${(amount * rate).toFixed(2)} ${currency}</p>`;
    }).join('');

    conversionResult.innerHTML = result;
}