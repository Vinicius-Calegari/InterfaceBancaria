import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final List<ContaBancaria> contas = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro();
            switch (opcao) {
                case 1 -> criarConta();
                case 2 -> depositar();
                case 3 -> sacar();
                case 4 -> consultarSaldo();
                case 5 -> System.out.println("Saindo do sistema. Obrigado!");
                default -> System.out.println("Opção inválida. Tente novamente.");
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
        String titular = scanner.nextLine().trim();
        try {
            contas.add(new ContaBancaria(titular));
            System.out.println("Conta criada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void depositar() {
        ContaBancaria conta = selecionarConta();
        if (conta == null) return;
        try {
            System.out.print("Digite o valor do depósito: ");
            conta.depositar(lerValorMonetario());
            System.out.println("Depósito realizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sacar() {
        ContaBancaria conta = selecionarConta();
        if (conta == null) return;
        try {
            System.out.print("Digite o valor do saque: ");
            System.out.println(conta.sacar(lerValorMonetario()) ? "Saque realizado com sucesso!" : "Saldo insuficiente!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void consultarSaldo() {
        ContaBancaria conta = selecionarConta();
        if (conta != null) System.out.printf("Saldo da conta de %s: R$ %s%n", conta.getTitular(), conta.getSaldo().toPlainString());
    }

    private static ContaBancaria selecionarConta() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return null;
        }
        System.out.println("Selecione a conta:");
        for (int i = 0; i < contas.size(); i++) System.out.printf("%d. %s%n", i + 1, contas.get(i).getTitular());
        int indice = lerInteiro();
        if (indice < 1 || indice > contas.size()) {
            System.out.println("Opção inválida.");
            return null;
        }
        return contas.get(indice - 1);
    }

    private static int lerInteiro() {
        while (true) {
            String entrada = scanner.nextLine().trim();
            try { return Integer.parseInt(entrada); }
            catch (NumberFormatException e) { System.out.print("Digite um número válido: "); }
        }
    }

    private static BigDecimal lerValorMonetario() {
        String entrada = scanner.nextLine().trim().replace(',', '.');
        try { return new BigDecimal(entrada); }
        catch (NumberFormatException e) { throw new IllegalArgumentException("Valor monetário inválido."); }
    }
}
