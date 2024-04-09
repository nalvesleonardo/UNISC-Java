import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Fazenda fazenda = new Fazenda();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Incluir animal");
            System.out.println("2. Consultar animal");
            System.out.println("3. Avaliar animal");
            System.out.println("4. Registrar venda");
            System.out.println("5. Registrar perda");
            System.out.println("6. Relatório animais por tipo");
            System.out.println("7. Relatório vendas");
            System.out.println("8. Relatório perdas");
            System.out.println("9. Encerrar");

            System.out.print("\nEscolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    incluirAnimal(scanner, fazenda);
                    break;
                case 2:
                    consultarAnimal(scanner, fazenda);
                    break;
                case 3:
                    avaliarAnimal(scanner, fazenda);
                    break;
                case 4:
                    registrarVenda(scanner, fazenda);
                    break;
                case 5:
                    registrarPerda(scanner, fazenda);
                    break;
                case 6:
                    relatorioAnimaisPorTipo(scanner, fazenda);
                    break;
                case 7:
                    fazenda.relatorioVendas();
                    break;
                case 8:
                    fazenda.relatorioPerdas();
                    break;
                case 9:
                    System.out.println("Encerrando o programa...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void incluirAnimal(Scanner scanner, Fazenda fazenda) {
        System.out.print("\nDigite o ID do animal: ");
        String id = scanner.nextLine();
        if (fazenda.existeAnimalComId(id)) {
            System.out.println("Erro: Este ID já está em uso. Por favor, insira um ID único.");
            return;
        }
        System.out.print("Digite a altura do animal: ");
        double altura = scanner.nextDouble();
        System.out.print("Digite o comprimento do animal: ");
        double comprimento = scanner.nextDouble();
        System.out.print("Digite a largura do animal: ");
        double largura = scanner.nextDouble();
        System.out.print("Digite o peso do animal: ");
        double peso = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Digite o tipo do animal: ");
        String tipo = scanner.nextLine();

        Animal animal = new Animal(id, altura, comprimento, largura, peso, tipo);
        fazenda.incluirAnimal(animal);
        System.out.println("\nAnimal incluído com sucesso.");
    }

    private static void consultarAnimal(Scanner scanner, Fazenda fazenda) {
        System.out.print("\nDigite o ID do animal: ");
        String id = scanner.nextLine();
        Animal animal = fazenda.consultarAnimal(id);
        if (animal != null) {
            System.out.println("\nInformações do Animal:");
            System.out.println("ID: " + animal.getId());
            System.out.println("Altura: " + animal.getAltura()+"cm.");
            System.out.println("Comprimento: " + animal.getComprimento()+ "cm.");
            System.out.println("Largura: " + animal.getLargura()+"cm.");
            System.out.println("Peso: " + animal.getPeso()+"cm.");
            System.out.println("Tipo: " + animal.getTipo());
            System.out.println("Status: " + animal.getStatus());
        } else {
            System.out.println("Animal não encontrado.");
        }
    }

    private static void avaliarAnimal(Scanner scanner, Fazenda fazenda) {
        System.out.print("\nDigite o ID do animal: ");
        String id = scanner.nextLine();
        if (fazenda.existeAnimalComId(id) == false) {
            System.out.println("Erro: ID não encontrado.");
            return;
        }
        System.out.print("Digite a nova altura: ");
        double altura = scanner.nextDouble();
        System.out.print("Digite o novo comprimento: ");
        double comprimento = scanner.nextDouble();
        System.out.print("Digite a nova largura: ");
        double largura = scanner.nextDouble();
        System.out.print("Digite o novo peso: ");
        double peso = scanner.nextDouble();

        fazenda.avaliarAnimal(id, altura, comprimento, largura, peso);
    }

    private static void registrarVenda(Scanner scanner, Fazenda fazenda) {
        System.out.print("\nDigite o ID do animal vendido: ");
        String id = scanner.nextLine();
        if (fazenda.existeAnimalComId(id) == false) {
            System.out.println("Erro: ID não encontrado.");
            return;
        }
        System.out.print("Digite o valor da venda: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Digite a data da venda: ");
        String venda = scanner.nextLine();
        fazenda.registrarVenda(id, valor, venda);
    }

    private static void registrarPerda(Scanner scanner, Fazenda fazenda) {
        System.out.print("\nDigite o ID do animal perdido: ");
        String id = scanner.nextLine();
        if (fazenda.existeAnimalComId(id) == false) {
            System.out.println("Erro: ID não encontrado.");
            return;
        }
        System.out.print("Digite a data da perda: ");
        String perda = scanner.nextLine();
        fazenda.registrarPerda(id, perda);
    }

    private static void relatorioAnimaisPorTipo(Scanner scanner, Fazenda fazenda) {
        System.out.print("\nDigite o tipo de animal: ");
        String tipo = scanner.nextLine();

        fazenda.relatorioAnimaisPorTipo(tipo);
    }
}