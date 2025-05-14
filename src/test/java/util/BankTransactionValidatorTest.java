package util;
import org.junit.jupiter.api.Test;
import org.demo.bank.util.BankTransactionValidator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankTransactionValidatorTest {

    @Test
    public void testValidateAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            BankTransactionValidator.validateAmount(BigDecimal.valueOf(-100))
        );
    }

    @Test
    public void testValidateSufficientBalanceThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            BankTransactionValidator.validateSufficientBalance(BigDecimal.valueOf(100), BigDecimal.valueOf(200))
        );
    }

    @Test
    public void testValidateAmountSuccess() {
        assertDoesNotThrow(() ->
                BankTransactionValidator.validateAmount(BigDecimal.valueOf(100))
        );
    }

    @Test
    public void testValidateSufficientBalanceSuccess() {
        assertDoesNotThrow(() ->
                BankTransactionValidator.validateSufficientBalance(BigDecimal.valueOf(200), BigDecimal.valueOf(100))
        );
    }
}
