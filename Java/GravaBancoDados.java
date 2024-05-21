import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GravaBancoDados {
    public static void main (String[] args){
        String arquivo = "produtos.txt";
        String jdbcURL = "jdbc:mysql://localhost:3306/db_exemplo";
        String jdbcUserName = "root";
        String jdbcPassword = "";

        try (Connection conexao = DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
            Statement statement = conexao.createStatement()) {

            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))){
                String linha;
                while ((linha=reader.readLine()) != null){
                    String[] dados = linha.split(";");
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String unidade = dados[2];
                    int quantidade = Integer.parseInt(dados[3]);
                    Double preco = Double.parseDouble(dados[4]);
                    
                    String sql = "INSERT INTO tb_produtos (nome,unidade,quantidade,preco) ";
                        sql += "VALUES ('"+nome+"','"+unidade+"',"+quantidade+","+preco+")";
                    System.out.println(sql);
                    statement.executeUpdate(sql);
                }
            } catch (IOException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
}
