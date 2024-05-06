import java.util.Scanner;

public class RevisaoArray {
    public static void main (String[] args){
        Scanner teclado = new Scanner(System.in);

        int quantidade = 15; // Quantidade de alunos no vetor
        String[] nome = new String[quantidade];
        double[] nota1 = new double[quantidade];
        double[] nota2 = new double[quantidade];
        double media,somaMedia;
        
        int i=0;
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
            System.out.println("Nome: " + nome[j] + " média: " + media );
        }
        System.out.println("Média da turma: " + somaMedia/i);
    }
        
}
