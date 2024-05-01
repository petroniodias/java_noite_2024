public class Estoque {
    public static void main(String[] args) {
        Produto p1 = new Produto("Chaleira",15,55.00);
        Produto p2 = new Produto("Tenis de corrida",5,399.00);
        p1.exibeProduto();
        p2.exibeProduto();
        p2.setValor(299.00);
        p2.exibeProduto();

        Cliente c1 = new Cliente("Antonio","61-99988-7766","antonio@zemail.com","111.222.333-44");
        c1.exibeCliente();
    }
}

class Produto {
    // Atributos
    private String nome;
    private int quantidade;
    private double valor;
    
    // Método construtor
    public Produto(String nome, int quantidade,double valor){
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public int getQuantidade(){
        return quantidade;
    }
    
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    public double getValor(){
        return valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }
    
    public void exibeProduto(){
        System.out.println("Nome: " + nome);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Valor: " + valor);
    }
    
}
class Cliente {
    // Atributos
    private String nome;
    private String telefone;
    private String email;
    private String cpf;

    public Cliente(String nome, String telefone, String email, String cpf){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }

    public void exibeCliente(){
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
        System.out.println("email: " + email);
        System.out.println("CPF: " + cpf);
    }
}