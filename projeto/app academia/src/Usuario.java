public class Usuario {
    private String nome;
    private String senha;
    private boolean mensalidadePaga;

    public Usuario(String nome, String senha, boolean mensalidadePaga) {
        this.nome = nome;
        this.senha = senha;
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

    public boolean isMensalidadePaga() {
        return mensalidadePaga;
    }

    public void setMensalidadePaga(boolean mensalidadePaga) {
        this.mensalidadePaga = mensalidadePaga;
    }
}
