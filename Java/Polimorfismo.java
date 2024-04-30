// Definição de uma classe Animal
class Animal {
    public void fazerSom() {
        System.out.println("O animal faz um som.");
    }
}

// Definição de uma classe Cachorro, que herda de Animal
class Cachorro extends Animal {
    @Override
    public void fazerSom() {
        System.out.println("O cachorro late.");
    }
}

// Definição de uma classe Gato, que herda de Animal
class Gato extends Animal {
    @Override
    public void fazerSom() {
        System.out.println("O gato mia.");
    }
}

// Classe principal para demonstrar o uso do polimorfismo
public class Polimorfismo {
    public static void main(String[] args) {
        // Criando objetos de diferentes tipos de animais
        Animal animal1 = new Cachorro();
        Animal animal2 = new Gato();
        
        // Chamando o método fazerSom para cada animal
        animal1.fazerSom(); // Saída: O cachorro late.
        animal2.fazerSom(); // Saída: O gato mia.
    }
}
