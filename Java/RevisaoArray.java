import java.util.Scanner;

public class RevisaoArray {
    public static void main (String[] args){
        Scanner teclado = new Scanner(System.in);

        int quantidade = 4; // Quantidade de alunos no vetor
        String[] nome = new String[quantidade];
        double[] nota1 = new double[quantidade];
        double[] nota2 = new double[quantidade];
        double media,somaMedia;
        
        int i=0;
        while(i<quantidade){
            System.out.print("Digitite o nome: ");
            nome[i] = teclado.nextLine();
            System.out.print("Digitite o nota 1: ");
            nota1[i] = teclado.nextDouble();
            System.out.print("Digitite o nota 2: ");
            nota2[i] = teclado.nextDouble();
            teclado.nextLine();
            i++;
        }
        teclado.close();

        somaMedia = 0;
        for(i=0;i<quantidade;i++){
            media = (nota1[i]+nota2[i])/2;
            somaMedia += media;
            System.out.println("Nome: " + nome[i] + " média: " + media );
        }
        System.out.println("Média da turma: " + somaMedia/quantidade);
    }
        
}
