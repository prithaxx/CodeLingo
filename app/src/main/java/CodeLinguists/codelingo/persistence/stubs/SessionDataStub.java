package CodeLinguists.codelingo.persistence.stubs;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.persistence.ISessionData;

public class SessionDataStub implements ISessionData {

    private AccountObj activeAccount;

    public SessionDataStub() {
        this.activeAccount = null;
    }

    @Override
    public void setActiveAccount(AccountObj account) {
        this.activeAccount = account;
    }
}