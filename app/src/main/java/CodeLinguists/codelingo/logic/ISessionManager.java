package CodeLinguists.codelingo.logic;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.QuizObj;

public interface ISessionManager {

    public void guestLogin(String user);
    IQuizHandler startQuiz();
    CourseObj getActiveCourse();

    void setActiveChapter(int index);
}
