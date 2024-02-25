package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;

public interface ISessionManager {

    void guestLogin(String user);
    IQuizHandler startQuiz();
    CourseObj getActiveCourse();
    void setActiveChapter(int index);
    List<ChapterObj> getActiveCourseChapters();
    int calculateProgressPercentage(CourseObj course);
}
