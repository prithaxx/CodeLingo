package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.CourseObj;

public interface ISessionManager {

    void guestLogin(String user);
    IQuizIterator startQuiz();
    CourseObj getActiveCourse();
    void setActiveChapter(int index);
}
