package CodeLinguists.codelingo.application;

import CodeLinguists.codelingo.logic.AccountHandler;
import CodeLinguists.codelingo.logic.IAccountHandler;
import CodeLinguists.codelingo.logic.ICourseHandler;
import CodeLinguists.codelingo.logic.IQuizHandler;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.QuizHandler;
import CodeLinguists.codelingo.logic.SessionManager;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.IChapterData;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.IQuizData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.persistence.sql.AccountDataSQL;
import CodeLinguists.codelingo.persistence.sql.ChapterDataSQL;
import CodeLinguists.codelingo.persistence.sql.CourseDataSQL;
import CodeLinguists.codelingo.persistence.sql.QuizDataSQL;
import CodeLinguists.codelingo.persistence.sql.SessionDataSQL;
import CodeLinguists.codelingo.persistence.stubs.AccountDataStub;
import CodeLinguists.codelingo.persistence.stubs.ChapterDataStub;
import CodeLinguists.codelingo.persistence.stubs.CourseDataStub;
import CodeLinguists.codelingo.persistence.stubs.QuizDataStub;
import CodeLinguists.codelingo.persistence.stubs.SessionDataStub;
import CodeLinguists.codelingo.persistence.utils.HSQLDBRunner;
import CodeLinguists.codelingo.persistence.utils.ISqlRunner;

/**
 * Provides creation and distribution of persistence layer classes
 * Handles dependency injection
 */
public class Services {
    private enum DbType {
        STUB, HSQLDB
    }

    //Configure
    private static final DbType DB_IMPLEMENTATION = DbType.HSQLDB;

    //Logic Layer
    private static ISessionManager sessionManager = null;
    private static IAccountHandler accountHandler = null;
    private static IQuizHandler quizHandler = null;
    private static ICourseHandler courseHandler = null;

    //Persistence layer
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
            sessionManager = new SessionManager(getQuizHandler(), getAccountHandler(), getCourseHandler());
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

    public static ICourseHandler getCourseHandler(){
        if (courseHandler == null){
            courseHandler = new CourseHandler(getCourseData(), getChapterData());
        }
        return courseHandler;
    }

    public static synchronized IAccountData getAccountData() {
        if (accountData == null) {
            switch (DB_IMPLEMENTATION){
                case STUB -> accountData = new AccountDataStub();
                case HSQLDB -> accountData = new AccountDataSQL(getSqlRunner());
            }
        }
        return accountData;
    }

    public static synchronized ICourseData getCourseData(){
        if(courseData == null){
            switch (DB_IMPLEMENTATION){
                case STUB -> courseData = new CourseDataStub();
                case HSQLDB -> courseData = new CourseDataSQL(getSqlRunner());
            }
        }
        return courseData;
    }

    public static synchronized ISessionData getSessionData() {
        if (sessionData == null) {
            switch (DB_IMPLEMENTATION){
                case STUB -> sessionData = new SessionDataStub();
                case HSQLDB -> sessionData = new SessionDataSQL(getSqlRunner());
            }
        }

        return sessionData;
    }

    public static synchronized IQuizData getQuizData() {
        if (quizData == null) {
            switch (DB_IMPLEMENTATION){
                case STUB -> quizData = new QuizDataStub();
                case HSQLDB -> quizData = new QuizDataSQL(getSqlRunner());
            }
        }
        return quizData;
    }

    public static synchronized IChapterData getChapterData() {
        if (chapterData == null) {
            switch (DB_IMPLEMENTATION){
                case STUB -> chapterData = new ChapterDataStub();
                case HSQLDB -> chapterData = new ChapterDataSQL(getSqlRunner());
            }
        }
        return chapterData;
    }

    private static synchronized ISqlRunner getSqlRunner() {
        return switch (DB_IMPLEMENTATION){
            case STUB -> null;
            case HSQLDB -> new HSQLDBRunner(Main.getDbUrl());
        };
    }
}
