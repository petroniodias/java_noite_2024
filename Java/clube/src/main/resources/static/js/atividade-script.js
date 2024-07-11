document.addEventListener('DOMContentLoaded', function() {
    fetch('/atividade')
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#atividadeTable tbody');
            tbody.innerHTML = '';
            data.forEach(atividade => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${atividade.id}</td>
                    <td>${atividade.nome}</td>
                    <td>${atividade.descricao}</td>
                    <td>
                        <a href="#" onclick="editarAtividade(${atividade.id})">Editar</a> |
                        <a href="#" onclick="excluirAtividade(${atividade.id})">Excluir</a>
                    </td>`;
                tbody.appendChild(tr);
            });
        })
        .catch(error => console.error('Erro ao carregar as atividades:', error));
});

function excluirAtividade(id) {
    if (confirm('Tem certeza que deseja excluir esta atividade?')) {
        fetch(`/atividade/${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                alert('Erro ao excluir atividade');
            }
        })
        .catch(error => console.error('Erro ao excluir atividade:', error));
    }
}

document.getElementById('addForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const atividade = {
        nome: document.getElementById('addNome').value,
        descricao: document.getElementById('addDescricao').value
    };

    fetch('/atividade', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(atividade),
    })
    .then(response => {
        if (response.ok) {
            location.reload();
        } else {
            alert('Erro ao adicionar atividade');
        }
    })
    .catch(error => console.error('Erro ao adicionar atividade:', error));
});

document.querySelector('.close').addEventListener('click', function() {
    document.getElementById('addModal').style.display = 'none';
});

document.getElementById('addAtividadeBtn').addEventListener('click', function() {
    document.getElementById('addModal').style.display = 'block';
});
