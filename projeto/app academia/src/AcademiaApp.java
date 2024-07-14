import java.util.Random;
import java.util.Scanner;

public class AcademiaApp {
    private static BancoDeDadosSimulado bancoDeDados = new BancoDeDadosSimulado();
    private static Usuario usuarioLogado = null;
    private static Scanner scanner = new Scanner(System.in);
    private static Professor[] professores;

    public static void main(String[] args) {
        try {
            iniciar();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void iniciar() {
        System.out.println("Bem-vindo ao sistema da academia!");

        // Inicializar professores e treinos
        Treino[] treinos1 = {new Treino("Treino A"), new Treino("Treino B")};
        Treino[] treinos2 = {new Treino("Treino C"), new Treino("Treino D")};
        Professor professor1 = new Professor("Eduardo", treinos1);
        Professor professor2 = new Professor("Camila", treinos2);

        professores = new Professor[]{professor1, professor2};

        while (true) {
            realizarLogin(); // Chama a função de login

            int opcao;
            do {
                mostrarMenu(); // Menu de opções principal
                opcao = lerOpcao();

                switch (opcao) {
                    case 1:
                        verificarMensalidade();
                        break;
                    case 2:
                        escolherProfessor();
                        break;
                    case 3:
                        solicitarTreinoAleatorio();
                        break;
                    case 4:
                        atualizarPerfil();
                        break;
                    case 5:
                        visualizarHistoricoDeTreinos();
                        break;
                    case 6:
                        enviarFeedback();
                        break;
                    case 7:
                        System.out.println("Deslogando...\n\n");
                        usuarioLogado = null;
                        break;
                    case 8:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 7 && opcao != 8);
        }
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

            Usuario novoUsuario = new Usuario(nome, senha, email, telefone, false);

            try {
                bancoDeDados.adicionarUsuario(novoUsuario);
                System.out.println("Cadastro realizado com sucesso! Faça o login para continuar.\n");
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
        return telefone.matches("\\d{10,11}"); // Supondo um número de telefone com 10 ou 11 dígitos
    }

    private static void mostrarMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Verificar estado da mensalidade");
        System.out.println("2. Agendar treino particular com professor");
        System.out.println("3. Solicitar treino rápido aleatório");
        System.out.println("4. Atualizar perfil");
        System.out.println("5. Visualizar histórico de treinos");
        System.out.println("6. Enviar feedback");
        System.out.println("7. Deslogar");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void limparBufferScanner() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
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
        limparBufferScanner();
        String data = scanner.nextLine();

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
            System.out.println("Aula agendada para " + data + " com o professor " + professorEscolhido.getNome() + " sucesso!\n");
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private static void solicitarTreinoAleatorio() {
        Professor professor = professores[new Random().nextInt(professores.length)];
        Treino treino = professor.fornecerTreinoAleatorio();
        System.out.println("Seu treino aleatório é: " + treino.getDescricao());
    }

    private static void atualizarPerfil() {
        System.out.print("Digite seu novo nome de usuário: ");
        limparBufferScanner();
        String novoNome = scanner.nextLine();
        System.out.print("Digite sua nova senha: ");
        String novaSenha = scanner.nextLine();

        usuarioLogado.setNome(novoNome);
        usuarioLogado.setSenha(novaSenha);
        System.out.println("Perfil atualizado com sucesso!");
    }

    private static void visualizarHistoricoDeTreinos() {
        System.out.println("Histórico de Treinos:");
        System.out.println("1. Treino de força - 01/07/2024");
        System.out.println("2. Treino de resistência - 05/07/2024");
        System.out.println("3. Treino cardiovascular - 10/07/2024");
        System.out.println("Histórico de treinos visualizado com sucesso!");
    }

    private static void enviarFeedback() {
        System.out.print("Digite seu feedback: ");
        limparBufferScanner();
        String feedback = scanner.nextLine();

        System.out.println("Obrigado pelo seu feedback: " + feedback);
    }
}
