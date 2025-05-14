package service;

import org.junit.jupiter.api.Test;
import org.demo.bank.model.Account;
import org.demo.bank.model.BankTransaction;
import org.demo.bank.model.BankTransactionType;
import org.demo.bank.service.AccountStatementService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountStatementServiceTest {

    @Test
    public void testGenerateStatement() {
        Account account = new Account("Name Surname");
        account.updateBalance(BigDecimal.valueOf(1000));
        AccountStatementService statementService = new AccountStatementService();

        String statement = statementService.generateStatement(account);

        assertTrue(statement.contains("Name Surname"));
        assertTrue(statement.contains("1000"));
    }

    @Test
    public void testGenerateStatementSortOrder() {
        Account account = new Account("Name Surname");

        // Add transactions with different dates
        account.addOperation(new BankTransaction("Deposit 1", BankTransactionType.DEPOSIT, BigDecimal.valueOf(500), LocalDateTime.now().minusDays(2)));
        account.addOperation(new BankTransaction("Deposit 2", BankTransactionType.DEPOSIT, BigDecimal.valueOf(1000), LocalDateTime.now().minusDays(1)));
        account.addOperation(new BankTransaction("Withdrawal", BankTransactionType.WITHDRAWAL, BigDecimal.valueOf(200), LocalDateTime.now()));

        AccountStatementService statementService = new AccountStatementService();
        String statement = statementService.generateStatement(account);

        // Verify the order of transactions in the statement
        int firstOccurrence = statement.indexOf("Withdrawal");
        int secondOccurrence = statement.indexOf("Deposit 2");
        int thirdOccurrence = statement.indexOf("Deposit 1");

        assertTrue(firstOccurrence < secondOccurrence);
        assertTrue(secondOccurrence < thirdOccurrence);
    }
}
