package com.example.bank;
 
import org.junit.Test;
import static org.junit.Assert.*;
 
public class TransactionServiceTest {
 
    @Test
    public void testTransferMovesMoney() {
        BankAccount from = new BankAccount("Alice", 200);
        BankAccount to = new BankAccount("Bob", 100);
        TransactionService service = new TransactionService();
 
        service.transfer(from, to, 50);
 
        assertEquals(150, from.getBalance(), 0.001);
        assertEquals(150, to.getBalance(), 0.001);
    }
 
    @Test(expected = IllegalArgumentException.class)
    public void testTransferFailsIfInsufficientFunds() {
        BankAccount from = new BankAccount("Alice", 20);
        BankAccount to = new BankAccount("Bob", 100);
        TransactionService service = new TransactionService();
 
        service.transfer(from, to, 50);
    }
}
