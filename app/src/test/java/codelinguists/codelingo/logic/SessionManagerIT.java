package codelinguists.codelingo.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.logic.IAccountHandler;
import CodeLinguists.codelingo.logic.ICourseHandler;
import CodeLinguists.codelingo.logic.IQuizHandler;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.SessionManager;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.persistence.IQuizData;
import codelinguists.codelingo.utils.TestUtils;

public class SessionManagerIT {
    private File tempDB;
    private SessionManager sessionManager;
    private ISessionData sessionData;
    private IQuizHandler quizHandler;
    private IAccountHandler accountHandler;
    private ICourseHandler courseHandler;
    @Before
    public void setup() throws IOException {
        this.tempDB = TestUtils.copyDB();
        sessionManager = new SessionManager(quizHandler, accountHandler, courseHandler);
    }

    @After
    public void reset() {
        this.tempDB.delete();
        Services.resetObjects();
    }

    @Test
    public void testSetActiveAccount_Successful() {
        

    }


}
