package CodeLinguists.codelingo.logic;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.QuizObj;

public interface ISessionManager {

    public void guestLogin(String user);
    public List<QuizObj> getQuiz();

    void startQuiz();

    QuizObj nextQuestion();

    boolean hasNextQuestion();

    QuizObj prevQuestion();

    boolean hasPrevQuestion();

    CourseObj getActiveCourse();

    void setActiveChapter(int index);
}
