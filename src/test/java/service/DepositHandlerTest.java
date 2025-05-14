package service;
import org.junit.jupiter.api.Test;
import org.demo.bank.model.Account;
import org.demo.bank.service.DepositHandler;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DepositHandlerTest {

    @Test
    public void testDeposit() {
        Account account = new Account("Name Surname");
        DepositHandler depositHandler = new DepositHandler();

        depositHandler.process("Salary", account, BigDecimal.valueOf(1000));

        assertEquals(BigDecimal.valueOf(1000), account.getBalance());
        assertEquals(1, account.getOperations().size());
    }

    @Test
    public void testDepositWithInvalidAmount() {
        Account account = new Account("Name Surname");
        DepositHandler depositHandler = new DepositHandler();

        assertThrows(IllegalArgumentException.class, () ->
                depositHandler.process("Invalid Deposit", account, BigDecimal.valueOf(-500.0))
        );
    }
}
