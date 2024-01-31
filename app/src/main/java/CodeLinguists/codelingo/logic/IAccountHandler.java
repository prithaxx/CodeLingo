package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;

public interface IAccountHandler {
    List<AccountObj> getGuestAccounts();
    void createGuestAccount(String name);

    /**
     * @return - Course from previous session
     */
    CourseObj getActiveCourse();

    //Logins must update ISessionData
    /**
     * Login to guest account
     *
     * @param account - account to log into
     * @throws AccountNotFoundException
     */
    //void login(AccountObj account);

    void setActiveCourse(CourseObj course);

    /**
     * Log into an account
     *
     * @param username
     * @param password - set to null for guest account
     * @throws AccountNotFoundException
     */
    void login(String username, String password);

    AccountObj getAccountDetails();

    void logout();
}
