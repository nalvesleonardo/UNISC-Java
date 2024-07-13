import java.util.HashMap;
import java.util.Map;

public class BancoDeDadosSimulado {
    private Map<String, Usuario> usuarios;
    private Professor professor;

    public BancoDeDadosSimulado() { //usarios pre prontos
        usuarios = new HashMap<>();
        usuarios.put("user1", new Usuario("user1", "senha1", true));
        usuarios.put("user2", new Usuario("user2", "senha2", false));

        Treino[] treinos = {
            new Treino("Treino de força"),
            new Treino("Treino de resistência"),
            new Treino("Treino cardiovascular")
        };
        professor = new Professor("Professor Carlos", treinos);
    }

    public Usuario getUsuario(String nome) {
        return usuarios.get(nome);
    }

    public void adicionarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getNome())) {
            throw new IllegalArgumentException("Usuário já existe.");
        }
        usuarios.put(usuario.getNome(), usuario);
    }

    public Professor getProfessor() {
        return professor;
    }
}
