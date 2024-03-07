package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;

public interface IAccountHandler {

    //Logins must update ISessionData
    /**
     * Login to guest account
     *
     * @param name - name of account to log into
     */
    AccountObj guestLogin(String name) throws DataInaccessibleException, InputValidationException;
    AccountObj guestLogin(String name, boolean stayLoggedIn) throws DataInaccessibleException, InputValidationException;

    void setActiveCourse(AccountObj account, int courseId) throws InputValidationException;

    AccountObj autoLogin();

    void logout();
}
