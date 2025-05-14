package service;

import org.junit.jupiter.api.Test;
import org.demo.bank.model.Account;
import org.demo.bank.model.BankTransactionType;
import org.demo.bank.service.TransactionProcessor;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionProcessorTest {

    @Test
    public void testTransactionProcessor() {
        Account account = new Account("Name Surname");
        TransactionProcessor processor = new TransactionProcessor();

        processor.execute(BankTransactionType.DEPOSIT, "Salary", account, BigDecimal.valueOf(1000));
        processor.execute(BankTransactionType.WITHDRAWAL, "Grocery", account, BigDecimal.valueOf(200));

        assertEquals(BigDecimal.valueOf(800), account.getBalance());
        assertEquals(2, account.getOperations().size());
    }

    @Test
    public void testInvalidTransactionType() {
        Account account = new Account("Name Surname");
        TransactionProcessor processor = new TransactionProcessor();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                processor.execute(null, "Invalid Type", account, BigDecimal.valueOf(100))
        );

        assertEquals("No handler found for transaction type: null", exception.getMessage());
        assertEquals(BigDecimal.ZERO, account.getBalance());
    }
}
