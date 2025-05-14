package org.demo.bank.service;

import org.demo.bank.model.Account;
import org.demo.bank.model.BankTransaction;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class AccountStatementService {

    public String generateStatement(Account account) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        StringBuilder statement = new StringBuilder();
        statement.append("Account Statement for ").append(account.getOwnerName()).append("\n");
        statement.append("Balance: ").append(account.getBalance()).append("\n");
        statement.append("Account history:\n");

        account.getOperations().stream()
                .sorted(Comparator.comparing(BankTransaction::getDate).reversed())
                .forEach(bankTransaction -> statement.append(formatter.format(bankTransaction.getDate()))
                        .append(": ")
                        .append(bankTransaction.getTitle())
                        .append(" - ")
                        .append(bankTransaction.getType())
                        .append(" - ")
                        .append(bankTransaction.getAmount())
                        .append("\n"));

        return statement.toString();
    }

}
