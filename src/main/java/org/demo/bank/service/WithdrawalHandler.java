package org.demo.bank.service;

import org.demo.bank.model.Account;
import org.demo.bank.model.BankTransaction;
import org.demo.bank.model.BankTransactionType;
import org.demo.bank.util.BankTransactionValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WithdrawalHandler implements TransactionHandler {

    @Override
    public void process(String transactionTitle, Account account, BigDecimal amount) {
        BankTransactionValidator.validateAmount(amount);
        BankTransactionValidator.validateSufficientBalance(account.getBalance(), amount);
        account.updateBalance(amount.negate());
        account.addOperation(new BankTransaction(transactionTitle, BankTransactionType.WITHDRAWAL, amount, LocalDateTime.now()));
    }
}
