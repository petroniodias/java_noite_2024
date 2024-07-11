document.addEventListener('DOMContentLoaded', function() {
    fetch('/socio')
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#socioTable tbody');
            tbody.innerHTML = '';
            data.forEach(socio => {
                const tr = document.createElement('tr');
                tr.innerHTML = `<td>${socio.id}</td><td>${socio.nome}</td>`;
                tbody.appendChild(tr);
            });
        })
        .catch(error => console.error('Erro ao carregar os sócios:', error));
});
