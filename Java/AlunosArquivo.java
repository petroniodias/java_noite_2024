import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AlunosArquivo {
    public static void main (String[] args){
        Scanner teclado = new Scanner(System.in);

        int quantidade = 15; // Quantidade de alunos no vetor
        String[] nome = new String[quantidade];
        double[] nota1 = new double[quantidade];
        double[] nota2 = new double[quantidade];
        double media,somaMedia;
        String arquivo = "alunos.txt";
        String linha;
        int i=0;
        
        // Entrada de dados do arquivo (ler do arquivo)
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            // Cria um BufferedReader para ler o arquivo especificado

            while ((linha = reader.readLine()) != null) {
                // Lê cada linha do arquivo até que o final do arquivo seja alcançado
                // A função readLine() retorna a linha lida ou null se o final do arquivo for alcançado

                String[] texto = linha.split(";");
                nome[i] = texto[0];
                // nota1[i] = texto[1];
                // nota2[i] = texto[2];
                i++;
            }
        } catch (IOException e) {
            // Captura e trata a exceção caso ocorra um erro de E/S (Input/Output)
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Entrada de dados pelo teclado
        while(i<quantidade){
            System.out.print("Digitite o nome: ");
            nome[i] = teclado.nextLine();
            if (nome[i].equalsIgnoreCase("fim")){
                break;
            }

            System.out.print("Digitite o nota 1: ");
            nota1[i] = teclado.nextDouble();
            System.out.print("Digitite o nota 2: ");
            nota2[i] = teclado.nextDouble();
            teclado.nextLine();
            i++;
        }
        teclado.close();

        somaMedia = 0;
        for(int j=0;j<i;j++){
            media = (nota1[j]+nota2[j])/2;
            somaMedia += media;
            System.out.println("Nome: " + nome[j].toUpperCase() + " - Média: " + media );

            // Grava no arquivo
            linha = nome[j]+";"+nota1[j]+";"+nota2[j];
            gravarNoArquivo(arquivo, linha);
        }
        System.out.println("Média da turma: " + somaMedia/i);
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
