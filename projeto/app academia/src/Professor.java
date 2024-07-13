import java.util.Random;
import java.util.Scanner;

public class Professor {
    private String nome;
    private Treino[] treinos;

    public Professor(String nome, Treino[] treinos) {
        this.nome = nome;
        this.treinos = treinos;
    }

    public String getNome() {
        return nome;
    }

    public Treino fornecerTreinoAleatorio() {
        Random random = new Random();
        int indice = random.nextInt(treinos.length);
        return treinos[indice];
    }

    public static void main(String[] args) {
        Treino[] treinos1 = {new Treino("Treino A"), new Treino("Treino B")};
        Treino[] treinos2 = {new Treino("Treino C"), new Treino("Treino D")};
        Professor professor1 = new Professor("Professor 1", treinos1);
        Professor professor2 = new Professor("Professor 2", treinos2);

        Professor[] professores = {professor1, professor2};

        Scanner scanner = new Scanner(System.in);
        System.out.println("Professores disponíveis:");
        for (int i = 0; i < professores.length; i++) {
            System.out.println((i + 1) + ". " + professores[i].getNome());
        }
        System.out.print("Escolha o número do professor: ");
        int escolha = scanner.nextInt();

        if (escolha > 0 && escolha <= professores.length) {
            Professor professorEscolhido = professores[escolha - 1];
            Treino treinoAleatorio = professorEscolhido.fornecerTreinoAleatorio();
            System.out.println("Treino fornecido por " + professorEscolhido.getNome() + ": " + treinoAleatorio.getDescricao());
        } else {
            System.out.println("Escolha inválida.");
        }
    }
}
