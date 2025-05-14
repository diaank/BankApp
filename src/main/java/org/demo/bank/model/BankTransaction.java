package org.demo.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankTransaction {

    private String title;
    private BankTransactionType type;
    private BigDecimal amount;
    private LocalDateTime date;

    public BankTransaction(String title, BankTransactionType type, BigDecimal amount, LocalDateTime date) {
        this.title = title;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public BankTransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}