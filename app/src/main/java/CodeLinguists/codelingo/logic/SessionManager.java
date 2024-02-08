package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.persistence.IQuizData;

public class SessionManager implements ISessionManager{
    //Singleton setup
    private static ISessionManager sessionManager;

    public static ISessionManager newInstance() {
        if (sessionManager==null) {
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    public static void clearSessionData() {
        sessionManager = null;
    }

    //instance fields
    IQuizData quizData;

    AccountObj account;
    CourseObj course;
    int chapterId;

    public SessionManager() {
        quizData = Services.getQuizData();
    }

    @Override
    public void guestLogin(String user) {
        IAccountHandler accountHandler = new AccountHandler();
        this.account = accountHandler.guestLogin(user);
    }

    @Override
    public IQuizHandler startQuiz() {
        return new QuizHandler(this.getQuiz());
    }

    @Override
    public CourseObj getActiveCourse() {
        return new CourseObj(0, "Example Course", "Welcome to the example course! This is a placeholder for future courses", true, true);
    }

    @Override
    public void setActiveChapter(int index) {
        chapterId=index;
    }

    private List<QuizObj> getQuiz() {
        return quizData.getQuizByChapterId(1);
    }
}
