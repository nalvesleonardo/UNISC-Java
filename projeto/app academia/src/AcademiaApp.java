import java.util.Scanner;
import java.util.Random;

public class AcademiaApp {
    private static BancoDeDadosSimulado bancoDeDados = new BancoDeDadosSimulado();
    private static Usuario usuarioLogado = null;
    private static Scanner scanner = new Scanner(System.in);
    private static Object[] Professor;

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

        realizarLogin(); //chama a função de login

        int opcao;
        do {
            mostrarMenu(); //menu de opções principal
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    verificarMensalidade();
                    break;
                case 2:
                    EscolherumProfessor();
                    break;
                case 3:
                    solicitarTreinoAleatorio();
                    break;
                case 4:
                    atualizarPerfil();
                    break;
                case 5:
                    agendarAula();
                    break;
                case 6:
                    visualizarHistoricoDeTreinos();
                    break;
                case 7:
                    enviarFeedback();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);
    }

    private static void realizarLogin() { //metodo de login ou cadastro
        System.out.println("1. Login");
        System.out.println("2. Cadastro");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();
        scanner.nextLine(); // Limpa o buffer do scanner

        if (opcao == 2) {
            cadastrarNovoUsuario();
        } else {
            while (usuarioLogado == null) {
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
        System.out.print("Digite seu nome de usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nome, senha, false); 
        try {
            bancoDeDados.adicionarUsuario(novoUsuario);
            System.out.println("Cadastro realizado com sucesso! Faça o login para continuar.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Verificar estado da mensalidade");
        System.out.println("2. Escolher um Professor");
        System.out.println("3. Solicitar treino aleatório");
        System.out.println("4. Atualizar perfil");
        System.out.println("5. Agendar aula");
        System.out.println("6. Visualizar histórico de treinos");
        System.out.println("7. Enviar feedback");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() { //verifica se o numero digitado é valido
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void verificarMensalidade() { //verifica o estado da mensalidade
        if (usuarioLogado.isMensalidadePaga()) {
            System.out.println("Sua mensalidade está paga.");
        } else {
            System.out.println("Sua mensalidade está pendente.");
        }
    }
    
  private static void EscolherumProfessor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Professores disponíveis:");
        for (int i = 0; i < Professor.length; i++) {
            System.out.println((i + 1) + ". " + Professor[i].getNome());
        }
        System.out.print("Escolha o número do professor: ");
        int escolha = scanner.nextInt();

        if (escolha > 0 && escolha <= Professor.length) {
            Professor professorEscolhido = (Professor) Professor[escolha - 1];
            Treino treinoAleatorio = professorEscolhido.fornecerTreinoAleatorio();
            System.out.println("Treino fornecido por " + professorEscolhido.getNome() + ": " + treinoAleatorio.getDescricao());
        } else {
            System.out.println("Escolha inválida.");
        }
    }
    
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private static void solicitarTreinoAleatorio() { //solciita um treino aleatorio
        Professor professor = bancoDeDados.getProfessor();
        Treino treino = professor.fornecerTreinoAleatorio();
        System.out.println("Seu treino aleatório é: " + treino.getDescricao());
    }

    private static void atualizarPerfil() { //altera o usario e senha
        System.out.print("Digite seu novo nome de usuário: ");
        scanner.nextLine(); // Limpa o buffer do scanner
        String novoNome = scanner.nextLine();
        System.out.print("Digite sua nova senha: ");
        String novaSenha = scanner.nextLine();

        usuarioLogado.setNome(novoNome);
        usuarioLogado.setSenha(novaSenha);
        System.out.println("Perfil atualizado com sucesso!");
    }

    private static void agendarAula() { 
        System.out.print("Digite a data da aula (dd/MM/yyyy): ");
        scanner.nextLine(); // Limpa o buffer do scanner
        String data = scanner.nextLine();

        // Simulando o agendamento
        System.out.println("Aula agendada para " + data + " com sucesso!");
    }

    private static void visualizarHistoricoDeTreinos() {
        // Simulando o histórico de treinos
        System.out.println("Histórico de Treinos:");
        System.out.println("1. Treino de força - 01/07/2024");
        System.out.println("2. Treino de resistência - 05/07/2024");
        System.out.println("3. Treino cardiovascular - 10/07/2024");
    }

    private static void enviarFeedback() {
        System.out.print("Digite seu feedback: ");
        scanner.nextLine(); 
        String feedback = scanner.nextLine();

        // Simulando o envio de feedback
        System.out.println("Obrigado pelo seu feedback: " + feedback);
    }
}
