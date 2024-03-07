package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;

public interface IAccountHandler {

    //Logins must update ISessionData
    /**
     * Login to guest account
     *
     * @param name - name of account to log into
     */
    AccountObj guestLogin(String name) throws DataInaccessibleException;

    void setActiveCourse(AccountObj account, int courseId);
}
