package CodeLinguists.codelingo.logic;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.exceptions.InputValidationException;

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
     * @param name - name of account to log into
     * @throws AccountNotFoundException
     */
    AccountObj guestLogin(String name);

    void setActiveCourse(CourseObj course);

    /**
     * Log into an account
     *
     * @param username
     * @param password 
     * @throws AccountNotFoundException
     */
    void login(String username, String password);

    AccountObj getAccountDetails();

    void logout();

    List<ChapterObj> getActiveCourseChapters();
}
