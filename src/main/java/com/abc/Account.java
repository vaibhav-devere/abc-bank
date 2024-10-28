package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;
    public static final int SUPER_SAVING = 3; // new account type
    public final List<Transaction> transactions;
    private final int accountType;
    private double transactionSum = 0.0; //  variable to store the sum of transactions,
    // preventing multiple iterations through the transaction list.

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<>(10); // Initial capacity can be adjusted
    }

    // Method to deposit an amount into the account
    public void deposit(double amount) {
        validateAmount(amount);
        transactions.add(new Transaction(amount));
        transactionSum += amount; // Update transaction Balance
    }

    // Method to withdraw an amount from the account
    public void withdraw(double amount) {
        validateAmount(amount);
        transactions.add(new Transaction(-amount));
        transactionSum -= amount; // Update transactionSum
    }

    /*
    To reduce Code Duplication
     */
    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
    }

    // Method to calculate the interest earned based on the account type
    public double interestEarned() {
        double amount = transactionSum; // Use transaction balance
        switch (accountType) {
            case SAVINGS:
                return calculateSavingsInterest(amount);
            case MAXI_SAVINGS:
                return calculateMaxiSavingsInterest(amount);
            case SUPER_SAVING:
                return calculateSuperSavingsInterest(amount);
            default:
                return amount * 0.001; // CHECKING account
        }
    }

    // Method to calculate interest for SAVINGS account
    private double calculateSavingsInterest(double amount) {
        if (amount <= 1000) {
            return amount * 0.001;
        } else {
            return 1 + (amount - 1000) * 0.002;
        }
    }

    // Method to calculate interest for MAXI_SAVINGS account
    private double calculateMaxiSavingsInterest(double amount) {
        if (amount <= 1000) {
            return amount * 0.02;
        }
        if (amount <= 2000) {
            return 20 + (amount - 1000) * 0.05;
        }
        return 70 + (amount - 2000) * 0.1;
    }

    // Method to calculate interest for SUPER_SAVINGS account
    private double calculateSuperSavingsInterest(double amount) {
        return calculateMaxiSavingsInterest(amount) + (amount * 0.02); // Maxi Savings interest + 2%

    }

    public void transferTo(Account targetAccount, double amount) {
        validateAmount(amount);
        this.withdraw(amount); // Withdraw from the current account
        targetAccount.deposit(amount); // Deposit into the target account
    }

    public double sumTransactions() {
        return transactionSum; // Return transaction sum
    }

    public int getAccountType() {
        return accountType;
    }
}