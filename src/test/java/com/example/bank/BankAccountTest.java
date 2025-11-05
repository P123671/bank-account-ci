package com.example.bank;
 
import org.junit.Test;
import static org.junit.Assert.*;
 
public class BankAccountTest {
 
    @Test
    public void testDepositIncreasesBalance() {
        BankAccount acc = new BankAccount("Alice", 100);
        acc.deposit(50);
        assertEquals(150, acc.getBalance(), 0.001);
    }
 
    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegativeThrowsException() {
        BankAccount acc = new BankAccount("Alice", 100);
        acc.deposit(-10);
    }
 
    @Test
    public void testWithdrawDecreasesBalance() {
        BankAccount acc = new BankAccount("Bob", 200);
        acc.withdraw(50);
        assertEquals(150, acc.getBalance(), 0.001);
    }
 
    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawTooMuchThrowsException() {
        BankAccount acc = new BankAccount("Bob", 100);
        acc.withdraw(200);
    }
 
    @Test
    public void testHasSufficientFundsTrue() {
        BankAccount acc = new BankAccount("Charlie", 300);
        assertTrue(acc.hasSufficientFunds(100));
    }
 
    @Test
    public void testHasSufficientFundsFalse() {
        BankAccount acc = new BankAccount("Charlie", 50);
        assertFalse(acc.hasSufficientFunds(100));
    }
}
