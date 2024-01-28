package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;

public interface IAccountHandler {
    public List<AccountObj> getGuestAccounts();
    public void createGuestAccount(String name);
    public AccountObj getAccountById(int id);

    /**
     * @return - Course from previous session
     */
    public CourseObj getActiveCourse();

    //Logins must update ISessionData
    /**
     * Login to guest account
     * @param account - account to log into
     * @throws AccountNotFoundException
     */
    public void login(AccountObj account);

    /**
     * Log into an account
     *
     * @param username
     * @param password - set to null for guest account
     * @throws AccountNotFoundException
     */
    public void login(String username, String password);

    public void logout();
}
