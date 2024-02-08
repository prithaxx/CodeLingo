package CodeLinguists.codelingo.application;

import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.IQuizData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.persistence.stubs.AccountDataStub;
import CodeLinguists.codelingo.persistence.stubs.QuizDataStub;
import CodeLinguists.codelingo.persistence.stubs.SessionDataStub;

/**
 * Provides creation and distribution of persistance layer classes
 */
public class Services {
    private static IAccountData accountData = null;
    private static ISessionData sessionData = null;
    private static IQuizData quizData = null;

    public static synchronized void resetObjects() {
        accountData=null;
        sessionData=null;
        quizData=null;
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

    public static synchronized IQuizData getQuizData() {
        if (quizData==null) {
            quizData = new QuizDataStub();
        }
        return quizData;
    }
}
