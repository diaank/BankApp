package org.demo.bank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String ownerName;
    private BigDecimal balance;
    private List<BankTransaction> bankTransactions;

    public Account(String ownerName) {
        this.ownerName = ownerName;
        this.balance = BigDecimal.ZERO;
        this.bankTransactions = new ArrayList<>();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void updateBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public List<BankTransaction> getOperations() {
        return new ArrayList<>(bankTransactions);
    }

    public void addOperation(BankTransaction bankTransaction) {
        bankTransactions.add(bankTransaction);
    }
}
