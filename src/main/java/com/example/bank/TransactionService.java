package com.example.bank;
 
public class TransactionService {
 
    public void transfer(BankAccount from, BankAccount to, double amount) {
        if (!from.hasSufficientFunds(amount)) {
            throw new IllegalArgumentException("Sender has insufficient funds");
        }
        from.withdraw(amount);
        to.deposit(amount);
    }
}
