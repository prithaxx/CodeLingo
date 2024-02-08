package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.persistence.IQuizData;

public class SessionManager implements ISessionManager{
    IQuizData quizData;

    AccountObj account;
    CourseObj course;
    ChapterObj chapter;
    List<QuizObj> activeQuiz;
    int currentQuizCursor;

    public static SessionManager newInstance() {
        return new SessionManager();
    }

    public SessionManager() {
        quizData = Services.getQuizData();
    }

    @Override
    public void guestLogin(String user) {

    }

    @Override
    public List<QuizObj> getQuiz() {
        return quizData.getQuizByChapterId(1);
    }

    @Override
    public void startQuiz() {
        activeQuiz = getQuiz();
        currentQuizCursor = 0;
    }

    @Override
    public QuizObj nextQuestion() {
        if (currentQuizCursor >= activeQuiz.size()) {
            return null;
        }
        return activeQuiz.get(currentQuizCursor++);
    }

    @Override
    public boolean hasNextQuestion() {
        return currentQuizCursor < activeQuiz.size();
    }

    @Override
    public QuizObj prevQuestion() {
        //cursor always points to the next quiz
        //so going backwards requires on offset of 1
        if (currentQuizCursor <= 1) {
            return null;
        }
        return activeQuiz.get(--currentQuizCursor-1);
    }

    @Override
    public boolean hasPrevQuestion() {
        return currentQuizCursor > 1;
    }
}
