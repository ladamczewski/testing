package pl.testing.account;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceStubTest {

    @Test
    void getAllActiveAccount(){
        //given
        AccountRepository accountRepositoryStab = new AccountRepositoryStub();
        AccountService accountService = new AccountService(accountRepositoryStab);

        //when
        List<Account> accountList = accountService.getAllActiveAccount();

        //then
        assertThat(accountList, hasSize(2));
    }

}