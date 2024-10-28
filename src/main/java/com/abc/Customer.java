package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name; // Customer's name
    private final List<Account> accounts; // List to hold customer's accounts

    // Constructor to initialize customer's name and accounts list
    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>(); // Using diamond operator
    }

    public String getName() {
        return name;
    }

    // Method to open a new account for the customer
    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    // Method to get the number of accounts the customer has
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    // Method to calculate total interest earned across all accounts
    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts) {
            total += a.interestEarned();
        }
        return total; // Return the total interest earned
    }

    // Method to generate a statement for the customer
    public String getStatement() {
        StringBuilder statement = new StringBuilder(); // Using StringBuilder for better performance
        statement.append("Statement for ").append(name).append("\n");
        double total = 0.0;

        for (Account a : accounts) {
            statement.append("\n").append(statementForAccount(a)).append("\n"); // Append account statement
            total += a.sumTransactions(); // Sum up all transactions for the total
        }

        statement.append("\nTotal In All Accounts ").append(toDollars(total));
        return statement.toString(); // Convert StringBuilder to String and return
    }

    // Method to generate a statement for a specific account
    private String statementForAccount(Account a) {
        StringBuilder s = new StringBuilder(); // Using StringBuilder for better performance and Readability

        // Account Type
        switch (a.getAccountType()) {
            case Account.CHECKING:
                s.append("Checking Account\n");
                break;
            case Account.SAVINGS:
                s.append("Savings Account\n");
                break;
            case Account.MAXI_SAVINGS:
                s.append("Maxi Savings Account\n");
                break;
        }

        // Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s.append("  ").append(t.amount < 0 ? "withdrawal" : "deposit")
                    .append(" ").append(toDollars(t.amount)).append("\n");
            total += t.amount;
        }
        s.append("Total ").append(toDollars(total));
        return s.toString(); // Convert StringBuilder to String
    }

    // New method to facilitate transferring funds between accounts
    public void transferBetweenAccounts(Account fromAccount, Account toAccount, double amount) {
        fromAccount.transferTo(toAccount, amount); // Use the transfer method from Account
    }

    private String toDollars(double d) {
        return String.format("$%,.2f", d); // Removed unnecessary Math.abs
    }
}
