const routeId = document.getElementById('routeId').value;
const form = document.getElementById('commentForm');

form.addEventListener('submit', handleSubmit);

async function handleSubmit(event) {
    event.preventDefault();

    const message = document.getElementById('message').value;

    const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').textContent;
    const csrfToken = document.head.querySelector('[name=_csrf]').textContent;

    try {
        const response = await fetch(`http://localhost:8080/api/${routeId}/comments`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accepts': 'application/json',
                csrfHeaderName: csrfToken
            },
            body: JSON.stringify({
                message
            })
        });

        if (response.ok != true) {
            const error = await response.json();
            throw new Error(error.message);
        }

        const objResponse = await response.json();

        event.target.reset();
    } catch (ex) {
        console.log(ex.message);
        throw ex;
    }
}