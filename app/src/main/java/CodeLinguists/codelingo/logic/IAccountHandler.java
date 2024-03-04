package CodeLinguists.codelingo.logic;

import java.sql.SQLException;
import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;

public interface IAccountHandler {

    //Logins must update ISessionData
    /**
     * Login to guest account
     *
     * @param name - name of account to log into
     */
    AccountObj guestLogin(String name) throws SQLException;

    void setActiveCourse(AccountObj account, int courseId);

    List<ChapterObj> getActiveCourseChapters();
}
