import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDinamico {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        
        String linha;
        String arquivo="produtos.txt";

        List<String> nome = new ArrayList<>();
        List<String> unidade = new ArrayList<>();
        List<Integer> qtde = new ArrayList<>();
        List<Double> preco = new ArrayList<>();
        int opcao=0;

        while(opcao!=5){
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Carregar dados");
            System.out.println("4 - Salvar dados");
            System.out.println("5 - Sair");
            System.out.print("Digite uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1: // Cadastrar produto
                    System.out.println("Digite 'fim' para encerrar o cadastro.");
                    while (true) {
                        System.out.print("Digite o nome: ");
                        String nomeProduto = sc.nextLine();
                        if (nomeProduto.equalsIgnoreCase("fim")) {
                            break;
                        }
                        System.out.print("Digite a unidade: ");
                        String unidadeProduto = sc.nextLine();
                        System.out.print("Digite a quantidade: ");
                        int qtdeProduto = sc.nextInt();
                        System.out.print("Digite o preço: ");
                        double precoProduto = sc.nextDouble();
                        sc.nextLine(); // Limpar o buffer
                        
                        nome.add(nomeProduto);
                        unidade.add(unidadeProduto);
                        qtde.add(qtdeProduto);
                        preco.add(precoProduto);
                    }
                    break;
                case 2: // Listar produtos
                    for (int i = 0; i < nome.size(); i++) {
                        System.out.println(nome.get(i));
                        System.out.println(unidade.get(i));
                        System.out.println(qtde.get(i));
                        System.out.println(preco.get(i));
                    }
                    break;
                case 3: // Carregar dados do arquivo
                    try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                        while ((linha = reader.readLine()) != null) {
                            String[] texto = linha.split(";");
                            nome.add(texto[0]);
                            unidade.add(texto[1]);
                            qtde.add(Integer.parseInt(texto[2]));
                            preco.add(Double.parseDouble(texto[3]));
                        }
                    } catch (IOException e) {
                        System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                    }
                    break;
                case 4: // Salvar dados no arquivo
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
                        for (int i = 0; i < nome.size(); i++) {
                            linha = nome.get(i) + ";" + unidade.get(i) + ";" + qtde.get(i) + ";" + preco.get(i);
                            writer.write(linha);
                            writer.newLine();
                        }
                    } catch (IOException e) {
                        System.out.println("Erro ao gravar no arquivo: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Até mais.");
                    break;
                default: 
                    System.out.println("Opção inválida");
            }
        }
        sc.close();
    }
}
