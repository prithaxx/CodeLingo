package CodeLinguists.codelingo.persistence;

import CodeLinguists.codelingo.dso.AccountObj;

public interface ISessionData {

    /**
     * @return - "remember me" account
     */
    public AccountObj getActiveAccount();
    public void setActiveAccount(AccountObj account);
}
