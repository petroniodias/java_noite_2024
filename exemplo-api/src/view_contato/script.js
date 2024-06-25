// Selecionando os elementos HTML necessários
const tabela = document.getElementById('tabela'); // Tabela onde serão listados os ccontatos
const adicionarContatoForm = document.getElementById('adicionar-form'); // Formulário de adição de contato
const atualizarContatoForm = document.getElementById('atualizar-form'); // Formulário de atualização de contato
const idInput = document.getElementById('id'); // Input de ID para atualização
const nomeInput = document.getElementById('nome'); // Input de nome para adição
const emailInput = document.getElementById('email'); // Input de email para adição
const telefoneInput = document.getElementById('telefone'); // Input de telefone para adição
const novoNomeInput = document.getElementById('nomeAtualizado'); // Input de nome para atualização
const novoEmailInput = document.getElementById('emailAtualizado'); // Input de email para atualização
const novoTelefoneInput = document.getElementById('telefoneAtualizado'); // Input de telefone para atualização

// Funções para exibir os formulários ao clicar no botão para isso
document.getElementById("mostrarFormAdd").addEventListener("click", function() {
    var formulario = document.getElementById("adicionar-form");
    formulario.style.display = "block";
    var botao = document.getElementById("mostrarFormAdd");
    botao.style.display = "none"
})

function mostrarFormulario() {
    var formulario = document.getElementById("atualizar-form");
    formulario.style.display = "block";
    var botao = document.getElementById("mostrarFormAtt");
    botao.style.display = "none"
}
document.getElementById("mostrarFormAtt").addEventListener("click", function() {
    mostrarFormulario();
})

// Função para carregar os contatos da API e popular a tabela
function carregarContatos() {
    fetch('http://localhost:8080/contatos')
        .then(response => response.json())
        .then(contatos => {
            // Criando os cabeçalhos da tabela
            const cabecalho = tabela.createTHead();
            const cabecalhoLinha = cabecalho.insertRow();
            const cabecalhoID = cabecalhoLinha.insertCell();
            const cabecalhoNome = cabecalhoLinha.insertCell();
            const cabecalhoEmail = cabecalhoLinha.insertCell();
            const cabecalhoTelefone = cabecalhoLinha.insertCell();
            const cabecalhoAcao = cabecalhoLinha.insertCell();
            // Preenchendo as células do cabeçalho da tabela
            cabecalhoID.innerText = "ID";
            cabecalhoNome.innerText = "Nome";
            cabecalhoEmail.innerText = "Email";
            cabecalhoTelefone.innerText = "Telefone";
            cabecalhoAcao.innerText = "Ação";
            // Criando o corpo da tabela
            const corpoTabela = tabela.createTBody();
            // Criando o loop para criar uma linha para cada contato
            contatos.forEach(contato => {
                // Cria as colunas do corpo da tabela
                const linha = corpoTabela.insertRow();
                const colunaID = linha.insertCell();
                const colunaNome = linha.insertCell();
                const colunaEmail = linha.insertCell();
                const colunaTelefone = linha.insertCell();
                const colunaAcao = linha.insertCell();
                // Atribui às colunas os valores dos atributos do contato

                colunaID.innerText = contato
                .id;
                colunaNome.innerText = contato
                .nome;
                colunaEmail.innerText = contato
                .email;
                colunaTelefone.innerText = contato
                .telefone;
                // Adicionando botões de editar e excluir
                const botaoEditar = document.createElement("button");
                botaoEditar.innerText = "Editar"; // Gero o texto do botão
                botaoEditar.id = "botaoEditar"; // Gera o id do botão
                const botaoExcluir = document.createElement("button");
                botaoExcluir.innerText = "Excluir"; // Gero o texto do botão
                botaoExcluir.id = "botaoExcluir"; // Gera o id do botão
                // Inserindo os botões na coluna "Ações" da tabela
                colunaAcao.appendChild(botaoEditar);
                colunaAcao.appendChild(botaoExcluir);
                // Definindo as ações dos botões
                botaoEditar.onclick = function() {
                    mostrarFormulario();
                    // Define o valor das variáveis "input" (que correspondem a objetos do form-atualizar) com os dados do contato
                     selecionado
                    idInput.value = contato.id;
                    novoNomeInput.value = contato.nome;
                    novoEmailInput.value = contato.email;
                    novoTelefoneInput.value = contato.telefone;
                };
                botaoExcluir.onclick = function() {
                    // Cria uma janela para confirmar se o ususário quer remover um contato
                    
                    const resposta = window.confirm(`Tem certeza que deseja excluir o/a contato
                     ${contato.nome}?`);
                    if (resposta) { // Se o boolean "resposta" receber "true", chamar a função "excluirContato"
                        excluirContato(contato.id);
                    }
                }
            });
        })
        .catch(error => console.error('Erro ao carregar contatos:', error));
}

// Adicionando um evento para o formulário de adição de contato
adicionarContatoForm.addEventListener('submit', function(event) {
    event.preventDefault();
    // Obtendo os valores do formulário para criar um novo contato
    const novoContato = {
        nome: nomeInput.value,
        email: emailInput.value,
        telefone: telefoneInput.value
    };
    // Enviando uma requisição de verbo POST para adicionar o contato
    fetch('http://localhost:8080/contatos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novoContato)
    })
    .then(response => response.json())
    .then(contatoAdicionado => {
        carregarContatos();
    })
    .catch(error => console.error('Erro ao adicionar contato:', error));
});

// Adicionando um evento para o formulário de atualização de contato
atualizarContatoForm.addEventListener('submit', function(event) {
    event.preventDefault();
    // Obtendo os valores do formulário para atualizar o contato
    const contato = {
        id: idInput.value,
        nome: novoNomeInput.value,
        email: novoEmailInput.value,
        telefone: novoTelefoneInput.value
    };
    // Enviando uma requisição PUT para atualizar o contato
    fetch(`http://localhost:8080/contato/${contato.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(contato)
    })
    .then(response => response.json())
    .then(contatoAtualizado => {
        window.location.href = `./index.html`; // Re-carrega a página
    })
    .catch(error => console.error('Erro ao atualizar contato:', error));
});

// Função para excluir um contato (que recebe como parâmetro o id enviado do "botaoExcluir")
function excluirContato(id) {
    // Adiciona o id à URL pois o Endpoint para o verbo DELETE exige ele
    fetch(`http://localhost:8080/contatos/${id}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (response.ok) {
            alert("Contato excluído com sucesso!");
        } else {
            alert("Erro ao excluir o contato");
        }
    })
}
// Carregar os contatos ao carregar a página
carregarContatos();