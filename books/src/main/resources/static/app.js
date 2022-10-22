let btn = document.getElementById('loadBooks');

btn.addEventListener('click', onLoadBooks);

function onLoadBooks(event) {
    let options = {
        method: 'GET'
    };

    let authorsContainer = document.getElementById('authors-container');
    authorsContainer.innerHTML = '';

    let url = 'http://localhost:8080/api/books';
    fetch(url, options)
        .then(response => response.json())
        .then(json => json.forEach(book => {
            let row = document.createElement('tr');

            let titleCol = document.createElement('td');
            let authorCol = document.createElement('td');
            let isbnCol = document.createElement('td');
            let actionCol = document.createElement('td');

            titleCol.textContent = book.title;
            authorCol.textContent = book.author.name;
            isbnCol.textContent = book.isbn;

            row.appendChild(titleCol);
            row.appendChild(authorCol);
            row.appendChild(isbnCol);
            row.appendChild(actionCol);

            authorsContainer.appendChild(row);
        }))
        .catch(error => console.log(error));
}