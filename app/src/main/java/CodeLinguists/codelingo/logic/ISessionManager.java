package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.logic_exceptions.NoItemSelectedException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;

public interface ISessionManager {

    void guestLogin(String user) throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException;
    void guestLogin(String user, boolean stayLoggedIn) throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException;
    AccountObj getActiveAccount() throws AccountPermissionException;
    IQuizIterator startQuiz() throws NoItemSelectedException;
    CourseObj getActiveCourse() throws CourseNotFoundException, AccountPermissionException;
    void setActiveCourse(int index) throws CourseNotFoundException, AccountPermissionException, InputValidationException;
    List<CourseObj> getCourseList() throws AccountPermissionException;
    void setActiveChapter(int index) throws InputValidationException, AccountPermissionException;
    List<ChapterObj> getActiveCourseChapters() throws CourseNotFoundException, AccountPermissionException;
    int calculateProgressPercentage() throws CourseNotFoundException, AccountPermissionException;
    boolean autoLogin();
    void logout();
}
