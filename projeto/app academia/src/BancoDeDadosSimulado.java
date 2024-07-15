import java.util.HashMap;
import java.util.Map;

public class BancoDeDadosSimulado {
    private Map<String, Usuario> usuarios;
    private Professor professor;

    public BancoDeDadosSimulado() { //usuarios pre prontos
        usuarios = new HashMap<>();
        usuarios.put("madu", new Aluno("Maria Eduarda", "1234", "mariaeduarda2024@gmail.com", "51998540392", true));
        usuarios.put("carlos", new Aluno("Carlos Arenques", "1234", "carlosarenques@gmail.com", "51997548327", false));

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