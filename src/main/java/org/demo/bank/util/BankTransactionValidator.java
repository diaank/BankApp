package org.demo.bank.util;

import java.math.BigDecimal;

public class BankTransactionValidator {

    public static void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }

    public static void validateSufficientBalance(BigDecimal balance, BigDecimal amount) {
        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }
}
