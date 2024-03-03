package codelinguists.codelingo.application;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.persistence.IQuizData;

public class ServicesTest {
    @Before
    public void setup() {
        Services.resetObjects();
    }

    @Test
    public void testGetAccountData() {
        IAccountData newAccount = Services.getAccountData(true);
        assertNotNull(newAccount);
        IAccountData repeatAccount = Services.getAccountData(true);
        assertSame(newAccount, repeatAccount);
    }
    @Test
    public void testGetSessionData() {
        ISessionData newSession = Services.getSessionData(true);
        assertNotNull(newSession);
        ISessionData repeatSession = Services.getSessionData(true);
        assertSame(newSession, repeatSession); //ensure singleton is single
    }

    @Test
    public void testGetQuizData() {
        IQuizData newQuiz = Services.getQuizData(true);
        assertNotNull(newQuiz);
        IQuizData repeatQuiz = Services.getQuizData(true);
        assertSame(newQuiz, repeatQuiz);
    }
}
