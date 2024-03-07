package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.logic.logicExceptions.AccountPermissionException;
import CodeLinguists.codelingo.persistence.persistenceExceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.persistenceExceptions.DataInaccessibleException;

public interface ISessionManager {

    void guestLogin(String user) throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException;
    AccountObj getActiveAccount() throws AccountPermissionException;
    IQuizIterator startQuiz();
    CourseObj getActiveCourse() throws CourseNotFoundException, AccountPermissionException;
    void setActiveCourse(int index) throws CourseNotFoundException, AccountPermissionException;
    List<CourseObj> getCourseList();
    void setActiveChapter(int index);
    List<ChapterObj> getActiveCourseChapters() throws CourseNotFoundException, AccountPermissionException;
    int calculateProgressPercentage(CourseObj course) throws CourseNotFoundException;
}
