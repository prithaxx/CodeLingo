package CodeLinguists.codelingo.persistence.stubs;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.persistence.IAccountData;

public class AccountDataStub implements IAccountData {
    private final List<AccountObj> guestAccounts;

    public AccountDataStub() {
        guestAccounts = new ArrayList<>();
    }

    @Override
    public AccountObj getGuestAccountByName(String name) {
        for (AccountObj account:this.guestAccounts) {
            if (name.equals(account.getName())){
                return account;
            }
        }
        return null;
    }


    @Override
    public AccountObj createGuestAccount(String name) {
        AccountObj newAccount = new AccountObj(guestAccounts.size() + 1, name, true, -1, null, null);
        guestAccounts.add(newAccount);
        return newAccount;
    }
}
