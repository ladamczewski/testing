package pl.testing.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AccountServiceTest {

    @Test
    void getAllActiveAccount(){
        //given
        List<Account> accounts = preparedAccount();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(accounts);

        //when
        List<Account> accountList = accountService.getAllActiveAccount();

        //then
        assertThat(accountList, hasSize(2));
    }

    @Test
    void getNoActiveAccount(){
        //given
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(Collections.emptyList());

        //when
        List<Account> accountList = accountService.getAllActiveAccount();

        //then
        assertThat(accountList, hasSize(0));
    }
    private List<Account> preparedAccount(){
        Address address = new Address("Dudka", "12A");
        Account account = new Account(address);
        Address address1 = new Address("Marii", "112N");
        Account account1 = new Account(address1);
        Account account2 = new Account();
        return Arrays.asList(account, account1, account2);
    }
}
