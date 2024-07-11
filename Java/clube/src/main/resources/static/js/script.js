document.addEventListener('DOMContentLoaded', function() {
    fetch('/socio')
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#socioTable tbody');
            tbody.innerHTML = '';
            data.forEach(socio => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${socio.id}</td>
                    <td>${socio.nome}</td>
                    <td>
                        <a href="#" onclick="openEditModal(${socio.id})">Editar</a> |
                        <a href="#" onclick="excluirSocio(${socio.id})">Excluir</a>
                    </td>`;
                tbody.appendChild(tr);
            });
        })
        .catch(error => console.error('Erro ao carregar os sócios:', error));
});

function openEditModal(id) {
    fetch(`/socio/${id}`)
        .then(response => response.json())
        .then(socio => {
            document.getElementById('editId').value = socio.id;
            document.getElementById('editNome').value = socio.nome;
            document.getElementById('editEndereco').value = socio.endereco;
            document.getElementById('editCpf').value = socio.cpf;
            document.getElementById('editTelefone').value = socio.telefone;
            document.getElementById('editEmail').value = socio.email;
            document.getElementById('editModal').style.display = 'block';
        })
        .catch(error => console.error('Erro ao carregar sócio:', error));
}

function excluirSocio(id) {
    if (confirm('Tem certeza que deseja excluir este sócio?')) {
        fetch(`/socio/${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                alert('Erro ao excluir sócio');
            }
        })
        .catch(error => console.error('Erro ao excluir sócio:', error));
    }
}

document.getElementById('editForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const id = document.getElementById('editId').value;
    const socio = {
        id: id,
        nome: document.getElementById('editNome').value,
        endereco: document.getElementById('editEndereco').value,
        cpf: document.getElementById('editCpf').value,
        telefone: document.getElementById('editTelefone').value,
        email: document.getElementById('editEmail').value
    };

    fetch(`/socio/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(socio),
    })
    .then(response => {
        if (response.ok) {
            location.reload();
        } else {
            alert('Erro ao atualizar sócio');
        }
    })
    .catch(error => console.error('Erro ao atualizar sócio:', error));
});

document.getElementById('addForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const socio = {
        nome: document.getElementById('addNome').value,
        endereco: document.getElementById('addEndereco').value,
        cpf: document.getElementById('addCpf').value,
        telefone: document.getElementById('addTelefone').value,
        email: document.getElementById('addEmail').value
    };

    fetch('/socio', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(socio),
    })
    .then(response => {
        if (response.ok) {
            location.reload();
        } else {
            alert('Erro ao adicionar sócio');
        }
    })
    .catch(error => console.error('Erro ao adicionar sócio:', error));
});

document.querySelectorAll('.close').forEach(closeButton => {
    closeButton.addEventListener('click', function() {
        document.getElementById('editModal').style.display = 'none';
        document.getElementById('addModal').style.display = 'none';
    });
});

document.getElementById('addSocioBtn').addEventListener('click', function() {
    document.getElementById('addModal').style.display = 'block';
});
