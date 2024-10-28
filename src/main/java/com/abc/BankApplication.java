package com.abc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData() {
         return args -> {
            // Create dummy accounts for users
            Account checkingAccount = new Account(Account.CHECKING);
            checkingAccount.deposit(1500);
            checkingAccount.withdraw(500);
            Account savingsAccount =     new Account(Account.SAVINGS);
            savingsAccount.deposit(2500);
            savingsAccount.withdraw(600);
            savingsAccount.transferTo(checkingAccount, 500);
            Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
            maxiSavingsAccount.deposit(3000);
            Account superSavingsAccount = new Account(Account.SUPER_SAVING);
            superSavingsAccount.deposit(4000);

            // Create customers and open accounts
            Customer customer1 = new Customer("jon Doe")
                    .openAccount(checkingAccount)
                    .openAccount(savingsAccount);
            Customer customer2 = new Customer("Vaibhav")
                    .openAccount(maxiSavingsAccount)
                    .openAccount(superSavingsAccount);

            // Print out the dummy data
            System.out.println(customer1.getStatement());
            System.out.println(customer2.getStatement());
        };
    }
}

