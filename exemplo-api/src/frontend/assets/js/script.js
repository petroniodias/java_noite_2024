const tbody = document.querySelector('tbody');
const addForm = document.querySelector('.form_inserir');
const inputNome = document.querySelector('.contato_nome');
const inputEmail = document.querySelector('.contato_email');
const inputTelefone = document.querySelector('.contato_telefone');

const fetchContatos = async () => {
    const response = await fetch('http://localhost:8080/contatos')
    const contatos = await response.json()
    return contatos;
}

// Insere contatos
const addContato = async (event) => {
  event.preventDefault();
  
  const contato = { 
        nome: inputNome.value,
        email: inputEmail.value,
        telefone: inputTelefone.value
    };
  
  await fetch('http://localhost:8080/contatos', {
    method: 'post',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(contato),
  });
  
  
  loadContatos();
  inputNome.value = '';
  inputEmail.value = '';
  inputTelefone.value = '';
}

// Exclui contatos
const deleteContato = async (id) => {
  await fetch(`http://localhost:8080/contatos/${id}`, {
    method: 'delete',
  });
  
  loadContatos();
}

// Atualiza contatos
const updateContato = async ({ id, nome, email, telefone }) => {

    await fetch(`http://localhost:8080/contatos/${id}`, {
        method: 'put',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id, nome, email, telefone }),
    });

    loadContatos();
}


const createElement = (tag, innerText = '', innerHTML = '') => {
    const element = document.createElement(tag);

    if (innerText) {
        element.innerText = innerText;
    }

    if (innerHTML) {
        element.innerHTML = innerHTML;
    }

    return element;
}


// Insere linhas 
const createRow = (contato) => {

    const { id, nome, email, telefone } = contato;

    const tr = createElement('tr');
    const tdNome = createElement('td', nome);
    const tdEmail = createElement('td', email);
    const tdTelefone = createElement('td', telefone);
    const tdActions = createElement('td');

    const editButton = createElement('button', '', '<span class="material-symbols-outlined">edit</span>');
    const deleteButton = createElement('button', '', '<span class="material-symbols-outlined">delete</span>');

    const editForm = createElement('form');
    const editNome = createElement('input');
    const editEmail = createElement('input');
    const editFone = createElement('input');
    
    editNome.value = nome;
    editEmail.value = email;
    editFone.value = telefone;
    editForm.appendChild(editNome);
//    editForm.appendChild(editEmail);
//    editForm.appendChild(editFone);

    editForm.addEventListener('submit', (event) => {
        event.preventDefault();
        updateContato({ id, nome: editNome.value, email: editEmail.value, telefone: editFone.value });
    });

    editButton.addEventListener('click', () => {
        tdNome.innerText = '';
        tdNome.appendChild(editForm);
    });

    editButton.classList.add('btn-editar');
    deleteButton.classList.add('btn-excluir');

    deleteButton.addEventListener('click', () => deleteContato(id));

    tdActions.appendChild(editButton);
    tdActions.appendChild(deleteButton);

    tr.appendChild(tdNome);
    tr.appendChild(tdEmail);
    tr.appendChild(tdTelefone);
    tr.appendChild(tdActions);

    return tr;
}

const loadContatos = async () => {
    const contatos = await fetchContatos();

    tbody.innerHTML = '';

    contatos.forEach((contato) => {
        const tr = createRow(contato);
        tbody.appendChild(tr);
    });
}

addForm.addEventListener('submit', addContato);

loadContatos();