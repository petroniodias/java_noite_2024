import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OficinaMecanica {
    private static final String URL = "jdbc:mysql://localhost:3306/oficina_mecanica";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;

            do {
                System.out.println("1. Adicionar Cliente");
                System.out.println("2. Adicionar Veículo");
                System.out.println("3. Adicionar Serviço");
                System.out.println("4. Listar Clientes");
                System.out.println("5. Listar Veículos");
                System.out.println("6. Listar Serviços");
                System.out.println("7. Atualizar Cliente");
                System.out.println("8. Atualizar Veículo");
                System.out.println("9. Atualizar Serviço");
                System.out.println("10. Remover Cliente");
                System.out.println("11. Remover Veículo");
                System.out.println("12. Remover Serviço");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();  // Consome a nova linha

                switch (opcao) {
                    case 1:
                        adicionarCliente(scanner);
                        break;
                    case 2:
                        adicionarVeiculo(scanner);
                        break;
                    case 3:
                        adicionarServico(scanner);
                        break;
                    case 4:
                        listarClientes();
                        break;
                    case 5:
                        listarVeiculos();
                        break;
                    case 6:
                        listarServicos();
                        break;
                    case 7:
                        atualizarCliente(scanner);
                        break;
                    case 8:
                        atualizarVeiculo(scanner);
                        break;
                    case 9:
                        atualizarServico(scanner);
                        break;
                    case 10:
                        removerCliente(scanner);
                        break;
                    case 11:
                        removerVeiculo(scanner);
                        break;
                    case 12:
                        removerServico(scanner);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } while (opcao != 0);
        }
    }

    private static void adicionarCliente(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO cliente (nome, telefone) VALUES (?, ?)")) {
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.executeUpdate();
            System.out.println("Cliente adicionado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    private static void adicionarVeiculo(Scanner scanner) {
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("ID do Proprietário: ");
        int idProprietario = scanner.nextInt();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO veiculo (modelo, placa, proprietario_id) VALUES (?, ?, ?)")) {
            stmt.setString(1, modelo);
            stmt.setString(2, placa);
            stmt.setInt(3, idProprietario);
            stmt.executeUpdate();
            System.out.println("Veículo adicionado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar veículo: " + e.getMessage());
        }
    }

    private static void adicionarServico(Scanner scanner) {
        System.out.print("ID do Veículo: ");
        int idVeiculo = scanner.nextInt();
        scanner.nextLine();  // Consome a nova linha
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        Date data = new Date(); // Data atual
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO servico (veiculo_id, descricao, valor, data) VALUES (?, ?, ?, ?)")) {
            stmt.setInt(1, idVeiculo);
            stmt.setString(2, descricao);
            stmt.setDouble(3, valor);
            stmt.setTimestamp(4, new Timestamp(data.getTime()));
            stmt.executeUpdate();
            System.out.println("Serviço adicionado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar serviço: " + e.getMessage());
        }
    }

    private static void listarClientes() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cliente")) {
            while (rs.next()) {
                System.out.println("Cliente [id=" + rs.getInt("id") + ", nome=" + rs.getString("nome") + ", telefone=" + rs.getString("telefone") + "]");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
    }

    private static void listarVeiculos() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT v.*, c.nome AS proprietario_nome FROM veiculo v JOIN cliente c ON v.proprietario_id = c.id")) {
            while (rs.next()) {
                System.out.println("Veiculo [id=" + rs.getInt("id") + ", modelo=" + rs.getString("modelo") + ", placa=" + rs.getString("placa") + ", proprietario=" + rs.getString("proprietario_nome") + "]");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos: " + e.getMessage());
        }
    }

    private static void listarServicos() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT s.*, v.modelo AS veiculo_modelo, v.placa AS veiculo_placa FROM servico s JOIN veiculo v ON s.veiculo_id = v.id")) {
            while (rs.next()) {
                System.out.println("Servico [id=" + rs.getInt("id") + ", veiculo=" + rs.getString("veiculo_modelo") + " (" + rs.getString("veiculo_placa") + "), descricao=" + rs.getString("descricao") + ", valor=" + rs.getDouble("valor") + ", data=" + rs.getTimestamp("data") + "]");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar serviços: " + e.getMessage());
        }
    }

    private static void atualizarCliente(Scanner scanner) {
        System.out.print("ID do Cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consome a nova linha
        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Telefone: ");
        String telefone = scanner.nextLine();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE cliente SET nome = ?, telefone = ? WHERE id = ?")) {
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setInt(3, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cliente atualizado com sucesso.");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    private static void atualizarVeiculo(Scanner scanner) {
        System.out.print("ID do Veículo: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consome a nova linha
        System.out.print("Novo Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Nova Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Novo ID do Proprietário: ");
        int idProprietario = scanner.nextInt();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE veiculo SET modelo = ?, placa = ?, proprietario_id = ? WHERE id = ?")) {
            stmt.setString(1, modelo);
            stmt.setString(2, placa);
            stmt.setInt(3, idProprietario);
            stmt.setInt(4, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Veículo atualizado com sucesso.");
            } else {
                System.out.println("Veículo não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar veículo: " + e.getMessage());
        }
    }

    private static void atualizarServico(Scanner scanner) {
        System.out.print("ID do Serviço: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consome a nova linha
        System.out.print("Nova Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Novo Valor: ");
        double valor = scanner.nextDouble();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE servico SET descricao = ?, valor = ? WHERE id = ?")) {
            stmt.setString(1, descricao);
            stmt.setDouble(2, valor);
            stmt.setInt(3, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Serviço atualizado com sucesso.");
            } else {
                System.out.println("Serviço não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar serviço: " + e.getMessage());
        }
    }

    private static void removerCliente(Scanner scanner) {
        System.out.print("ID do Cliente: ");
        int id = scanner.nextInt();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM cliente WHERE id = ?")) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente removido com sucesso.");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover cliente: " + e.getMessage());
        }
    }

    private static void removerVeiculo(Scanner scanner) {
        System.out.print("ID do Veículo: ");
        int id = scanner.nextInt();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM veiculo WHERE id = ?")) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Veículo removido com sucesso.");
            } else {
                System.out.println("Veículo não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover veículo: " + e.getMessage());
        }
    }

    private static void removerServico(Scanner scanner) {
        System.out.print("ID do Serviço: ");
        int id = scanner.nextInt();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM servico WHERE id = ?")) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Serviço removido com sucesso.");
            } else {
                System.out.println("Serviço não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover serviço: " + e.getMessage());
        }
    }
}
