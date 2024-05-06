import java.util.Scanner;

class Revisao {
    public static void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        String nome;
        int idade;
        double nota1,nota2,media;

        System.out.print("Digite o nome: ");
        nome = teclado.nextLine();
        System.out.print("Digite a idade: ");
        idade = teclado.nextInt();
        System.out.print("Digite a nota 1: ");
        nota1 = teclado.nextDouble();
        System.out.print("Digite a nota 2: ");
        nota2 = teclado.nextDouble();
        teclado.close();

        media = (nota1+nota2)/2;

        System.out.println("Nome: " + nome);
        System.out.println("Idade: "+ idade);
        System.out.println("Média: " + media);
        if (media>=7){
            System.out.println("Resultado: Aprovado(a)");
        } else {
            System.out.println("Resultado: Reprovado(a)");
        }

    }
}
