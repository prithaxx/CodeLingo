package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.LocalPreferences;
import CodeLinguists.codelingo.logic.SessionManager;
import CodeLinguists.codelingo.persistence.persistence_exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.AccountHandler;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.persistence.stubs.AccountDataStub;
import CodeLinguists.codelingo.persistence.stubs.SessionDataStub;

public class AccountHandlerTest {
    private AccountDataStub accountDataStub;
    private AccountHandler accountHandler;
    private SessionDataStub sessionDataStub;


    @Before
    public void setUp() {
        accountDataStub = new AccountDataStub();
        sessionDataStub = new SessionDataStub(); // Your session stub setup
        accountHandler = new AccountHandler(accountDataStub, sessionDataStub);
    }

    @Test
    public void testGuestLoginValidName() throws Exception {
        AccountObj account = accountDataStub.createGuestAccount("TestUser");
        assertNotNull("Account should not be null", account);
        assertEquals("TestUser", account.getName());
    }

    @Test(expected = InputValidationException.class)
    public void guestLoginNullInput() throws DataInaccessibleException, InputValidationException {
        accountHandler.guestLogin(null);
    }

    @Test(expected = InputValidationException.class)
    public void guestLoginEmptyInput() throws DataInaccessibleException,  InputValidationException {
        accountHandler.guestLogin("");
    }

    @Test
    public void guestLoginNoAccount() throws DataInaccessibleException, AccountNotFoundException, InputValidationException {
        AccountObj acc = accountHandler.guestLogin("test");
        assertEquals(acc.getName(), "test");
    }

    @Test
    public void logoutTest() throws InputValidationException, DataInaccessibleException {
        accountHandler.guestLogin("TestUser", true); // Ensure there's a logged-in session
        accountHandler.logout();
    }

    @Test
    public void setActiveCourseTest() throws AccountNotFoundException {
        AccountObj testAccount = accountDataStub.createGuestAccount("TestUser");
        assertNotNull("Account should not be null", testAccount);
        int testCourseId = 4; // Example course ID
        accountHandler.setActiveCourse(testAccount, testCourseId);

        assertEquals("Active course ID should be updated", testCourseId, testAccount.getActiveCourseId());
    }


}