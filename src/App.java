import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static final ArrayList<ContaBancaria> contas = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            String mensagem = switch (opcao) {
                case 1 -> { criarConta(); yield ""; }
                case 2 -> { depositar(); yield ""; }
                case 3 -> { sacar(); yield ""; }
                case 4 -> { consultarSaldo(); yield ""; }
                case 5 -> { yield "Saindo do sistema. Obrigado!"; }
                default -> { yield "Opção inválida. Tente novamente."; }
            };

            if (!mensagem.isEmpty()) {
                System.out.println(mensagem);
            }

        } while (opcao != 5);
    }

    private static void exibirMenu() {
        System.out.println("\n=== Banco Console ===");
        System.out.println("1. Criar conta");
        System.out.println("2. Depositar");
        System.out.println("3. Sacar");
        System.out.println("4. Consultar saldo");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarConta() {
        System.out.print("Digite o nome do titular: ");
        String titular = scanner.nextLine();
        contas.add(new ContaBancaria(titular));
        System.out.println("Conta criada com sucesso!");
    }

    private static void depositar() {
        ContaBancaria conta = selecionarConta();
        if (conta != null) {
            System.out.print("Digite o valor do depósito: ");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // limpar buffer
            conta.depositar(valor);
            System.out.println("Depósito realizado com sucesso!");
        }
    }

    private static void sacar() {
        ContaBancaria conta = selecionarConta();
        if (conta != null) {
            System.out.print("Digite o valor do saque: ");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // limpar buffer
            if (conta.sacar(valor)) {
                System.out.println("Saque realizado com sucesso!");
            } else {
                System.out.println("Saldo insuficiente!");
            }
        }
    }

    private static void consultarSaldo() {
        ContaBancaria conta = selecionarConta();
        if (conta != null) {
            System.out.printf("Saldo da conta de %s: R$ %.2f%n", conta.getTitular(), conta.getSaldo());
        }
    }

    private static ContaBancaria selecionarConta() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return null;
        }

        System.out.println("Selecione a conta:");
        for (int i = 0; i < contas.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, contas.get(i).getTitular());
        }

        int indice = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        if (indice < 1 || indice > contas.size()) {
            System.out.println("Opção inválida.");
            return null;
        }

        return contas.get(indice - 1);
    }
}

class ContaBancaria {

    private final String titular;
    private double saldo;

    public ContaBancaria(String titular) {
        this.titular = titular;
        this.saldo = 0.0;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }
}
