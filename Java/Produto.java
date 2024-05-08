import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Scanner;

public class Produto {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        
        int quantidade = 50;
        int i=0;
        String linha;
        String arquivo="produtos.txt";

        String[] nome = new String[quantidade];
        String[] unidade = new String[quantidade];
        int[] qtde = new int[quantidade];
        double[] preco = new double[quantidade];
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
                    while(i<50){
                        System.out.print("Digite o nome: ");
                        nome[i] = sc.nextLine();
                        if (nome[i].equalsIgnoreCase("fim")){
                            break;
                        }
                        System.out.print("Digite a unidade: ");
                        unidade[i] = sc.nextLine();
                        System.out.print("Digite a quantidade: ");
                        qtde[i] = sc.nextInt();
                        System.out.print("Digite o preco: ");
                        preco[i] = sc.nextDouble();
                        sc.nextLine();
            
                        i++;
                    }
                    break;
                case 2: // Listar produtos
                    for(int j=0;j<i;j++){
                        System.out.println(nome[j]);
                        System.out.println(unidade[j]);
                        System.out.println(qtde[j]);
                        System.out.println(preco[j]);
                    }
                    break;
                case 3: // Carregar dados do arquivo
                    try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                        // Cria um BufferedReader para ler o arquivo especificado

                        while ((linha = reader.readLine()) != null) {
                            // Lê cada linha do arquivo até que o final do arquivo seja alcançado
                            // A função readLine() retorna a linha lida ou null se o final do arquivo for alcançado

                            String[] texto = linha.split(";");
                            nome[i] = texto[0];
                            unidade[i] = texto[1];
                            qtde[i] = Integer.parseInt(texto[2]);
                            preco[i] = Double.parseDouble(texto[3]);
                            i++;
                        }
                    } catch (IOException e) {
                        // Captura e trata a exceção caso ocorra um erro de E/S (Input/Output)
                        System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                    }
                    break;
                case 4: // Salvar dados no arquivo
                    for(int j=0;j<i;j++){
                        // Grava no arquivo
                        linha = nome[j]+";"+unidade[j]+";"+qtde[j]+";"+preco[j];
                        gravarNoArquivo(arquivo, linha);
                    }
                    break;
                default: 
                    System.out.println("Opção inválida");
            }
        }
        sc.close();
    }

    public static void gravarNoArquivo(String arquivo, String linha) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
            // Cria um BufferedWriter para escrever no arquivo especificado
            // O segundo argumento "true" indica que o arquivo deve ser aberto em modo de adição (append)

            writer.write(linha); // Escreve a linha no arquivo
            writer.newLine(); // Insere uma nova linha após cada linha escrita
        } catch (IOException e) {
            // Captura e trata a exceção caso ocorra um erro de E/S (Input/Output)
            System.out.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }
}
