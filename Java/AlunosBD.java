import java.util.Scanner;
import java.lang.Override;

Class Aluno {
    private String nome;
    private double nota1;
    private double nota2;

    // Construtor
    public Aluno(String nome,double nota1,double nota2) {
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota2() {
        return nota2;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " - Nota 1: " + nota1 + " - Nota 2: " + nota2;
    }
}

public class AlunosBD {
    public static void main (String[] args){
        Scanner teclado = new Scanner(System.in);

        int quantidade = 15; // Quantidade de alunos no vetor
        double media,somaMedia;

        List alunos = new ArrayList();
        String linha;
        int i=0;
        
        // Entrada de dados do arquivo (ler do arquivo)
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            // Cria um BufferedReader para ler o arquivo especificado

            while ((linha = reader.readLine()) != null) {
                // Lê cada linha do arquivo até que o final do arquivo seja alcançado
                // A função readLine() retorna a linha lida ou null se o final do arquivo for alcançado

                String[] texto = linha.split(";");
                Aluno aluno = new Aluno(texto[0],Double.parseDouble(texto[1]),Double.parseDouble(texto[2]))
            }
        } catch (IOException e) {
            // Captura e trata a exceção caso ocorra um erro de E/S (Input/Output)
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Entrada de dados pelo teclado
        while(true){
            System.out.print("Digitite o nome: ");
            String nome = teclado.nextLine();
            if (nome.equalsIgnoreCase("fim")){
                break;
            }

            System.out.print("Digite o nota 1: ");
            double nota1 = teclado.nextDouble();
            System.out.print("Digite o nota 2: ");
            double nota2 = teclado.nextDouble();
            teclado.nextLine();
            Alunos aluno = new Aluno(nome,nota1,nota);
        }
        teclado.close();

        somaMedia = 0;
        for(int j=0;j<i;j++){
            media = (nota1[j]+nota2[j])/2;
            somaMedia += media;
            System.out.println("Nome: " + nome[j].toUpperCase() + " - Média: " + media );

            // Grava no arquivo
            linha = nome[j]+";"+nota1[j]+";"+nota2[j];
            gravarNoBD(arquivo, linha);
        }
        System.out.println("Média da turma: " + somaMedia/i);
    }

    public static void gravarNoBD(String arquivo, String linha) {
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
