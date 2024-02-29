package CodeLinguists.codelingo.logic;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.IQuizData;

public class SessionManager implements ISessionManager {
    //Singleton
    private static ISessionManager sessionManager;

    public static ISessionManager newInstance() {
        if (sessionManager == null) {
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    public static void clearSessionData() {
        sessionManager = null;
    }


    //instance fields
    IQuizData quizData;
    ICourseData courseData;
    AccountObj account;
    CourseObj course;
    int courseId;
    int chapterId;

    public SessionManager() {
        quizData = Services.getQuizData();
        courseData = Services.getCourseData();
        courseId = 1; //hardcoded bad i know, set active course can be called in view_GuestLogin maybe, not sure best place
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
        course = courseData.getCourseById(courseId);
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

        return courseData.getStartedCourseList();
    }

    @Override
    public void setActiveChapter(int index) {
        chapterId = index;
    }

    @Override
    public List<ChapterObj> getActiveCourseChapters() {
        if (chapterData == null) {
            chapterData = Services.getChapterData();
        }
        if (getActiveCourse() == null) {
            throw new IllegalStateException("Active course is not set.");
        }
        return chapterData.getChapterByCourseId(getActiveCourse().id());
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
