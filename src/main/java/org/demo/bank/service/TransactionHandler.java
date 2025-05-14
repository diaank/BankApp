package org.demo.bank.service;

import org.demo.bank.model.Account;

import java.math.BigDecimal;

public interface TransactionHandler {
    void process(String transactionTitle, Account account, BigDecimal amount);
}
