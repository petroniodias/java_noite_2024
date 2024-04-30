// Definição da classe Pessoa
class Pessoa {
    // Atributos
    String nome;
    int idade;
    
    // Método construtor
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    // Método para exibir informações da pessoa
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }
}

// Definição da classe Aluno, que herda de Pessoa
class Aluno extends Pessoa {
    // Atributo específico de Aluno
    int matricula;
    
    // Método construtor
    public Aluno(String nome, int idade, int matricula) {
        super(nome, idade); // Chama o construtor da superclasse Pessoa
        this.matricula = matricula;
    }
}

// Definição da classe Professor, que herda de Pessoa
class Professor extends Pessoa {
    // Atributo específico de Professor
    String disciplina;
    
    // Método construtor
    public Professor(String nome, int idade, String disciplina) {
        super(nome, idade); // Chama o construtor da superclasse Pessoa
        this.disciplina = disciplina;
    }
}

// Classe principal para demonstrar o uso das classes
public class Escola {
    public static void main(String[] args) {
        // Criando objetos das classes Pessoa, Aluno e Professor
        Pessoa pessoa = new Pessoa("João", 30);
        Aluno aluno = new Aluno("Maria", 20, 12345);
        Professor professor = new Professor("Carlos", 40, "Matemática");
        
        // Chamando método para exibir informações de cada objeto
        pessoa.exibirInformacoes();
        aluno.exibirInformacoes();
        System.out.println("Matrícula do aluno: " + aluno.matricula);
        professor.exibirInformacoes();
        System.out.println("Disciplina do professor: " + professor.disciplina);
    }
}
