/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Carrington
 */
public class AccountTest {

    public AccountTest() {
    }

    @Test
    public void testTransfer() {
        Account a1 = new Account(0);
        Account a2 = new Account(0);
        assertEquals(0, a1.sumTransactions(), 0);
        a1.deposit(10000);
        a1.transferTo(a2, 5000);
        assertEquals(5000, a1.sumTransactions(), 0);
        assertEquals(5000, a2.sumTransactions(), 0);
    }
}
