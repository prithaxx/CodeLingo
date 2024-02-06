package CodeLinguists.codelingo.persistence.stubs;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.ISessionData;

public class SessionDataStub implements ISessionData {

    private AccountObj activeAccount;

    public SessionDataStub(AccountObj activeAccount) {
        this.activeAccount = activeAccount;
    }

    public SessionDataStub() {
        this.activeAccount = null;
    }

    @Override
    public AccountObj getActiveAccount() {
        return this.activeAccount;
    }

    @Override
    public void setActiveAccount(AccountObj account) {
        this.activeAccount = account;
    }

    @Override
    public CourseObj getActiveCourse() {
        return activeAccount.getActiveCourse();
    }
}