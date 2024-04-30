import java.util.Scanner;

public class Aluno {
    private String nome;
    private int idade;
    private double nota;

    // Construtor
    public Aluno(String nome, int idade, double nota) {
        this.nome = nome;
        this.idade = idade;
        this.nota = nota;
    }

    // Métodos getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}

public class Escolinha {
    public static void main(String[] args) {
        // Criando um vetor para armazenar instâncias de alunos
        Aluno[] alunos = new Aluno[3];

        // Scanner para entrada de dados
        Scanner scanner = new Scanner(System.in);

        // Preenchendo o vetor com instâncias de alunos
        for (int i = 0; i < alunos.length; i++) {
            System.out.println("Digite o nome do aluno:");
            String nome = scanner.nextLine();

            System.out.println("Digite a idade do aluno:");
            int idade = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            System.out.println("Digite a nota do aluno:");
            double nota = scanner.nextDouble();
            scanner.nextLine(); // Limpar o buffer

            alunos[i] = new Aluno(nome, idade, nota);
        }

        // Exibindo os dados dos alunos
        for (Aluno aluno : alunos) {
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Idade: " + aluno.getIdade());
            System.out.println("Nota: " + aluno.getNota());
            System.out.println();
        }

        // Fechar o scanner
        scanner.close();
    }
}
