package org.demo.bank;

import org.demo.bank.model.Account;
import org.demo.bank.model.BankTransactionType;
import org.demo.bank.service.TransactionProcessor;
import org.demo.bank.service.AccountStatementService;

import java.math.BigDecimal;

public class BankApp {
    public static void main(String[] args) {
        //Example flow of the banking application flow only for demonstration purposes
        //TODO: Delete method content in further development
        Account account = new Account("Demo User");

        TransactionProcessor processor = new TransactionProcessor();

        // Deposit transaction
        processor.execute(BankTransactionType.DEPOSIT, "Salary Deposit", account, BigDecimal.valueOf(1000));

        // Simulate a delay for the example on the account statement
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Withdrawal transaction
        processor.execute(BankTransactionType.WITHDRAWAL, "Grocery Shopping", account, BigDecimal.valueOf(200));

        // Generate account statement
        AccountStatementService statementService = new AccountStatementService();
        String statement = statementService.generateStatement(account);
        System.out.println(statement);
    }
}