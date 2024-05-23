import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Cliente {
    private int id;
    private String nome;
    private String telefone;

    public Cliente(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", telefone=" + telefone + "]";
    }
}

class Veiculo {
    private int id;
    private String modelo;
    private String placa;
    private Cliente proprietario;

    public Veiculo(int id, String modelo, String placa, Cliente proprietario) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.proprietario = proprietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Cliente getProprietario() {
        return proprietario;
    }

    public void setProprietario(Cliente proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        return "Veiculo [id=" + id + ", modelo=" + modelo + ", placa=" + placa + ", proprietario=" + proprietario + "]";
    }
}

class Servico {
    private int id;
    private Veiculo veiculo;
    private String descricao;
    private double valor;
    private Date data;

    public Servico(int id, Veiculo veiculo, String descricao, double valor, Date data) {
        this.id = id;
        this.veiculo = veiculo;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Servico [id=" + id + ", veiculo=" + veiculo + ", descricao=" + descricao + ", valor=" + valor + ", data=" + data + "]";
    }
}

public class OficinaArrayList {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Veiculo> veiculos = new ArrayList<>();
    private static List<Servico> servicos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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

        scanner.close();
    }

    private static void adicionarCliente(Scanner scanner) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consome a nova linha
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        clientes.add(new Cliente(id, nome, telefone));
    }

    private static void adicionarVeiculo(Scanner scanner) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consome a nova linha
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("ID do Proprietário: ");
        int idProprietario = scanner.nextInt();
        Cliente proprietario = clientes.stream()
                                       .filter(c -> c.getId() == idProprietario)
                                       .findFirst()
                                       .orElse(null);
        if (proprietario != null) {
            veiculos.add(new Veiculo(id, modelo, placa, proprietario));
        } else {
            System.out.println("Proprietário não encontrado.");
        }
    }

    private static void adicionarServico(Scanner scanner) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consome a nova linha
        System.out.print("ID do Veículo: ");
        int idVeiculo = scanner.nextInt();
        scanner.nextLine();  // Consome a nova linha
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();  // Consome a nova linha
        Date data = new Date(); // Data atual
        Veiculo veiculo = veiculos.stream()
                                  .filter(v -> v.getId() == idVeiculo)
                                  .findFirst()
                                  .orElse(null);
        if (veiculo != null) {
            servicos.add(new Servico(id, veiculo, descricao, valor, data));
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private static void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private static void listarVeiculos() {
        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo);
        }
    }

    private static void listar
