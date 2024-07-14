import java.util.Random;

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
}
