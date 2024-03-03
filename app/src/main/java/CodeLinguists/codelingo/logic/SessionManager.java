package CodeLinguists.codelingo.logic;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.CourseObjFactory;
import CodeLinguists.codelingo.exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.exceptions.NoItemSelectedException;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.persistence.IChapterData;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.IQuizData;

public class SessionManager implements ISessionManager {
    //Singleton
    private static ISessionManager sessionManager;

    public static ISessionManager newInstance() {
        if (sessionManager==null) {
            sessionManager = new SessionManager(new QuizHandler(true), new AccountHandler(true),true);
        }
        return sessionManager;
    }

    public static void clearSessionData() {
        sessionManager = null;
    }


    //instance fields
    private final IQuizHandler quizHandler;
    private final IAccountHandler accountHandler;
    private final IQuizData quizData;
    private final ICourseData courseData;
    private final IChapterData chapterData;
    private AccountObj account;
    private CourseObj course;
    private int courseId;
    private int chapterId;


    private SessionManager(IQuizHandler quizHandler, IAccountHandler accountHandler, boolean forProduction) {
        this.quizHandler = quizHandler;
        this.accountHandler = accountHandler;
        course = accountHandler.getActiveCourse();
        quizData = Services.getQuizData(forProduction);
        courseData = Services.getCourseData(forProduction);
        chapterData = Services.getChapterData(forProduction);
        courseId = 1; //hardcoded bad i know, set active course can be called in view_GuestLogin maybe, not sure best place
    }

    public SessionManager(boolean forProduction) {
        this(new QuizHandler(forProduction), new AccountHandler(forProduction),true);
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
    public CourseObj getActiveCourse() throws CourseNotFoundException {
        //return course;
        if (this.account == null) {
            throw new IllegalStateException("Account is not set.");
        }
        int accountId = account.getId();
        try {
            course = courseData.getCourseById(courseId, accountId);
        } catch (CourseNotFoundException e) {
            course = CourseObjFactory.getNoneCourse();
            throw e;
        }
        if(course == null){
            throw new IllegalStateException("Active course not set");
        }
        return course;
    }

    @Override
    public void setActiveCourse(int index){
        courseId = index;
    }

    @Override
    public List<CourseObj> getStartedCourseList(){
        int accountId = account.getId();
        return courseData.getStartedCourseList(accountId);
    }

    @Override
    public void setActiveChapter(int index) {
        chapterId = index;
    }

    @Override
    public List<ChapterObj> getActiveCourseChapters() throws CourseNotFoundException {
        if (course == null) {
            throw new CourseNotFoundException("No course selected");
        }
        int accountId = account.getId();
        return chapterData.getChapterByCourseId(getActiveCourse().id(), accountId);
    }


    private List<QuizObj> getQuiz() {
        return quizData.getQuizByChapterId(1);
    }

    @Override
    public int calculateProgressPercentage(CourseObj course) throws CourseNotFoundException {
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
