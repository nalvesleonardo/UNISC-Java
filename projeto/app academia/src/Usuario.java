// classe pai de Aluno e Professor > Encapsulamento e herança
abstract class Usuario {
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    private boolean mensalidadePaga;

    public Usuario(String nome, String senha, String email, String telefone, boolean mensalidadePaga) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.mensalidadePaga = mensalidadePaga;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public boolean isMensalidadePaga() {
        return mensalidadePaga;
    }

    public void setMensalidadePaga(boolean mensalidadePaga) {
        this.mensalidadePaga = mensalidadePaga;
    }

    public abstract void mostrarMenu();
}