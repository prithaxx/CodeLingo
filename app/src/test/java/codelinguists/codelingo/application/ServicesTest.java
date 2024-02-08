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
        IAccountData newAccount = Services.getAccountData();
        assertNotNull(newAccount);
        IAccountData repeatAccount = Services.getAccountData();
        assertSame(newAccount, repeatAccount);
    }
    @Test
    public void testGetSessionData() {
        ISessionData newSession = Services.getSessionData();
        assertNotNull(newSession);
        ISessionData repeatSession = Services.getSessionData();
        assertSame(newSession, repeatSession); //ensure singleton is single
    }

    @Test
    public void testGetQuizData() {
        IQuizData newQuiz = Services.getQuizData();
        assertNotNull(newQuiz);
        IQuizData repeatQuiz = Services.getQuizData();
        assertSame(newQuiz, repeatQuiz);
    }
}
