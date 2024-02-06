package CodeLinguists.codelingo.persistence.stubs;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.persistence.IAccountData;

public class AccountDataStub implements IAccountData {

    private List<AccountObj> accounts;
    private List<AccountObj> guestAccounts;

    public AccountDataStub() {
        accounts = new ArrayList<AccountObj>();
        guestAccounts = new ArrayList<AccountObj>();
    }

    public AccountDataStub(List<AccountObj> accounts, List<AccountObj> guestAccounts) {
        this.accounts = accounts;
        this.guestAccounts = guestAccounts;
    }

    @Override
    public AccountObj getGuestAccountByName(String name) {
        return guestAccounts.stream()
                .filter(account -> name.equals(account.getName()))
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("No Account matches the name "+name));
    }

    @Override
    public List<AccountObj> getAllAccounts() {
        List<AccountObj> allAccounts = new ArrayList<>();
        allAccounts.addAll(accounts);
        allAccounts.addAll(guestAccounts);
        return allAccounts;
    }

    @Override
    public List<AccountObj> getGuestAccounts() {
        return guestAccounts;
    }

    @Override
    public AccountObj createGuestAccount(String name) {
        AccountObj newAccount = new AccountObj(accounts.size() + 1, name, true, null, null, null);
        guestAccounts.add(newAccount);
        return newAccount;
    }

    @Override
    public void setActiveCourse(AccountObj account, CourseObj course) {
        account.setActiveCourse(course);
    }
}
