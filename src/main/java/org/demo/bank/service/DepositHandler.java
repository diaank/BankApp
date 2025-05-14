package org.demo.bank.service;

import org.demo.bank.model.Account;
import org.demo.bank.model.BankTransaction;
import org.demo.bank.model.BankTransactionType;
import org.demo.bank.util.BankTransactionValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositHandler implements TransactionHandler {

    @Override
    public void process(String transactionTitle, Account account, BigDecimal amount) {
        BankTransactionValidator.validateAmount(amount);
        account.updateBalance(amount);
        account.addOperation(new BankTransaction(transactionTitle, BankTransactionType.DEPOSIT, amount, LocalDateTime.now()));
    }
}