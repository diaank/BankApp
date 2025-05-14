package integration;

import org.junit.jupiter.api.Test;
import org.demo.bank.model.Account;
import org.demo.bank.model.BankTransactionType;
import org.demo.bank.service.AccountStatementService;
import org.demo.bank.service.TransactionProcessor;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTransactionIntegrationTest {

    @Test
    public void testFullBankingFlow() {
        // Create account
        Account account = new Account("Name Surname");

        // Initialize services
        TransactionProcessor transactionProcessor = new TransactionProcessor();
        AccountStatementService statementService = new AccountStatementService();

        // Perform deposit
        transactionProcessor.execute(BankTransactionType.DEPOSIT, "Salary", account, BigDecimal.valueOf(1000.31));
        assertEquals(BigDecimal.valueOf(1000.31), account.getBalance());

        // Perform withdrawal
        transactionProcessor.execute(BankTransactionType.WITHDRAWAL, "Groceries", account, BigDecimal.valueOf(200.35));
        assertEquals(BigDecimal.valueOf(799.96), account.getBalance());

        // Generate account statement
        String statement = statementService.generateStatement(account);
        assertTrue(statement.contains("Name Surname"));
        assertTrue(statement.contains("Salary"));
        assertTrue(statement.contains("Groceries"));
        assertTrue(statement.contains("799.96"));
    }

    @Test
    public void testDepositNegativeAmount() {
        // Create account
        Account account = new Account("Name Surname");

        // Initialize services
        TransactionProcessor transactionProcessor = new TransactionProcessor();

        // Attempt to deposit a negative amount
        Exception exception = null;
        try {
            transactionProcessor.execute(BankTransactionType.DEPOSIT, "Invalid Deposit", account, BigDecimal.valueOf(-100.00));
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        assertTrue(exception instanceof IllegalArgumentException);
        assertEquals("Amount must be greater than zero", exception.getMessage());
        assertEquals(BigDecimal.ZERO, account.getBalance());
    }

    @Test
    public void testWithdrawalExceedingBalance() {
        // Create account
        Account account = new Account("Name Surname");

        // Initialize services
        TransactionProcessor transactionProcessor = new TransactionProcessor();

        // Attempt to withdraw more than the balance
        Exception exception = null;
        try {
            transactionProcessor.execute(BankTransactionType.WITHDRAWAL, "Overdraft", account, BigDecimal.valueOf(500));
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        assertTrue(exception instanceof IllegalArgumentException);
        assertEquals("Insufficient balance", exception.getMessage());
        assertEquals(BigDecimal.ZERO, account.getBalance());
    }

    @Test
    public void testInvalidTransactionType() {
        // Create account
        Account account = new Account("Name Surname");

        // Initialize services
        TransactionProcessor transactionProcessor = new TransactionProcessor();

        // Attempt an invalid transaction type
        Exception exception = null;
        try {
            transactionProcessor.execute(null, "Invalid Type", account, BigDecimal.valueOf(100.00));
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        assertTrue(exception instanceof IllegalArgumentException);
        assertTrue(exception.getMessage().contains("No handler found for transaction type"));
        assertEquals(BigDecimal.ZERO, account.getBalance());
    }
}