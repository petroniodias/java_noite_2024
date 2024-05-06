import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExemploArquivo {

    public static void main(String[] args) {
        String arquivo = "arquivo.txt";

        // Gravar dados no arquivo
        gravarNoArquivo(arquivo, "Produto A,10.0,20");
        gravarNoArquivo(arquivo, "Produto B,15.0,15");
        gravarNoArquivo(arquivo, "Produto C,5.0,30");

        // Ler dados do arquivo
        lerDoArquivo(arquivo);
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

    public static void lerDoArquivo(String arquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            // Cria um BufferedReader para ler o arquivo especificado

            String linha;
            while ((linha = reader.readLine()) != null) {
                // Lê cada linha do arquivo até que o final do arquivo seja alcançado
                // A função readLine() retorna a linha lida ou null se o final do arquivo for alcançado

                System.out.println(linha); // Exibe a linha lida no console
            }
        } catch (IOException e) {
            // Captura e trata a exceção caso ocorra um erro de E/S (Input/Output)
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
