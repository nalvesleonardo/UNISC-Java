import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Adicionar funcionário");
            System.out.println("2. Mostrar funcionários");
            System.out.println("3. Editar funcionário");
            System.out.println("4. Excluir funcionário");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    adicionarFuncionario(scanner, funcionarios);
                    break;
                case 2:
                    mostrarFuncionarios(funcionarios);
                    break;
                case 3:
                    editarFuncionario(scanner, funcionarios);
                    break;
                case 4:
                    excluirFuncionario(scanner, funcionarios);
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }
    }

    private static void adicionarFuncionario(Scanner scanner, ArrayList<Funcionario> funcionarios) {
        Funcionario funcionario = new Funcionario();

        System.out.println("Digite o nome do funcionário:");
        String nome = scanner.nextLine();
        funcionario.setNome(nome);

        System.out.println("Digite o cargo do funcionário:");
        String cargoStr = scanner.nextLine();
        Cargo cargo = verificarCargo(cargoStr);
        if (cargo == null) {
            System.out.println("Cargo inválido. Por favor, escolha novamente.");
            return;
        }
        funcionario.setCargo(cargo);

        System.out.println("Digite o salário do funcionário:");
        double salario = scanner.nextDouble();
        funcionario.setSalario(salario);

        funcionarios.add(funcionario);
        System.out.println("Funcionário adicionado com sucesso!");
    }

    private static Cargo verificarCargo(String cargoStr) {
        try {
            return Cargo.valueOf(cargoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static void mostrarFuncionarios(ArrayList<Funcionario> funcionarios) {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados.");
            return;
        }

        System.out.println("Lista de funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Cargo: " + funcionario.getCargo());
            System.out.println("Salário: " + funcionario.getSalario());
            System.out.println();
        }
    }

    private static void editarFuncionario(Scanner scanner, ArrayList<Funcionario> funcionarios) {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados para editar.");
            return;
        }

        System.out.println("Digite o nome do funcionário que deseja editar:");
        String nome = scanner.nextLine();

        Funcionario funcionario = null;
        for (Funcionario f : funcionarios) {
            if (f.getNome().equalsIgnoreCase(nome)) {
                funcionario = f;
                break;
            }
        }

        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.println("Digite o novo nome do funcionário:");
        String novoNome = scanner.nextLine();
        funcionario.setNome(novoNome);

        System.out.println("Digite o novo cargo do funcionário:");
        String novoCargoStr = scanner.nextLine();
        Cargo novoCargo = verificarCargo(novoCargoStr);
        if (novoCargo == null) {
            System.out.println("Cargo inválido. O cargo não será alterado.");
        } else {
            funcionario.setCargo(novoCargo);
        }

        System.out.println("Digite o novo salário do funcionário:");
        double novoSalario = scanner.nextDouble();
        funcionario.setSalario(novoSalario);

        System.out.println("Funcionário editado com sucesso!");
    }

    private static void excluirFuncionario(Scanner scanner, ArrayList<Funcionario> funcionarios) {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados para excluir.");
            return;
        }

        System.out.println("Digite o nome do funcionário que deseja excluir:");
        String nome = scanner.nextLine();

        Funcionario funcionarioParaExcluir = null;
        for (Funcionario f : funcionarios) {
            if (f.getNome().equalsIgnoreCase(nome)) {
                funcionarioParaExcluir = f;
                break;
            }
        }

        if (funcionarioParaExcluir == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        funcionarios.remove(funcionarioParaExcluir);
        System.out.println("Funcionário excluído com sucesso!");
    }
}
