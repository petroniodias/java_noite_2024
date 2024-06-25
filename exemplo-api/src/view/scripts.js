// Selecionando elementos do DOM usando seus IDs
const produtosList = document.getElementById('produtos-list');

// Função para carregar a lista de produtos do servidor via API
function carregarProdutos() {
    fetch('http://localhost:8080/produtos') // Faz uma requisição GET para a API de produtos
        .then(response => response.json()) // Extrai os dados JSON da resposta da API
        .then(produtos => {
            produtosList.innerHTML = ''; // Limpa a lista de produtos atual

            // Itera sobre a lista de produtoss recebida da API e adiciona cada produto à lista do DOM
            produtos.forEach(produto => {
                const li = document.createElement('li'); // Cria um elemento <li> para cada produtos
                li.innerHTML = `<strong>${produto.nome}</strong> - ${produto.descricao} - ${produto.unidade} - ${produto.quantidade} - ${produto.preco || ''}
                <a href="#" class="alterar" data-id="${produto.id}">Alterar</a>
                <a href="#" class="excluir" data-id="${produto.id}">Excluir</a>`;
                produtosList.appendChild(li); // Adiciona o <li> à lista de produtos no DOM
            });
        })
        .catch(error => console.error('Erro ao carregar produtos:', error)); // Tratamento de erro caso ocorra algum problema na requisição
}

// Carrega a lista de produtos ao carregar a página
carregarProdutos();