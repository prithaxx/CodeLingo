package codelinguists.codelingo.integration_tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.logic_exceptions.NoItemSelectedException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;
import codelinguists.codelingo.utils.SqlDbIT;

public class SessionManagerIT extends SqlDbIT {

    ISessionManager sessionManager;

    //General session manager tests
    @Override
    public void setup() throws IOException {
        super.setup();
        sessionManager = Services.getSessionManager();
    }

    @Test
    public void testGuestLogin() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.getActiveAccount());
    }

    @Test (expected = InputValidationException.class)
    public void testGuestLoginEmpty() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException, InputValidationException {
        String user = "";
        sessionManager.guestLogin(user);
    }

    @Test (expected = InputValidationException.class)
    public void testGuestLoginNull() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException, InputValidationException {
        String user = null;
        sessionManager.guestLogin(user);
    }

    @Test
    public void testGuestLoginOverload() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user, false);
        assertNotNull(sessionManager.getActiveAccount());
    }

    @Test (expected = InputValidationException.class)
    public void testGuestLoginOverloadEmpty() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException, InputValidationException {
        String user = "";
        sessionManager.guestLogin(user, false);
    }

    @Test (expected = InputValidationException.class)
    public void testGuestLoginOverloadNull() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException, InputValidationException {
        String user = null;
        sessionManager.guestLogin(user, false);
    }

    @Test
    public void testNoAutoLogin() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.getActiveAccount());
    }

    @Test(expected = AccountPermissionException.class)
    public void testLogoutAccountWithoutSignin() throws AccountPermissionException {
        sessionManager.logout();
        sessionManager.getActiveAccount();
    }

    @Test(expected = AccountPermissionException.class)
    public void testLogoutAccount() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.logout();
        sessionManager.getActiveAccount();
    }

    @Test(expected = AccountPermissionException.class)
    public void testLogoutCourse() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.logout();
        sessionManager.getActiveCourse();
    }

    @Test(expected = AccountPermissionException.class)
    public void testLogoutChapter() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.logout();
        sessionManager.getActiveCourse();
    }

    @Test(expected = NoItemSelectedException.class)
    public void testStartQuizNoCourse() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.startQuiz();
    }

    @Test(expected = NoItemSelectedException.class)
    public void testStartQuizNoChapter() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.startQuiz();
    }

    @Test
    public void testStartQuiz() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException, NoItemSelectedException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        sessionManager.setActiveChapter(1);
        assertNotNull(sessionManager.startQuiz());
    }

    @Test(expected = InputValidationException.class)
    public void testSetActiveCourseNegative() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(-1);
    }

    @Test(expected = CourseNotFoundException.class)
    public void testSetActiveCourseOutOfRange() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(Integer.MAX_VALUE);
    }

    @Test(expected = InputValidationException.class)
    public void testSetActiveChapterNegative() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveChapter(-1);
    }

    @Test
    public void testGetDefaultActiveCourse() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        try{
            sessionManager.getActiveCourse();
        } catch (CourseNotFoundException ignored) {}
        assertEquals(1, sessionManager.getActiveCourse().id());
    }

    @Test
    public void testGetActiveCourse() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        assertNotNull(sessionManager.getActiveCourse());
    }

    @Test
    public void testGetCourseList() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.getCourseList());
    }

    @Test
    public void testGetActiveCourseChapters() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(1);
        assertNotNull(sessionManager.getActiveCourseChapters());
    }

    @Test(expected = CourseNotFoundException.class)
    public void testGetActiveCourseChaptersNoCourse() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        sessionManager.setActiveCourse(0);
        assertNotNull(sessionManager.getActiveCourseChapters());
    }

    @Test
    public void testCalculateProgressPercentage() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        int completePercent = sessionManager.calculateProgressPercentage();
        assertTrue(completePercent>=0); //mostly checking for error
    }

    @Test
    public void testSetActiveCourse() throws DataInaccessibleException, CourseNotFoundException, AccountPermissionException, InputValidationException {
        String user = "SA";
        sessionManager.guestLogin(user);
        List<CourseObj> courses = sessionManager.getCourseList();
        sessionManager.setActiveCourse(1);
        assertNotNull(sessionManager.getActiveCourse());
    }

    @Test(expected = AccountPermissionException.class)
    public void testGetActiveCourseWithoutLogin() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.getActiveCourse();
    }

    //State specific integration tests
    @Test
    public void testAutoLoginDisabled() {
        assertFalse(sessionManager.autoLogin());
    }

    @Test
    public void testAutoLoginNoLocalPreference() throws SQLException {
        SqlTestRunner.executeUpdate("DELETE FROM LOCAL_PREFERENCES");
        assertFalse(sessionManager.autoLogin());
    }

    @Test
    public void testAutoLoginAutoLoginFalse() throws SQLException {
        SqlTestRunner.executeUpdate("UPDATE LOCAL_PREFERENCES SET autologin=FALSE, activeAccountId=1");
        assertFalse(sessionManager.autoLogin());
    }

    @Test
    public void testAutoLoginAccountInvalid() throws SQLException {
        SqlTestRunner.executeUpdate("UPDATE LOCAL_PREFERENCES SET autologin=TRUE, activeAccountId=0");
        assertFalse(sessionManager.autoLogin());
    }

    @Test
    public void testAutoLoginAccountNotFound() throws SQLException {
        SqlTestRunner.executeUpdate("UPDATE LOCAL_PREFERENCES SET autologin=TRUE, activeAccountId=123456789");
        assertFalse(sessionManager.autoLogin());
    }

    @Test
    public void testAutoLoginSuccess() throws SQLException {
        SqlTestRunner.executeUpdate("UPDATE LOCAL_PREFERENCES SET autologin=TRUE, activeAccountId=2");
        assertTrue(sessionManager.autoLogin());
    }

    //login existing account/not existing
    @Test
    public void testLoginExistingAccount() throws CourseNotFoundException, AccountPermissionException, SQLException, DataInaccessibleException, InputValidationException {
        SqlTestRunner.executeUpdate("INSERT INTO ACCOUNT VALUES (DEFAULT, 'John Doe', TRUE, 1, 'EXAMPLE', 'password')");
        sessionManager.guestLogin("EXAMPLE");
        AccountObj account = sessionManager.getActiveAccount();
        assertEquals("John Doe", account.getName());
    }

    @Test
    public void testLoginNotNonGuestAccount() throws CourseNotFoundException, AccountPermissionException, SQLException, DataInaccessibleException, InputValidationException {
        SqlTestRunner.executeUpdate("INSERT INTO ACCOUNT VALUES (DEFAULT, 'John Doe', FALSE, 1, 'EXAMPLE', 'password')");
        sessionManager.guestLogin("EXAMPLE");
        AccountObj account = sessionManager.getActiveAccount();
        assertEquals("EXAMPLE", account.getUsername());
    }

    @Test
    public void testLoginExistingAccountOverload() throws CourseNotFoundException, AccountPermissionException, SQLException, DataInaccessibleException, InputValidationException {
        SqlTestRunner.executeUpdate("INSERT INTO ACCOUNT VALUES (DEFAULT, 'John Doe', TRUE, 1, 'EXAMPLE', 'password')");
        sessionManager.guestLogin("EXAMPLE", false);
        AccountObj account = sessionManager.getActiveAccount();
        assertEquals("John Doe", account.getName());
    }

    @Test
    public void testLoginNotNonGuestAccountOverload() throws CourseNotFoundException, AccountPermissionException, SQLException, DataInaccessibleException, InputValidationException {
        SqlTestRunner.executeUpdate("INSERT INTO ACCOUNT VALUES (DEFAULT, 'John Doe', FALSE, 1, 'EXAMPLE', 'password')");
        sessionManager.guestLogin("EXAMPLE", false);
        AccountObj account = sessionManager.getActiveAccount();
        assertEquals("EXAMPLE", account.getUsername());
    }

    @Test
    public void testLogoutAutoLogin() throws SQLException {
        SqlTestRunner.executeUpdate("UPDATE LOCAL_PREFERENCES SET autologin=TRUE, activeAccountId=2");
        sessionManager.logout();
        assertFalse(sessionManager.autoLogin());
    }

    @Test
    public void testLoginLogoutAutoLogin() throws SQLException {
        SqlTestRunner.executeUpdate("UPDATE LOCAL_PREFERENCES SET autologin=TRUE, activeAccountId=2");
        assertTrue(sessionManager.autoLogin());
        sessionManager.logout();
        assertFalse(sessionManager.autoLogin());
    }

    @Test
    public void testCalculateProgressPercentagePositive() throws CourseNotFoundException, AccountPermissionException, DataInaccessibleException, InputValidationException, SQLException {
        String user = "SA";
        sessionManager.guestLogin(user);
        int accountId = sessionManager.getActiveAccount().getId();
        SqlTestRunner.executeUpdate(String.format("INSERT INTO CHAPTER_COMPLETION VALUES (%d, 1, TRUE, TRUE)", accountId));
        int completePercent = sessionManager.calculateProgressPercentage();
        assertTrue(completePercent>0); //mostly checking for error
    }
}
