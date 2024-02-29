package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.CourseObj;

public interface ISessionManager {

    void guestLogin(String user);
    IQuizIterator startQuiz();
    CourseObj getActiveCourse();
    void setActiveCourse(int index);
    List<CourseObj> getStartedCourseList();
    void setActiveChapter(int index);
    List<ChapterObj> getActiveCourseChapters();
    int calculateProgressPercentage(CourseObj course);
}
