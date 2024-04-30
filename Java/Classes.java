// Definição de uma classe Pessoa
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

// Classe principal para demonstrar o uso da classe Pessoa
public class Classes {
    public static void main(String[] args) {
        // Criando objetos da classe Pessoa
        Pessoa pessoa1 = new Pessoa("João", 25);
        Pessoa pessoa2 = new Pessoa("Maria", 30);
        
        // Chamando método para exibir informações das pessoas
        pessoa1.exibirInformacoes();
        pessoa2.exibirInformacoes();
    }
}
