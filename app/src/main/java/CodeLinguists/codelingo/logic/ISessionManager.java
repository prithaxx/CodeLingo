package CodeLinguists.codelingo.logic;

import java.sql.SQLException;
import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.CourseNotFoundException;

public interface ISessionManager {

    void guestLogin(String user) throws SQLException;
    IQuizIterator startQuiz();
    CourseObj getActiveCourse() throws CourseNotFoundException;
    void setActiveCourse(int index);
    List<CourseObj> getStartedCourseList();
    void setActiveChapter(int index);
    List<ChapterObj> getActiveCourseChapters() throws CourseNotFoundException;
    int calculateProgressPercentage(CourseObj course) throws CourseNotFoundException;
}
