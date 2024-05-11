import java.util.Scanner;

public class AuloObjeto{
    public static void main (String[] args){
        Scanner teclado = new Scanner(System.in);

        int quantidade = 100;
        Aluno[] aluno = new Aluno[quantidade];

        int i = 0;
        while (i<quantidade){
            System.out.print("Digitite o nome: ");
            aluno[i] = new Aluno();
            aluno[i].setNome(teclado.nextLine());
            if (aluno[i].getNome().equalsIgnoreCase("fim")){
                break;
            }
            System.out.print("Digitite o nota 1: ");
            aluno[i].setNota1(teclado.nextDouble());
            System.out.print("Digitite o nota 2: ");
            aluno[i].setNota2(teclado.nextDouble());
            teclado.nextLine();
            
            i++;
        }

        int somaMedia=0;
        for(int j=0;j<i;j++){
            System.out.println(aluno[j].getNome() + " - " + aluno[j].getMedia());
            somaMedia += aluno[j].getMedia();
        }
        System.out.println("Média da turma: " + somaMedia/i);
    }
}

class Aluno {
    private String nome;
    private double nota1;
    private double nota2;

    void setNome(String nome) {
        this.nome = nome;
    }

    String getNome() {
        return nome;
    }

    void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    void setNota2(double nota2) {
        this.nota2 = nota2;
    }
    double getMedia(){
        return (nota1+nota2)/2;
    }
}
