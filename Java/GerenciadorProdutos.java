import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Produto {
    private String nome;
    private String unidade;
    private int quantidade;
    private double preco;
    private String grupo;

    public Produto(String nome, String unidade, String grupo, int quantidade, double preco) {
        this.nome = nome;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.preco = preco;
        this.grupo = grupo;
    }

    public String getNome() {
        return nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public String getGrupo() {
        return grupo;
    }

    @Override
    public String toString() {
        return nome + ";" + unidade + ";" + grupo + ";" + quantidade + ";" + preco ;
    }
}

public class GerenciadorProdutos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcao = 0;
        List<Produto> produtos = new ArrayList<>();

        while (opcao != 5) {
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Carregar dados");
            System.out.println("4 - Salvar dados");
            System.out.println("5 - Sair");
            System.out.print("Digite uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = sc.nextLine();
                    if (nome.equalsIgnoreCase("fim")) {
                        break;
                    }
                    System.out.print("Digite a unidade: ");
                    String unidade = sc.nextLine();
                    System.out.print("Digite a grupo: ");
                    String grupo = sc.nextLine();
                    System.out.print("Digite a quantidade: ");
                    int quantidade = sc.nextInt();
                    System.out.print("Digite o preco: ");
                    double preco = sc.nextDouble();
                    sc.nextLine();

                    Produto produto = new Produto(nome, unidade, grupo, quantidade, preco);
                    produtos.add(produto);
                    break;
                case 2:
                    for (Produto p : produtos) {
                        System.out.println(p.toString());
                    }
                    break;
                case 3:
                    // Carregar dados do arquivo
                    System.out.println("Carregando dados do arquivo...");
                    produtos = carregarDados("produtos.txt");
                    break;
                case 4:
                    // Salvar dados no arquivo
                    System.out.println("Salvando dados no arquivo...");
                    salvarDados("produtos.txt", produtos);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

        sc.close();
    }

    public static void salvarDados(String arquivo, List<Produto> produtos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Produto p : produtos) {
                writer.write(p.toString());
                writer.newLine();
            }
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }

    public static List<Produto> carregarDados(String arquivo) {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Produto produto = new Produto(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]), Double.parseDouble(dados[4]));
                produtos.add(produto);
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return produtos;
    }
}
  
