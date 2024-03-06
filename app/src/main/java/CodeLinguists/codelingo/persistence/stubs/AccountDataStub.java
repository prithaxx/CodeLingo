package CodeLinguists.codelingo.persistence.stubs;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.LocalPreferences;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.exceptions.DataInaccessibleException;
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
    public AccountObj getGuestAccountById(int accountId) throws AccountNotFoundException {
        for (AccountObj account:this.guestAccounts) {
            if (accountId == account.getId()) {
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

    @Override
    public void setActiveCourse(int accountId, int courseId) {
        AccountObj account = getAccountById(accountId);
        if (account != null) {
            account.setActiveCourseId(courseId);
        }
    }

    //There is no persistence, so staying logged in does nothing
    @Override
    public void setStayLoggedIn(int accountid, boolean stayLoggedIn) {
        return;
    }

    @Override
    public LocalPreferences getLocalPreferences() throws DataInaccessibleException {
        return new LocalPreferences(false, 0);
    }

    //No persistence -> no localPreferences
    @Override
    public void initLocalPreferences() {
        return;
    }

    private AccountObj getAccountById(int accountId) {
        for (AccountObj account:this.guestAccounts) {
            if (accountId == account.getId()){
                return account;
            }
        }
        return null;
    }
}
