$(document).ready(function() {
    const form = $("#produto-form");
    const produtoList = $("#produto-list");

    // Fetch and display products on page load
    fetch('http://localhost:8080/produtos')
        .then(response => response.json())
        .then(data => {
            data.forEach(produto => {
                addProdutoToDOM(produto);
            });
        });

    form.on("submit", function(event) {
        event.preventDefault();

        const id = $("#produto-id").val();
        const descricao = $("#descricao").val();
        const unidade = $("#unidade").val();
        const preco = $("#preco").val();

        const produto = {
            id: id ? parseInt(id) : null,
            descricao,
            unidade,
            preco: parseFloat(preco)
        };

        if (id) {
            // Update product
            fetch('http://localhost:8080/produtos', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(produto)
            }).then(response => response.json())
              .then(updatedProduto => {
                  $(`[data-id='${updatedProduto.id}']`).remove();
                  addProdutoToDOM(updatedProduto);
                  form[0].reset();
              });
        } else {
            // Add new product
            fetch('http://localhost:8080/produtos', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(produto)
            }).then(response => response.json())
              .then(newProduto => {
                  addProdutoToDOM(newProduto);
                  form[0].reset();
              });
        }
    });

    function addProdutoToDOM(produto) {
        const li = $(`
            <li class="list-group-item" data-id="${produto.id}">
                ${produto.descricao} - ${produto.unidade} - R$ ${produto.preco.toFixed(2)}
                <div>
                    <button class="btn btn-warning btn-sm" onclick="editProduto(${produto.id})">Editar</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteProduto(${produto.id})">Excluir</button>
                </div>
            </li>
        `);
        produtoList.append(li);
    }

    window.editProduto = function(id) {
        fetch(`http://localhost:8080/produtos/${id}`)
            .then(response => response.json())
            .then(produto => {
                $("#produto-id").val(produto.id);
                $("#descricao").val(produto.descricao);
                $("#unidade").val(produto.unidade);
                $("#preco").val(produto.preco);
            });
    };

    window.deleteProduto = function(id) {
        fetch('http://localhost:8080/produtos', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id })
        }).then(() => {
            $(`[data-id='${id}']`).remove();
        });
    };
});
