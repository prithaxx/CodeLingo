package CodeLinguists.codelingo.application;

import CodeLinguists.codelingo.logic.AccountHandler;
import CodeLinguists.codelingo.logic.IAccountHandler;
import CodeLinguists.codelingo.logic.IQuizHandler;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.QuizHandler;
import CodeLinguists.codelingo.logic.SessionManager;
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
 * Handles dependency injection
 */
public class Services {
    private enum DbType {
        STUB, HSQLDB
    }
    private static final DbType DB_IMPLEMENTATION = DbType.HSQLDB;
    private static ISessionManager sessionManager = null;
    private static IAccountHandler accountHandler = null;
    private static IQuizHandler quizHandler = null;

    private static IAccountData accountData = null;
    private static ICourseData courseData = null;
    private static ISessionData sessionData = null;
    private static IQuizData quizData = null;
    private static IChapterData chapterData = null;

    public static synchronized void resetObjects() {
        sessionManager = null;
        accountHandler = null;
        quizHandler = null;
        accountData = null;
        sessionData = null;
        quizData = null;
        chapterData = null;
    }

    public static ISessionManager getSessionManager() {
        if (sessionManager == null) {
            sessionManager = new SessionManager(getQuizHandler(), getAccountHandler());
        }
        return sessionManager;
    }

    public static IAccountHandler getAccountHandler() {
        if (accountHandler == null) {
            accountHandler = new AccountHandler(getAccountData(), getSessionData());
        }
        return accountHandler;
    }

    public static IQuizHandler getQuizHandler() {
        if (quizHandler == null){
            quizHandler = new QuizHandler(getQuizData());
        }
        return quizHandler;
    }

    public static synchronized IAccountData getAccountData() {
        if (accountData == null) {
            switch (DB_IMPLEMENTATION){
                case STUB -> accountData = new AccountDataStub();
                case HSQLDB -> accountData = new AccountDataHSQLDB(Main.getDBPathName());
            }
        }
        return accountData;
    }

    public static synchronized ICourseData getCourseData(){
        if(courseData == null){
            switch (DB_IMPLEMENTATION){
                case STUB -> courseData = new CourseDataStub();
                case HSQLDB -> courseData = new CourseDataHSQLDB(Main.getDBPathName());
            }
        }
        return courseData;
    }

    public static synchronized ISessionData getSessionData() {
        if (sessionData == null) {
            switch (DB_IMPLEMENTATION){
                case STUB -> sessionData = new SessionDataStub();
                case HSQLDB -> sessionData = new SessionDataHSQLDB(Main.getDBPathName());
            }
        }

        return sessionData;
    }

    public static synchronized IQuizData getQuizData() {
        if (quizData == null) {
            switch (DB_IMPLEMENTATION){
                case STUB -> quizData = new QuizDataStub();
                case HSQLDB -> quizData = new QuizDataHSQLDB(Main.getDBPathName());
            }
        }
        return quizData;
    }

    public static synchronized IChapterData getChapterData() {
        if (chapterData == null) {
            switch (DB_IMPLEMENTATION){
                case STUB -> chapterData = new ChapterDataStub();
                case HSQLDB -> chapterData = new ChapterDataHSQLDB(Main.getDBPathName());
            }
        }
        return chapterData;
    }
}
