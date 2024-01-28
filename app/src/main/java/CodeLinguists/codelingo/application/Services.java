package CodeLinguists.codelingo.application;

import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.IChapterData;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.ISessionData;

/**
 * Provides creation and distribution of persistance layer classes
 */
public class Services {
    private static IAccountData accountData = null;
    private static ISessionData sessionData = null;
    private static ICourseData courseData = null;
    private static IChapterData chapterData = null;

    public static synchronized IAccountData getAccountData() {
        if (accountData==null) {
            //TODO add default persistance class
            //accountData = new accountDataStub();
        }
        return accountData;
    }

    public static synchronized ISessionData getSessionData() {
        if (sessionData ==null) {
            //TODO add default persistance class
            //sessionData = new sessionDataStub();
        }
        return sessionData;
    }

    public static synchronized ICourseData getCourseData() {
        if (courseData==null) {
            //TODO add default persistance class
            //courseData = new courseDataStub();
        }
        return courseData;
    }

    public static synchronized IChapterData getChapterData() {
        if (chapterData==null) {
            //TODO add default persistance class
            //chapterData = new chapterDataStub();
        }
        return chapterData;
    }
}
