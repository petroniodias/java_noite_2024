// Definição de uma classe Produto com encapsulamento
class Produto {
    private String nome;
    private double preco;
    
    // Método construtor
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    
    // Métodos getters e setters para acessar e modificar os atributos encapsulados
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getPreco() {
        return preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }
}

// Classe principal para demonstrar o uso da classe Produto
public class Encapsulamento {
    public static void main(String[] args) {
        // Criando objeto da classe Produto
        Produto produto = new Produto("Notebook", 2500.0);
        
        // Acessando e modificando atributos encapsulados usando getters e setters
        System.out.println("Nome do produto: " + produto.getNome());
        System.out.println("Preço do produto: R$ " + produto.getPreco());
        
        // Modificando o preço do produto
        produto.setPreco(2800.0);
        System.out.println("Novo preço do produto: R$ " + produto.getPreco());
    }
}
