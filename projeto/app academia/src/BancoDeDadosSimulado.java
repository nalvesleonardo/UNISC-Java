import java.util.HashMap;
import java.util.Map;

public class BancoDeDadosSimulado {
    private Map<String, Usuario> usuarios;
    private Professor professor;

    public BancoDeDadosSimulado() { //usarios pre prontos
        usuarios = new HashMap<>();
        usuarios.put("madu2024", new Usuario("Maria Eduarda", "1234", "mariaeduarda2024@gmail.com", "51998540392", true));
        usuarios.put("carlos2024", new Usuario("Carlos Arenques", "1234", "carlosarenques@gmail.com", "51997548327", false));

        Treino[] treinos = {
            new Treino("Treino de força"),
            new Treino("Treino de resistência"),
            new Treino("Treino cardiovascular")
        };
        professor = new Professor("Professor Eduardo", treinos);
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