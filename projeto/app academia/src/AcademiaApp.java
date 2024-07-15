import java.util.*;

public class AcademiaApp {
    private static BancoDeDadosSimulado bancoDeDados = new BancoDeDadosSimulado();
    private static Usuario usuarioLogado = null;
    private static Scanner scanner = new Scanner(System.in);
    private static Professor[] professores;

    public static void main(String[] args) {
        try {
            inicializarProfessores();
            while (true) {
                realizarLogin(); // Chama a função de login
                exibirMenuPrincipal();
            }
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void inicializarProfessores() {
        Treino[] treinos1 = { new Treino("Puxada alta > Supino com barra > Desenvolvimento > Rosca com halteres > 30 min esteira") };
        Treino[] treinos2 = { new Treino("Abdutora > Cadeira Extensora > Elevação de panturrilha") };
        Professor professor1 = new Professor("Eduardo", treinos1);
        Professor professor2 = new Professor("Camila", treinos2);

        professores = new Professor[] { professor1, professor2 };
    }

    private static void realizarLogin() {
        while (usuarioLogado == null) {
            System.out.println("1. Login");
            System.out.println("2. Cadastro");
            System.out.print("Escolha uma opção: ");
            int opcao = lerOpcao();
            limparBufferScanner();

            if (opcao == 2) {
                cadastrarNovoUsuario();
            } else {
                System.out.print("Digite seu nome de usuário: ");
                String nome = scanner.nextLine();
                System.out.print("Digite sua senha: ");
                String senha = scanner.nextLine();

                Usuario usuario = bancoDeDados.getUsuario(nome);
                if (usuario != null && usuario.verificarSenha(senha)) {
                    usuarioLogado = usuario;
                    System.out.println("Login realizado com sucesso!");
                } else {
                    System.out.println("Nome de usuário ou senha incorretos.");
                }
            }
        }
    }

    private static void cadastrarNovoUsuario() {
        String nome;
        String email;
        String telefone;
        String senha;
        String confirmacaoSenha;

        do {    
            System.out.print("Digite seu nome de usuário: ");
            nome = scanner.nextLine();
            System.out.print("Digite seu email: ");
            email = scanner.nextLine();
            System.out.print("Digite seu telefone: ");
            telefone = scanner.nextLine();

            if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
                System.out.println("Todos os campos são obrigatórios. Tente novamente.");
                continue;
            }

            if (!isEmailValid(email)) {
                System.out.println("Email inválido. Tente novamente.");
                continue;
            }

            if (!isTelefoneValid(telefone)) {
                System.out.println("Telefone inválido. Tente novamente.");
                continue;
            }

            System.out.print("Digite sua senha: ");
            senha = scanner.nextLine();
            System.out.print("Confirme sua senha: ");
            confirmacaoSenha = scanner.nextLine();

            if (!senha.equals(confirmacaoSenha)) {
                System.out.println("As senhas não coincidem. Tente novamente.");
                continue;
            }
            Usuario novoUsuario;
            novoUsuario = new Aluno(nome, senha, email, telefone, false);

            try {
                bancoDeDados.adicionarUsuario(novoUsuario);
                System.out.println("Cadastro realizado com sucesso! Faça o login para continuar.");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private static boolean isTelefoneValid(String telefone) {
        return telefone.matches("\\d{10,11}"); 
    }

    private static void exibirMenuPrincipal() {
        int opcao;
        do {
            usuarioLogado.mostrarMenu();
            opcao = lerOpcao();
            limparBufferScanner();

            switch (opcao) {
                case 1:
                    verificarMensalidade();
                    break;
                case 2:
                    if (usuarioLogado.isMensalidadePaga()) {
                        escolherProfessor();
                    } else if (usuarioLogado instanceof Aluno) {
                        ((Aluno) usuarioLogado).pagarMensalidade();
                    } else {
                        System.out.println("Acesso restrito. Mensalidade pendente.");
                    }
                    break;
                case 3:
                    if (usuarioLogado.isMensalidadePaga()) {
                        verTabelaDeTreino();
                    } else {
                        System.out.println("Acesso restrito. Mensalidade pendente.");
                    }
                    break;
                case 4:
                    if (usuarioLogado.isMensalidadePaga()) {
                        enviarFeedback();
                    } else {
                        System.out.println("Acesso restrito. Mensalidade pendente.");
                    }
                    break;
                case 5:
                    deslogar();
                    return;
                case 6:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true);
    }

    private static void verificarMensalidade() {
        if (usuarioLogado.isMensalidadePaga()) {
            System.out.println("Sua mensalidade está paga.");
        } else {
            System.out.println("Sua mensalidade está pendente.");
        }
    }

    private static void escolherProfessor() {
        System.out.print("Digite a data da aula (dd/MM/yyyy): ");
        String data = scanner.nextLine();

        System.out.println("Professores disponíveis:");
        for (int i = 0; i < professores.length; i++) {
            System.out.println((i + 1) + ". " + professores[i].getNome());
        }
        System.out.print("Escolha o número do professor: ");
        int escolha = lerOpcao();

        if (escolha > 0 && escolha <= professores.length) {
            Professor professorEscolhido = professores[escolha - 1];
            Treino treinoAleatorio = professorEscolhido.fornecerTreinoAleatorio();
            System.out.println("Treino agendado: " + treinoAleatorio.getDescricao());
            System.out.println("Aula agendada para " + data + " com o(a) professor(a) " + professorEscolhido.getNome() + " com sucesso!");
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private static void verTabelaDeTreino() {
        Professor professor = professores[new Random().nextInt(professores.length)];
        Treino treino = professor.fornecerTreinoAleatorio();
        System.out.println("Seu treino diário é: " + treino.getDescricao());
    }

    private static void enviarFeedback() {
        System.out.print("Digite seu feedback: ");
        String feedback = scanner.nextLine();

        System.out.println("Obrigado pelo seu feedback: " + feedback);
    }

    private static void deslogar() {
        usuarioLogado = null;
        System.out.println("Você foi deslogado com sucesso!");
    }

    private static int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void limparBufferScanner() {
        scanner.nextLine();
    }
}
