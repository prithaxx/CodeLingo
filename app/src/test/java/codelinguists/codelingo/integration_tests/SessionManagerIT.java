package codelinguists.codelingo.integration_tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.CourseObjFactory;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.exceptions.AccountPermissionException;
import CodeLinguists.codelingo.exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.exceptions.InputValidationException;
import CodeLinguists.codelingo.exceptions.NoItemSelectedException;
import CodeLinguists.codelingo.logic.ISessionManager;
import codelinguists.codelingo.utils.SqlDbIT;
import codelinguists.codelingo.utils.TestUtils;

public class SessionManagerIT extends SqlDbIT {

    ISessionManager sessionManager;

    //General session manager tests
    @Override
    public void setup() throws IOException {
        super.setup();
        sessionManager = Services.getSessionManager();
    }

    @Test
    public void testGuestLogin() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.getActiveAccount());
    }

    @Test (expected = InputValidationException.class)
    public void testGuestLoginEmpty() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException {
        String user = "";
        sessionManager.guestLogin(user);
    }

    @Test (expected = InputValidationException.class)
    public void testGuestLoginNull() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException {
        String user = null;
        sessionManager.guestLogin(user);
    }

    @Test
    public void testGuestLoginOverload() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException {
        String user = "SA";
        sessionManager.guestLogin(user, false);
        assertNotNull(sessionManager.getActiveAccount());
    }

    @Test (expected = InputValidationException.class)
    public void testGuestLoginOverloadEmpty() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException {
        String user = "";
        sessionManager.guestLogin(user, false);
    }

    @Test (expected = InputValidationException.class)
    public void testGuestLoginOverloadNull() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException {
        String user = null;
        sessionManager.guestLogin(user, false);
    }

    @Test
    public void testNoAutoLogin() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.getActiveAccount());
    }

    @Test(expected = AccountPermissionException.class)
    public void testLogoutAccountWithoutSignin() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        sessionManager.logout();
        sessionManager.getActiveAccount();
    }

    @Test(expected = AccountPermissionException.class)
    public void testLogoutAccount() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.logout();
        sessionManager.getActiveAccount();
    }

    @Test(expected = AccountPermissionException.class)
    public void testLogoutCourse() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.logout();
        sessionManager.getActiveCourse();
    }

    @Test(expected = AccountPermissionException.class)
    public void testLogoutChapter() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.logout();
        sessionManager.getActiveCourse();
    }

    @Test(expected = NoItemSelectedException.class)
    public void testStartQuizNoCourse() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.startQuiz();
    }

    @Test(expected = NoItemSelectedException.class)
    public void testStartQuizNoChapter() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.startQuiz();
    }

    @Test
    public void testStartQuiz() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        assertNotNull(sessionManager.startQuiz());
    }

    @Test(expected = InputValidationException.class)
    public void testSetActiveCourseNegative() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(-1);
    }

    @Test(expected = CourseNotFoundException.class)
    public void testSetActiveCourseOutOfRange() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(Integer.MAX_VALUE);
    }

    @Test(expected = InputValidationException.class)
    public void testSetActiveChapterNegative() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveChapter(-1);
    }

    @Test
    public void testGetDefaultActiveCourse() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        try{
            sessionManager.getActiveCourse();
        } catch (CourseNotFoundException ignored) {}
        assertEquals(1, sessionManager.getActiveCourse().id());
    }

    @Test
    public void testGetActiveCourse() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        assertNotNull(sessionManager.getActiveCourse());
    }

    @Test
    public void testGetCourseList() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.getCourseList());
    }

    @Test
    public void testGetActiveCourseChapters() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        assertNotNull(sessionManager.getActiveCourseChapters());
    }

    @Test(expected = CourseNotFoundException.class)
    public void testGetActiveCourseChaptersNoCourse() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.getActiveCourseChapters());
    }

    @Test
    public void testCalculateProgressPercentage() throws CourseNotFoundException, SQLException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        int completePercent = sessionManager.calculateProgressPercentage(sessionManager.getActiveCourse());
        assertTrue(completePercent>=0); //mostly checking for error
    }

    @Test(expected = CourseNotFoundException.class)
    public void testCalculateProgressPercentageNull() throws CourseNotFoundException, SQLException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        int completePercent = sessionManager.calculateProgressPercentage(null);
    }

    @Test(expected = CourseNotFoundException.class)
    public void testCalculateProgressPercentageNoneCourse() throws CourseNotFoundException, SQLException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        int completePercent = sessionManager.calculateProgressPercentage(CourseObjFactory.getNoneCourse());
    }

    @Test
    public void testSetActiveCourse() throws DataInaccessibleException, CourseNotFoundException, AccountPermissionException {
        String user = "SA";
        sessionManager.guestLogin(user);
        List<CourseObj> courses = sessionManager.getCourseList();
        if (!courses.isEmpty()) {
            sessionManager.setActiveCourse(0);
            assertNotNull(sessionManager.getActiveCourse());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testGetActiveCourseWithoutLogin() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.getActiveCourse();
    }

    //State specific integration tests
    @Test
    public void testAutoLoginDisabled() throws CourseNotFoundException, AccountPermissionException {
        assertFalse(sessionManager.autoLogin());
    }

    @Test
    public void testAutoLoginNoLocalPreference() throws CourseNotFoundException, AccountPermissionException, SQLException {
        SqlTestRunner.executeUpdate("DELETE FROM LOCAL_PREFERENCES");
        Exception e = assertThrows(DataInaccessibleException.class, ()-> {
            sessionManager.autoLogin();
        });

        assertEquals(Strings.CannotFindPreferences, e.getMessage());
    }

    @Test
    public void testAutoLoginAutoLoginFalse() throws CourseNotFoundException, AccountPermissionException, SQLException {
        SqlTestRunner.executeUpdate("UPDATE LOCAL_PREFERENCES SET autologin=FALSE, activeAccountId=1");
        assertFalse(sessionManager.autoLogin());
    }

    @Test
    public void testAutoLoginAccountInvalid() throws CourseNotFoundException, AccountPermissionException, SQLException {
        SqlTestRunner.executeUpdate("UPDATE LOCAL_PREFERENCES SET autologin=TRUE, activeAccountId=0");
        assertFalse(sessionManager.autoLogin());
    }

    @Test (expected = AccountNotFoundException.class)
    public void testAutoLoginAccountNotFound() throws CourseNotFoundException, AccountPermissionException, SQLException {
        SqlTestRunner.executeUpdate("UPDATE LOCAL_PREFERENCES SET autologin=TRUE, activeAccountId=123456789");
        sessionManager.autoLogin();
    }

    @Test
    public void testAutoLoginSuccess() throws CourseNotFoundException, AccountPermissionException, SQLException {
        SqlTestRunner.executeUpdate("UPDATE LOCAL_PREFERENCES SET autologin=TRUE, activeAccountId=2");
        assertTrue(sessionManager.autoLogin());
    }

    //login existing account/not existing
    @Test
    public void testLoginExistingAccount() throws CourseNotFoundException, AccountPermissionException, SQLException, DataInaccessibleException {
        SqlTestRunner.executeUpdate("INSERT INTO ACCOUNT VALUES (DEFAULT, 'EXAMPLE', TRUE, 1, 'john.doe', 'password')");
        sessionManager.guestLogin("EXAMPLE");
        AccountObj account = sessionManager.getActiveAccount();
        assertEquals("john.doe", account.getUsername());
    }

    @Test
    public void testLoginNotNonGuestAccount() throws CourseNotFoundException, AccountPermissionException, SQLException, DataInaccessibleException {
        SqlTestRunner.executeUpdate("INSERT INTO ACCOUNT VALUES (DEFAULT, 'EXAMPLE', FALSE, 1, 'john.doe', 'password')");
        sessionManager.guestLogin("EXAMPLE");
        AccountObj account = sessionManager.getActiveAccount();
        assertEquals("EXAMPLE", account.getUsername());
    }

    @Test
    public void testLoginExistingAccountOverload() throws CourseNotFoundException, AccountPermissionException, SQLException, DataInaccessibleException {
        SqlTestRunner.executeUpdate("INSERT INTO ACCOUNT VALUES (DEFAULT, 'EXAMPLE', TRUE, 1, 'john.doe', 'password')");
        sessionManager.guestLogin("EXAMPLE", false);
        AccountObj account = sessionManager.getActiveAccount();
        assertEquals("john.doe", account.getUsername());
    }

    @Test
    public void testLoginNotNonGuestAccountOverload() throws CourseNotFoundException, AccountPermissionException, SQLException, DataInaccessibleException {
        SqlTestRunner.executeUpdate("INSERT INTO ACCOUNT VALUES (DEFAULT, 'EXAMPLE', FALSE, 1, 'john.doe', 'password')");
        sessionManager.guestLogin("EXAMPLE", false);
        AccountObj account = sessionManager.getActiveAccount();
        assertEquals("EXAMPLE", account.getUsername());
    }

    @Test
    public void testLogoutAutoLogin() throws SQLException {
        SqlTestRunner.executeUpdate("UPDATE LOCAL_PREFERENCES SET autologin=TRUE, accountId=2");
        sessionManager.logout();
        assertFalse(sessionManager.autoLogin());
    }

    @Test
    public void testLoginLogoutAutoLogin() throws SQLException {
        SqlTestRunner.executeUpdate("UPDATE LOCAL_PREFERENCES SET autologin=TRUE, accountId=2");
        assertTrue(sessionManager.autoLogin());
        sessionManager.logout();
        assertFalse(sessionManager.autoLogin());
    }
}
