package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.AccountHandler;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ISessionData;

public class AccountHandlerTest {
    AccountHandler accountHandler;
    @Before
    public void setUp() throws Exception {
        Services.resetObjects();
        IAccountData accountData = Services.getAccountData();
        ISessionData sessionData = Services.getSessionData();

        this.accountHandler = new AccountHandler(accountData, sessionData);
    }

    @Test
    public void getGuestAccounts() {
        List<AccountObj> guestAccounts = accountHandler.getGuestAccounts();
        assertNotNull(guestAccounts);
        assertEquals(guestAccounts.size(), 0);
    }

    @Test
    public void createGuestAccount() {
        accountHandler.createGuestAccount("testPerson");
        List<AccountObj> guestAccounts = accountHandler.getGuestAccounts();
        assertNotNull(guestAccounts);
        assertEquals(guestAccounts.size(), 1);

        accountHandler.createGuestAccount("1");
        guestAccounts = accountHandler.getGuestAccounts();
        assertNotNull(guestAccounts);
        assertEquals(guestAccounts.size(), 2);
    }

    @Test(expected = InputValidationException.class)
    public void createGuestAccountNullInvalid() {
        accountHandler.createGuestAccount(null);
    }
    @Test(expected = InputValidationException.class)
    public void createGuestAccountEmptyInvalid() {
        accountHandler.createGuestAccount("");
    }
    @Test
    public void ActiveCourseDefaultValue() {
        //initial state defaults to null
        helperLoginGuest();

        CourseObj courseObj = accountHandler.getActiveCourse();
        assertNull(courseObj);
    }
    @Test
    public void ActiveCourseAssignment() {
        helperLoginGuest();

        CourseObj newCourseObj = new CourseObj(1, "test", "test", true, false);
        accountHandler.setActiveCourse(newCourseObj);
        CourseObj courseObj = accountHandler.getActiveCourse();
        assertSame(newCourseObj, courseObj);
    }
    @Test
    public void ActiveCourseUnAssignment() {
        helperLoginGuest();

        CourseObj newCourseObj = new CourseObj(1, "test", "test", true, false);
        accountHandler.setActiveCourse(newCourseObj);
        accountHandler.setActiveCourse(null);
        CourseObj courseObj = accountHandler.getActiveCourse();
        assertNull(courseObj);
    }
    @Test(expected = AccountNotFoundException.class)
    public void loginNoAccountFound() {
        accountHandler.login("test", "test");
    }
    @Test(expected = InputValidationException.class)
    public void loginNullUsername() {
        accountHandler.login(null, "test");
    }
    @Test(expected = InputValidationException.class)
    public void loginEmptyUsername() {
        accountHandler.login("", "test");
    }
    @Test(expected = InputValidationException.class)
    public void loginNullPassword() {
        accountHandler.login("test", null);
    }
    @Test(expected = InputValidationException.class)
    public void loginEmptyPassword() {
        accountHandler.login("test", "");
    }
    @Test(expected = AccountNotFoundException.class)
    public void loginNoGuestAccountFound() {
        accountHandler.guestLogin("test");
    }
    @Test
    public void loginGuest() {
        String name = "test";
        accountHandler.createGuestAccount(name);
        accountHandler.guestLogin(name);
        AccountObj account = accountHandler.getAccountDetails();
        assertEquals(account.getName(), name);
    }

    private void helperLoginGuest() {
        String name = "test";
        accountHandler.createGuestAccount(name);
        accountHandler.guestLogin(name);
    }
}