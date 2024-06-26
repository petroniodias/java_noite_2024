import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Produto {
    private String nome;
    private String unidade;
    private int quantidade;
    private double preco;

    // Construtor
    public Produto(String nome, String unidade, int quantidade, double preco) {
        this.nome = nome;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + ";" + unidade + ";" + quantidade + ";" + preco;
    }
}

public class GerenciadorProdutos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = -1;
        List<Produto> produtos = new ArrayList<>();

        while (opcao != 0) {
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Consultar produto");
            System.out.println("4 - Remover produto");
            System.out.println("5 - Ordenar dados");
            System.out.println("6 - Carregar dados do arquivo");
            System.out.println("7 - Salvar no arquivo");
            System.out.println("8 - Carregar dados do banco de dados");
            System.out.println("9 - Salvar dados no banco de dados");
            System.out.println("0 - Sair");
            System.out.print("Digite uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha após a leitura do número

            switch (opcao) {
                case 1: // Cadastrar produto
                    cadastrarProduto(sc, produtos);
                    break;
                case 2: // Listar produtos
                    listarProdutos(produtos);
                    break;
                case 3: // Consultar produto
                    consultarProduto(sc, produtos);
                    break;
                case 4: // Remover produto
                    removerProduto(sc, produtos);
                    break;
                case 5: // Ordenar dados
                    ordenarProdutos(produtos);
                    break;
                case 6: // Carregar dados do arquivo
                    produtos = carregarDados("produtos.txt");
                    break;
                case 7: // Salvar e sair
                    salvarDados("produtos.txt", produtos);
                    break;
                case 8: // Carregar dados do banco de dados
                    produtos = carregarDadosBD();
                    break;
                case 9: // Salvar dados no banco de dados
                    salvarDadosBD(produtos);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
        sc.close();
    }

    public static void cadastrarProduto(Scanner sc, List<Produto> produtos) {
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();
        if (nome.equalsIgnoreCase("fim")) {
            return;
        }
        System.out.print("Digite a unidade: ");
        String unidade = sc.nextLine();
        System.out.print("Digite a quantidade: ");
        int qtde = sc.nextInt();
        System.out.print("Digite o preco: ");
        double preco = sc.nextDouble();
        sc.nextLine(); // Consumir a quebra de linha após a leitura do número

        produtos.add(new Produto(nome, unidade, qtde, preco));
    }

    public static void listarProdutos(List<Produto> produtos) {
        for (Produto p : produtos) {
            System.out.println("Nome: " + p.getNome());
            System.out.println("Unidade: " + p.getUnidade());
            System.out.println("Quantidade: " + p.getQuantidade());
            System.out.println("Preço: " + p.getPreco());
        }
    }

    public static void consultarProduto(Scanner sc, List<Produto> produtos) {
        System.out.print("Digite o nome do produto: ");
        String nome = sc.nextLine();
        boolean encontrado = false;
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Nome: " + p.getNome());
                System.out.println("Unidade: " + p.getUnidade());
                System.out.println("Quantidade: " + p.getQuantidade());
                System.out.println("Preço: " + p.getPreco());
                encontrado = true;
                System.out.print("Deseja alterar algum dado (S/N)? ");
                String resposta = sc.nextLine();
                if (resposta.equalsIgnoreCase("S")) {
                    System.out.print("Digite o nome do dado que deseja alterar (nome, unidade, quantidade, preco): ");
                    String dado = sc.nextLine();
                    switch (dado.toLowerCase()) {
                        case "nome":
                            System.out.print("Digite o novo nome: ");
                            p.setNome(sc.nextLine());
                            break;
                        case "unidade":
                            System.out.print("Digite a nova unidade: ");
                            p.setUnidade(sc.nextLine());
                            break;
                        case "quantidade":
                            System.out.print("Digite a nova quantidade: ");
                            p.setQuantidade(sc.nextInt());
                            break;
                        case "preco":
                            System.out.print("Digite o novo preço: ");
                            p.setPreco(sc.nextDouble());
                            break;
                        default:
                            System.out.println("Opção inválida");
                    }
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void removerProduto(Scanner sc, List<Produto> produtos) {
        System.out.print("Digite o nome do produto a ser removido: ");
        String nome = sc.nextLine();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome().equalsIgnoreCase(nome)) {
                produtos.remove(i);
                System.out.println("Produto removido com sucesso.");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
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
                if (dados.length == 4) {
                    Produto produto = new Produto(dados[0], dados[1], Integer.parseInt(dados[2]),
                            Double.parseDouble(dados[3]));
                    produtos.add(produto);
                } else {
                    System.out.println("Formato inválido da linha: " + linha);
                }
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro de formato numérico: " + e.getMessage());
        }
        return produtos;
    }

    public static void ordenarProdutos(List<Produto> produtos) {
        int n = produtos.size();
        for (int i=0;i<n-1;i++) {
            for (int j=i+1;j<n;j++) {
                if (produtos.get(i).getNome().compareToIgnoreCase(produtos.get(j).getNome()) > 0){
                    Produto tmp = produtos.get(j);
                    produtos.set(j,produtos.get(i));
                    produtos.set(i,tmp);
                }
            }
        }
    }

    public static List<Produto> carregarDadosBD(){
        List<Produto> produtos = new ArrayList<>();
        
        String jdbcURL = "jdbc:mysql://localhost:3306/db_exemplo";
        String jdbcUserName = "root";
        String jdbcPassword = "";
        String sql = "SELECT id,nome,unidade,quantidade,preco FROM tb_produtos";
        
        try (Connection conexao = DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String unidade = resultSet.getString("unidade");
                int quantidade = resultSet.getInt("quantidade");
                double preco = resultSet.getDouble("preco");
                Produto produto = new Produto(nome, unidade, quantidade,preco);
                produtos.add(produto);
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao ler o banco de dados " + e.getMessage());
        }
        return produtos;
    }

    public static void salvarDadosBD(List<Produto> produtos){
        String jdbcURL = "jdbc:mysql://localhost:3306/db_exemplo";
        String jdbcUserName = "root";
        String jdbcPassword = "";

        try (Connection conexao = DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
            Statement statement = conexao.createStatement()) {
            for (Produto p : produtos) {
                String sql = "SELECT id,nome,unidade,quantidade,preco FROM tb_produtos ";
                sql += "WHERE nome='"+p.getNome()+"' AND unidade='"+p.getUnidade()+"'";
                //System.out.println(sql);
                /*
                    Pesquisar (consulta/select) o item pelo nome e unidade para ver se tem no banco.
                    Se houver realizo um update
                    Se não houver realizo um insert
                */
                try (Connection conn = DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                    if (rs.next()) {
                        System.out.println("Produto existente");
                        int id = rs.getInt("id");
                        // fazer update
                        // UPDATE `tb_produtos` SET `unidade` = 'G' WHERE `tb_produtos`.`id` = 10;
                        try (Connection connItem = DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
                        PreparedStatement stmtItem = connItem.prepareStatement("UPDATE tb_produtos SET nome=?, unidade=?, quantidade=?, preco=? WHERE id=?")) {
                        stmtItem.setString(1, p.getNome());
                        stmtItem.setString(2, p.getUnidade());
                        stmtItem.setInt(3, p.getQuantidade());
                        stmtItem.setDouble(4, p.getPreco());
                        stmtItem.setInt(5, id);
                        stmtItem.executeUpdate();
                        System.out.println("Produto atualizado com sucesso.");
                    } catch (SQLException e) {
                        System.out.println("Erro ao atualizar produto: " + e.getMessage());
                    }
                    } else {
                        System.out.println("Produto inexistente");
                        // fazer insert
                        try (Connection connItem = DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
                            PreparedStatement stmtItem = connItem.prepareStatement("INSERT INTO tb_produtos (nome, unidade, quantidade, preco) VALUES (?, ?, ?, ?)")) {
                            stmtItem.setString(1, p.getNome());
                            stmtItem.setString(2, p.getUnidade());
                            stmtItem.setInt(3, p.getQuantidade());
                            stmtItem.setDouble(4, p.getPreco());
                            stmtItem.executeUpdate();
                            System.out.println("Produto adicionado com sucesso.");
                        } catch (SQLException e) {
                            System.out.println("Erro ao adicionar produto: " + e.getMessage());
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Erro ao acessar produto: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

}
