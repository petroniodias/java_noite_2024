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
                        <a href="#" onclick="openEditModal(${atividade.id})">Editar</a> |
                        <a href="#" onclick="excluirAtividade(${atividade.id})">Excluir</a>
                    </td>`;
                tbody.appendChild(tr);
            });
        })
        .catch(error => console.error('Erro ao carregar as atividades:', error));
});

function openEditModal(id) {
    fetch(`/atividade/${id}`)
        .then(response => response.json())
        .then(atividade => {
            document.getElementById('editId').value = atividade.id;
            document.getElementById('editNome').value = atividade.nome;
            document.getElementById('editDescricao').value = atividade.descricao;
            document.getElementById('editModal').style.display = 'block';
        })
        .catch(error => console.error('Erro ao carregar atividade:', error));
}

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

document.getElementById('editForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const id = document.getElementById('editId').value;
    const atividade = {
        id: id,
        nome: document.getElementById('editNome').value,
        descricao: document.getElementById('editDescricao').value
    };

    fetch(`/atividade/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(atividade),
    })
    .then(response => {
        if (response.ok) {
            location.reload();
        } else {
            alert('Erro ao atualizar atividade');
        }
    })
    .catch(error => console.error('Erro ao atualizar atividade:', error));
});

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

document.querySelectorAll('.close').forEach(closeButton => {
    closeButton.addEventListener('click', function() {
        document.getElementById('editModal').style.display = 'none';
        document.getElementById('addModal').style.display = 'none';
    });
});

document.getElementById('addAtividadeBtn').addEventListener('click', function() {
    document.getElementById('addModal').style.display = 'block';
});