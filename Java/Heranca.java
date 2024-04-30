// Definição de uma classe Animal (superclasse)
class Animal {
    public void fazerSom() {
        System.out.println("O animal faz um som.");
    }
}

// Definição de uma classe Cachorro (subclasse), que herda de Animal
class Cachorro extends Animal {
    @Override
    public void fazerSom() {
        System.out.println("O cachorro late.");
    }
}

// Classe principal para demonstrar o uso da herança
public class Heranca {
    public static void main(String[] args) {
        // Criando objeto da classe Cachorro
        Cachorro cachorro = new Cachorro();
        
        // Chamando método da classe Cachorro
        cachorro.fazerSom(); // Saída: O cachorro late.
    }
}
