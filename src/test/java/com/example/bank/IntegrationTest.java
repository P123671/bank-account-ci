package com.example.bank;
 
import org.junit.Test;
import static org.junit.Assert.*;
 
public class IntegrationTest {
 
    @Test
    public void testFullWorkflow() {
        BankAccount alice = new BankAccount("Alice", 500);
        BankAccount bob = new BankAccount("Bob", 100);
        TransactionService service = new TransactionService();
 
        // Deposit to Alice’s account
        alice.deposit(100);
        assertEquals(600, alice.getBalance(), 0.001);
 
        // Transfer to Bob
        service.transfer(alice, bob, 200);
 
        assertEquals(400, alice.getBalance(), 0.001);
        assertEquals(300, bob.getBalance(), 0.001);
    }
}
