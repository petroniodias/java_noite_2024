import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class LeitorBancoDados {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/db_exemplo";
        String jdbcUserName = "root";
        String jdbcPassword = "";
        // ex Query do mal... 
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
                String linha = id + ";" + nome + ";" + unidade + ";" + quantidade + ";" + preco;
                System.out.println(linha);
                salvarDados("produtos.txt", linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao contectar com o banco" + e.getMessage());
        }
    }

    public static void salvarDados(String arquivo, String linha) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
                writer.write(linha);
                writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }
}