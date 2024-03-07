package CodeLinguists.codelingo.logic;

import java.sql.SQLException;
import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.LocalPreferences;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.exceptions.DataInaccessibleException;

public interface IAccountHandler {

    //Logins must update ISessionData
    /**
     * Login to guest account
     *
     * @param name - name of account to log into
     */
    AccountObj guestLogin(String name) throws DataInaccessibleException;
    AccountObj guestLogin(String name, boolean stayLoggedIn) throws DataInaccessibleException;

    void setActiveCourse(AccountObj account, int courseId);

    AccountObj autoLogin();

    void logout();
}
