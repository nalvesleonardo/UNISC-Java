//Classe filha de Usuario
import java.util.Scanner;

class Aluno extends Usuario {
    public Aluno(String nome, String senha, String email, String telefone, boolean mensalidadePaga) {
        super(nome, senha, email, telefone, mensalidadePaga);
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\nMenu do Aluno:");
        System.out.println("1. Verificar estado da mensalidade");

        if (isMensalidadePaga()) {
            System.out.println("2. Agendar treino particular com professor");
            System.out.println("3. Visualizar tabela de treinos");
            System.out.println("4. Enviar feedback");
            System.out.println("5. Deslogar");
            System.out.println("6. Sair");
        } else {
            System.out.println("2. Realizar pagamento da mensalidade");
            System.out.println("5. Deslogar");
            System.out.println("6. Sair");
        }
    }

    public void pagarMensalidade() {
        Scanner scanner = new Scanner(System.in);

        if (isMensalidadePaga()) {
            System.out.println("Sua mensalidade já está paga.");
            return;
        }

        System.out.println("Sua mensalidade está pendente. Deseja realizar o pagamento? (sim/nao)");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("sim")) {
            System.out.print("Digite o número do seu cartão: ");
            String numeroCartao = scanner.nextLine();
            System.out.print("Digite o CVV do seu cartão: ");
            String cvv = scanner.nextLine();

            if (validarCartao(numeroCartao, cvv)) {
                setMensalidadePaga(true);
                System.out.println("Pagamento realizado com sucesso! Mensalidade paga.");
            } else {
                System.out.println("Falha no pagamento. Número do cartão ou CVV inválido.");
            }
        } else {
            System.out.println("Pagamento não realizado. Mensalidade pendente.");
        }
    }

    private boolean validarCartao(String numeroCartao, String cvv) {
        return numeroCartao.matches("\\d{16}") && cvv.matches("\\d{3}");
    }
    
}
