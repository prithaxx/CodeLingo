package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.CourseObj;

public interface ISessionManager {

    void guestLogin(String user);
    IQuizHandler startQuiz();
    CourseObj getActiveCourse();
    void setActiveChapter(int index);
}
