package CodeLinguists.codelingo.application;

import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.IChapterData;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.IQuizData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.persistence.hsqldb.AccountDataHSQLDB;
import CodeLinguists.codelingo.persistence.hsqldb.ChapterDataHSQLDB;
import CodeLinguists.codelingo.persistence.hsqldb.CourseDataHSQLDB;
import CodeLinguists.codelingo.persistence.hsqldb.QuizDataHSQLDB;
import CodeLinguists.codelingo.persistence.hsqldb.SessionDataHSQLDB;
import CodeLinguists.codelingo.persistence.stubs.AccountDataStub;
import CodeLinguists.codelingo.persistence.stubs.ChapterDataStub;
import CodeLinguists.codelingo.persistence.stubs.CourseDataStub;
import CodeLinguists.codelingo.persistence.stubs.QuizDataStub;
import CodeLinguists.codelingo.persistence.stubs.SessionDataStub;

/**
 * Provides creation and distribution of persistence layer classes
 */
public class Services {
    private static IAccountData accountData = null;
    private static ICourseData courseData = null;
    private static ISessionData sessionData = null;
    private static IQuizData quizData = null;

    private static IChapterData chapterData = null;

    public static synchronized void resetObjects() {
        accountData = null;
        sessionData = null;
        quizData = null;
        chapterData = null;
    }

    public static synchronized IAccountData getAccountData(boolean forProduction) {
        if (accountData == null) {
            if (forProduction) {
                accountData = new AccountDataHSQLDB(Main.getDBPathName());
            } else {
                accountData = new AccountDataStub();
            }
        }
        return accountData;
    }

    public static synchronized ICourseData getCourseData(boolean forProduction){
        if(courseData == null){
            if (forProduction) {
                courseData = new CourseDataHSQLDB(Main.getDBPathName());
            } else {
                courseData = new CourseDataStub();
            }

        }
        return courseData;
    }

    public static synchronized ISessionData getSessionData(boolean forProduction) {
        if (sessionData == null) {
            if (forProduction) {
                sessionData = new SessionDataHSQLDB();
            } else {
                sessionData = new SessionDataStub();
            }
        }
        return sessionData;
    }

    public static synchronized IQuizData getQuizData(boolean forProduction) {
        if (quizData == null) {
            if (forProduction) {
                quizData = new QuizDataHSQLDB(Main.getDBPathName());
            } else {
                quizData = new QuizDataStub();
            }

        }
        return quizData;
    }

    public static synchronized IChapterData getChapterData(boolean forProduction) {
        if (chapterData == null) {
            if (forProduction) {
                chapterData = new ChapterDataHSQLDB(Main.getDBPathName());
            } else {
                chapterData = new ChapterDataStub();
            }
        }
        return chapterData;
    }
}
