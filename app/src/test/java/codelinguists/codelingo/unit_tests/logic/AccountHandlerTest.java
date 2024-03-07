package codelinguists.codelingo.unit_tests.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.AccountHandler;
import CodeLinguists.codelingo.persistence.stubs.AccountDataStub;

public class AccountHandlerTest {
    private AccountDataStub accountDataStub;
    private AccountHandler accountHandler;


    @Before
    public void setUp() {
        accountDataStub = new AccountDataStub();
        accountHandler = new AccountHandler(accountDataStub);
    }

    @Test
    public void testGuestLoginValidName() throws Exception {
        AccountObj account = accountDataStub.createGuestAccount("TestUser");
        accountHandler.guestLogin("TestUser");
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
        AccountObj testAccount = accountDataStub.createGuestAccount("TestUser");

        accountHandler.guestLogin("TestUser", true);
        accountDataStub.setStayLoggedIn(testAccount.getId(), true);
        accountHandler.logout();
        // not sure how to test this

    }

    @Test
    public void setActiveCourseTest() throws AccountNotFoundException, InputValidationException, AccountPermissionException {
        AccountObj testAccount = accountDataStub.createGuestAccount("TestUser");
        assertNotNull("Account should not be null", testAccount);

        int testCourseId = 4; // Example course ID
        accountHandler.setActiveCourse(testAccount, testCourseId);

        assertEquals("Active course ID should be updated", testCourseId, testAccount.getActiveCourseId());
    }

    @Test (expected = InputValidationException.class)
    public void setActiveCourseTestNoCourse() throws AccountNotFoundException, InputValidationException, AccountPermissionException {
        AccountObj testAccount = accountDataStub.createGuestAccount("TestUser");
        int testCourseId = -1; // Example course ID
        accountHandler.setActiveCourse(testAccount, testCourseId);
    }

    @Test (expected = AccountPermissionException.class)
    public void setActiveCourseTestNoAccount() throws InputValidationException, AccountPermissionException {
        int testCourseId = 1; // Example course ID
        accountHandler.setActiveCourse(null, testCourseId);
    }

    @Test
    public void autoLoginTestSuccess() throws InputValidationException, DataInaccessibleException {
        AccountObj account = accountDataStub.createGuestAccount("TestUser");
        accountHandler.guestLogin("TestUser", true);

        assertEquals(accountHandler.autoLogin(), account);
    }

    @Test
    public void autoLoginTestFail() throws InputValidationException, DataInaccessibleException {
        accountDataStub.createGuestAccount("TestUser");
        accountHandler.guestLogin("TestUser");

        assertNull(accountHandler.autoLogin());
    }
}