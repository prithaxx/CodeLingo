package CodeLinguists.codelingo.application;

import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.IChapterData;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.persistence.stubs.AccountDataStub;
import CodeLinguists.codelingo.persistence.stubs.SessionDataStub;

/**
 * Provides creation and distribution of persistance layer classes
 */
public class Services {
    private static IAccountData accountData = null;
    private static ISessionData sessionData = null;
    private static ICourseData courseData = null;
    private static IChapterData chapterData = null;

    public static synchronized void resetObjects() {
        accountData=null;
        sessionData=null;
        courseData=null;
        chapterData=null;
    }

    public static synchronized IAccountData getAccountData() {
        if (accountData==null) {
            accountData = new AccountDataStub();
        }
        return accountData;
    }

    public static synchronized ISessionData getSessionData() {
        if (sessionData ==null) {
            sessionData = new SessionDataStub();
        }
        return sessionData;
    }

    public static synchronized ICourseData getCourseData() {
        if (courseData==null) {
            //TODO add default persistance class
            //courseData = new CourseDataStub();
        }
        return courseData;
    }

    public static synchronized IChapterData getChapterData() {
        if (chapterData==null) {
            //TODO add default persistance class
            //chapterData = new ChapterDataStub();
        }
        return chapterData;
    }
}
