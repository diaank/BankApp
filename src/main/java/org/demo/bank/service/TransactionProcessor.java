package org.demo.bank.service;

import org.demo.bank.model.Account;
import org.demo.bank.model.BankTransactionType;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class TransactionProcessor {

    private final Map<BankTransactionType, TransactionHandler> handlers = new EnumMap<>(BankTransactionType.class);

    public TransactionProcessor() {
        handlers.put(BankTransactionType.DEPOSIT, new DepositHandler());
        handlers.put(BankTransactionType.WITHDRAWAL, new WithdrawalHandler());
    }

    public void execute(BankTransactionType type, String transactionTitle, Account account, BigDecimal amount) {
        TransactionHandler handler = handlers.get(type);
        if (handler == null) {
            throw new IllegalArgumentException("No handler found for transaction type: " + type);
        }
        handler.process(transactionTitle, account, amount);
    }
}