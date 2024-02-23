package CodeLinguists.codelingo.logic;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.persistence.IQuizData;

public class SessionManager implements ISessionManager{
    //Singleton
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

    @Override
    public List<ChapterObj> getActiveCourseChapters() {
        List<ChapterObj> chapterList;

        chapterList = new ArrayList<>();
        chapterList.add(new ChapterObj(1, "Introduction to Java", 1, null, true, true));
        chapterList.add(new ChapterObj(3, "Data Structures", 1, null, false, false));
        chapterList.add(new ChapterObj(4, "Advanced Java Features", 2, null, false, false));
        chapterList.add(new ChapterObj(5, "Concurrency in Java", 2, null, false, false));
        chapterList.add(new ChapterObj(6, "Java Networking", 3, null, false, false));

        return chapterList;
    }

    private List<QuizObj> getQuiz() {
        return quizData.getQuizByChapterId(1);
    }

    @Override
    public int calculateProgressPercentage(CourseObj course) {
        List<ChapterObj> listOfChapter = getActiveCourseChapters();

        int totalChapters = listOfChapter.size();
        int completedChapters = 0;

        for (ChapterObj chapter : listOfChapter) {
            if (chapter.isCompleted()) {
                completedChapters++;
            }
        }

        if (totalChapters == 0) return 0;

        double doublePercent = (double) completedChapters / totalChapters;

        return (int) (doublePercent * 100);
    }
}
