document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('produto-form');
    const produtoList = document.getElementById('produto-list');
    const apiUrl = 'http://localhost:8080/produtos';

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const id = document.getElementById('produto-id').value;
        const descricao = document.getElementById('descricao').value;
        const unidade = document.getElementById('unidade').value;
        const preco = parseFloat(document.getElementById('preco').value);

        const produto = {
            id: id ? parseInt(id) : null,
            descricao,
            unidade,
            preco
        };

        if (id) {
            // Update product
            fetch(apiUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(produto)
            }).then(response => response.json())
              .then(updatedProduto => {
                  document.querySelector(`[data-id='${updatedProduto.id}']`).remove();
                  addProdutoToDOM(updatedProduto);
                  form.reset();
              });
        } else {
            // Add new product
            fetch(apiUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(produto)
            }).then(response => response.json())
              .then(newProduto => {
                  addProdutoToDOM(newProduto);
                  form.reset();
              });
        }
    });

    function loadProdutos() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                data.forEach(produto => {
                    addProdutoToDOM(produto);
                });
            });
    }

    function addProdutoToDOM(produto) {
        const li = document.createElement('li');
        li.className = 'list-item';
        li.setAttribute('data-id', produto.id);

        const div = document.createElement('div');
        div.textContent = `${produto.descricao} - ${produto.unidade} - R$ ${produto.preco.toFixed(2)}`;

        const editButton = document.createElement('button');
        editButton.className = 'edit-btn';
        editButton.textContent = 'Editar';
        editButton.onclick = () => editProduto(produto.id);

        const deleteButton = document.createElement('button');
        deleteButton.className = 'delete-btn';
        deleteButton.textContent = 'Excluir';
        deleteButton.onclick = () => deleteProduto(produto.id);

        li.appendChild(div);
        li.appendChild(editButton);
        li.appendChild(deleteButton);

        produtoList.appendChild(li);
    }

    function editProduto(id) {
        fetch(`${apiUrl}/${id}`)
            .then(response => response.json())
            .then(produto => {
                document.getElementById('produto-id').value = produto.id;
                document.getElementById('descricao').value = produto.descricao;
                document.getElementById('unidade').value = produto.unidade;
                document.getElementById('preco').value = produto.preco;
            });
    }

    function deleteProduto(id) {
        fetch(apiUrl, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id })
        }).then(() => {
            document.querySelector(`[data-id='${id}']`).remove();
        });
    }

    loadProdutos();
});
