import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ContaBancaria {
    private final String titular;
    private BigDecimal saldo = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);

    public ContaBancaria(String titular) {
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("Titular é obrigatório.");
        }
        this.titular = titular.trim();
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal valor) {
        validarValorPositivo(valor);
        saldo = saldo.add(valor).setScale(2, RoundingMode.HALF_EVEN);
    }

    public boolean sacar(BigDecimal valor) {
        validarValorPositivo(valor);
        if (saldo.compareTo(valor) < 0) {
            return false;
        }
        saldo = saldo.subtract(valor).setScale(2, RoundingMode.HALF_EVEN);
        return true;
    }

    private static void validarValorPositivo(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }
}
