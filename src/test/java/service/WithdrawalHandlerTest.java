package service;

import org.junit.jupiter.api.Test;
import org.demo.bank.model.Account;
import org.demo.bank.service.WithdrawalHandler;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WithdrawalHandlerTest {

    @Test
    public void testWithdrawal() {
        Account account = new Account("Name Surname");
        account.updateBalance(BigDecimal.valueOf(1000));
        WithdrawalHandler withdrawalHandler = new WithdrawalHandler();

        withdrawalHandler.process("Grocery", account, BigDecimal.valueOf(200));

        assertEquals(BigDecimal.valueOf(800), account.getBalance());
        assertEquals(1, account.getOperations().size());
    }

    @Test
    public void testInsufficientBalance() {
        Account account = new Account("Name Surname");
        WithdrawalHandler withdrawalHandler = new WithdrawalHandler();

        assertThrows(IllegalArgumentException.class, () ->
            withdrawalHandler.process("Grocery", account, BigDecimal.valueOf(200))
        );
    }
}