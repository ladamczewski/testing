package pl.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void newAccountIsNotActive(){
        //given
        Account newAccount = new Account();
        //then
        assertFalse(newAccount.isActive());
    }

    @Test
    void activateFunctionIsWorking(){
        //given
        Account newAccount = new Account();
        assertFalse(newAccount.isActive());
        //when
        newAccount.activate();
        //then
        assertTrue(newAccount.isActive());
    }

    @Test
    void createdNewAccountShouldNotHaveDefaultDeliveryAddress(){
        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterSet(){
        //given
        Account account = new Account();

        //when
        account.setDefaultDeliveryAddress(new Address("Słodka", "11"));

        //then
        assertNotNull(account.getDefaultDeliveryAddress());
    }
}