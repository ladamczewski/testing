package pl.testing.account;

import java.util.Arrays;
import java.util.List;

public class AccountRepositoryStub implements AccountRepository{
    @Override
    public List<Account> getAllAccounts() {
        Address address = new Address("Dudka", "12A");
        Account account = new Account(address);
        Address address1 = new Address("Marii", "112N");
        Account account1 = new Account(address1);
        Account account2 = new Account();
        return Arrays.asList(account, account1, account2);
    }
}
