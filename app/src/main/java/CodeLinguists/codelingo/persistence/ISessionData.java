package CodeLinguists.codelingo.persistence;

import CodeLinguists.codelingo.dso.AccountObj;

public interface ISessionData {

    /**
     * @return - active account
     */
    AccountObj getActiveAccount();
    void setActiveAccount(AccountObj account);
}
