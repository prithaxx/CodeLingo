package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;

public interface IAccountHandler {

    //Logins must update ISessionData
    /**
     * Login to guest account
     *
     * @param name - name of account to log into
     */
    AccountObj guestLogin(String name);

    List<ChapterObj> getActiveCourseChapters();
}
