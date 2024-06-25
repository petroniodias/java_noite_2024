// Pra começar, tamo pegando referências dos elementos lá do HTML, né?!
const tabela = document.getElementById('tabela');
const adicionarProdutoForm = document.getElementById('adicionar-form');
const atualizarProdutoForm = document.getElementById('atualizar-form');
// Aqui tão as entradas de informações que o indivíduo pode digitar.
const idInput = document.getElementById('id');
const nomeInput = document.getElementById('nome');
const descricaoInput = document.getElementById('descricao');
const unidadeInput = document.getElementById('unidade');
const precoInput = document.getElementById('preco');
const quantidadeInput = document.getElementById('quantidade');
// Ôia só, esses aqui são pros dados atualizados quando o camarada vai editar um trem.
const novoNomeInput = document.getElementById('nomeAtualizado');
const novaDescricaoInput = document.getElementById('descricaoAtualizada');
const novaUnidadeInput = document.getElementById('unidadeAtualizada');
const novoPrecoInput = document.getElementById('precoAtualizado');
const novaQuantidadeInput = document.getElementById('quantidadeAtualizada');

// Aqui é um trabalho danado, mas tá carregando os produtos da API e mostrando na tabela.
function carregarProdutos() {
    fetch('http://localhost:8080/produtos')
        .then(response => response.json())
        .then(produtos => {
            // Vamo criá o cabeçalho da tabela, sô!
            const cabecalho = tabela.createTHead();
            const cabecalhoLinha = cabecalho.insertRow();
            const cabecalhoID = cabecalhoLinha.insertCell();
            const cabecalhoNome = cabecalhoLinha.insertCell();
            const cabecalhoDescricao = cabecalhoLinha.insertCell();
            const cabecalhoUnidade = cabecalhoLinha.insertCell();
            const cabecalhoPreco = cabecalhoLinha.insertCell();
            const cabecalhoQuantidade = cabecalhoLinha.insertCell();
            const cabecalhoAcao = cabecalhoLinha.insertCell();
            // Aqui tão os títulos das coluna, uai!
            cabecalhoID.innerText = "ID";
            cabecalhoNome.innerText = "Nome";
            cabecalhoDescricao.innerText = "Descrição";
            cabecalhoUnidade.innerText = "Unidade";
            cabecalhoPreco.innerText = "Preço";
            cabecalhoQuantidade.innerText = "Quantidade";
            cabecalhoAcao.innerText = "Ação";
            // Bão, agora vamo encher a tabela com os produtos do povo.
            const corpoTabela = tabela.createTBody();
            produtos.forEach(produto => {
                const linha = corpoTabela.insertRow();
                const colunaId = linha.insertCell();
                const colunaNome = linha.insertCell();
                const colunaDescricao = linha.insertCell();
                const colunaUnidade = linha.insertCell();
                const colunaPreco = linha.insertCell();
                const colunaQuantidade = linha.insertCell();
                const colunaAcao = linha.insertCell();
                // Ói, aqui tão os dados do produto na tabela.
                colunaId.innerText = produto.id;
                colunaNome.innerText = produto.nome;
                colunaDescricao.innerText = produto.descricao;
                colunaUnidade.innerText = produto.unidade;
                colunaPreco.innerText = produto.preco;
                colunaQuantidade.innerText = produto.quantidade;
                // Agora, vamo fazê os botãozinho de editar e excluir.
                const botaoEditar = document.createElement("button");
                botaoEditar.innerText = "Editar";
                botaoEditar.id = "botaoEditar";
                const botaoExcluir = document.createElement("button");
                botaoExcluir.innerText = "Excluir";
                botaoExcluir.id = "botaoExcluir";
                // Vamo dizer o que esses botões tão fazendo, uai!
                botaoEditar.onclick = function() {
                    idInput.value = produto.id;
                    novoNomeInput.value = produto.nome;
                    novaDescricaoInput.value = produto.descricao;
                    novaUnidadeInput.value = produto.unidade;
                    novoPrecoInput.value = produto.preco;
                    novaQuantidadeInput.value = produto.quantidade;
                }
                botaoExcluir.onclick = function() {
                    const resposta = window.confirm(`Cê tá sertin que quer excluir o produto ${produto.nome}, fi?`);
                    if (resposta) {
                        excluirProduto(produto.id);
                    }
                }
                // Bão, vamo bota esses botões na coluna de ação.
                colunaAcao.appendChild(botaoEditar);
                colunaAcao.appendChild(botaoExcluir);
            });
        })
        .catch(error => console.error('Ôxi, deu erro carregano os produtos:', error));
}

// Agora vamo vê quando o pessoar vai adicioná um produto novo.
adicionarProdutoForm.addEventListener('submit', function(event) {
    event.preventDefault();
    // Vamo pegá os trem que o pessoar digitô pra fazê um produto novo.
    const novoProduto = {
        nome: nomeInput.value,
        descricao: descricaoInput.value,
        unidade: unidadeInput.value,
        preco: precoInput.value,
        quantidade: quantidadeInput.value
    };

    // Mandano um trem pro servidor pra adicioná o produto.
    fetch('http://localhost:8080/produtos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novoProduto)
    })
    .then(response => response.json())
    .then(produtoAdicionado => {
        // Bão, depois de add, vamo vortá pra página dos produtos.
        window.location.href = `./produto.html`;
    })
    .catch(error => console.error('Ôxi, deu erro ao adicionar o produto:', error));
});

// Agora, vamo vê quando o pessoar vai atualizá um produto.
atualizarProdutoForm.addEventListener('submit', function(event) {
    event.preventDefault();

    // Pegando os trem atualizado que o pessoar digitô.
    const produto = {
        id: idInput.value,
        nome: novoNomeInput.value,
        descricao: novaDescricaoInput.value,
        unidade: novaUnidadeInput.value,
        preco: novoPrecoInput.value,
        quantidade: novaQuantidadeInput.value
    };
// Mandano um trem pro servidor pra atualizá o produto.
fetch(`http://localhost:8080/produtos/${produto.id}`, {
    method: 'PUT',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(produto)
})
.then(response => response.json())
.then(produtoAtualizado => {
    // Bão, depois de atualizá, vamo vortá pra página dos produtos.
    window.location.href = `./produto.html`;
})
.catch(error => console.error('Ôxi, deu erro ao atualizar o produto:', error));
});
// Agora, vamo vê quando o pessoar vai excluí um produto.
function excluirProduto(id) {
fetch(`https://localhost:8080/produtos/${id}`, {
    method: 'DELETE'
})
.then(response => {
    if (response.ok) {
        alert("Produto excluído com sucesso!");
        // Vamo vortá pra página de produtos depois de excluir.
        window.location.href = './produto.html';
    } else {
        alert("Ôxi, deu erro ao excluir o produto");
    }
});
}

// Vamo começá carregando a lista de produtos quando abrí a página.
carregarProdutos();