package CodeLinguists.codelingo.logic;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.exceptions.InputValidationException;

public interface IAccountHandler {

    //Logins must update ISessionData
    /**
     * Login to guest account
     *
     * @param name - name of account to log into
     * @throws AccountNotFoundException
     */
    AccountObj guestLogin(String name);

    List<ChapterObj> getActiveCourseChapters();
}
