package pl.testing.account;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

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

    @Test
    void newAccountWithNotNullAddressShouldBeActive(){
        //given
        Address address = new Address("Puławska", "111");

        //when
        Account account = new Account(address);

        //then
        assumingThat( address != null, () -> {
            assertTrue(account.isActive());
        });
    }

    @Test
    void invalidEmailShouldThrowException(){
        //given
        Account account = new Account();

        //when
        //then
        assertThrows(IllegalArgumentException.class, ()->account.setEmail("ubububu"));
    }
    @Test
    void validEmailShouldBeNotNull(){
        //given
        Account account = new Account();

        //when
        account.setEmail("bubu@wp.pl");
        //then
        assertThat(account.getEmail(), is("bubu@wp.pl"));
    }

}