package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.NoItemSelectedException;

public class SessionManager implements ISessionManager{
    //Singleton
    private static ISessionManager sessionManager;

    public static ISessionManager newInstance() {
        if (sessionManager==null) {
            sessionManager = new SessionManager(new QuizHandler(), new AccountHandler());
        }
        return sessionManager;
    }

    public static void clearSessionData() {
        sessionManager = null;
    }


    //instance fields
    IQuizHandler quizHandler;
    IAccountHandler accountHandler;

    AccountObj account;
    CourseObj course;
    int chapterId;

    private SessionManager(IQuizHandler quizHandler, IAccountHandler accountHandler) {
        this.quizHandler = quizHandler;
        this.accountHandler = accountHandler;
        chapterId=-1; //unselected by default
    }

    @Override
    public void guestLogin(String user) {
        this.account = accountHandler.guestLogin(user);
    }

    @Override
    public IQuizIterator startQuiz() {
        if (course==null || chapterId<0) {
            throw new NoItemSelectedException(Strings.NoCourseSelected);
        }
        return quizHandler.getQuiz(course.id(), chapterId);
    }

    @Override
    public CourseObj getActiveCourse() {
        return new CourseObj(0, "Example Course", "Welcome to the example course! This is a placeholder for future courses", true, true);
    }

    @Override
    public void setActiveChapter(int index) {
        chapterId=index;
    }
}
