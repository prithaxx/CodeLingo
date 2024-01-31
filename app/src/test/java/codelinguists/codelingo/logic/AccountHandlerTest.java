package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
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

    //TODO make expected exception more specific
    @Test(expected = RuntimeException.class)
    public void createGuestAccountNullInvalid() {
        accountHandler.createGuestAccount(null);
    }
    @Test(expected = RuntimeException.class)
    public void createGuestAccountEmptyInvalid() {
        accountHandler.createGuestAccount("");
    }
    @Test
    public void ActiveCourseDefaultValue() {
        //initial state defaults to null
        CourseObj courseObj = accountHandler.getActiveCourse();
        assertNull(courseObj);

        //test course assignment
        CourseObj newCourseObj = new CourseObj(1, "test", "test", true, false);
        accountHandler.setActiveCourse(newCourseObj);
        courseObj = accountHandler.getActiveCourse();
        assertSame(newCourseObj, courseObj);

        //test course un-assignment
        accountHandler.setActiveCourse(null);
        courseObj = accountHandler.getActiveCourse();
        assertNull(courseObj);
    }
    @Test
    public void ActiveCourseAssignment() {
        CourseObj newCourseObj = new CourseObj(1, "test", "test", true, false);
        accountHandler.setActiveCourse(newCourseObj);
        CourseObj courseObj = accountHandler.getActiveCourse();
        assertSame(newCourseObj, courseObj);

        //test course un-assignment
        accountHandler.setActiveCourse(null);
        courseObj = accountHandler.getActiveCourse();
        assertNull(courseObj);
    }
    @Test
    public void ActiveCourseUnAssignment() {
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
    @Test(expected = RuntimeException.class)
    public void loginNullUsername() {
        accountHandler.login(null, "test");
    }
    @Test(expected = RuntimeException.class)
    public void loginEmptyUsername() {
        accountHandler.login("", "test");
    }
    @Test(expected = RuntimeException.class)
    public void loginNullPassword() {
        accountHandler.login("test", null);
    }
    @Test(expected = RuntimeException.class)
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
}