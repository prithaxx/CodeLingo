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
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountPermissionException;
import CodeLinguists.codelingo.exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.exceptions.NoItemSelectedException;
import CodeLinguists.codelingo.logic.ISessionManager;
import codelinguists.codelingo.utils.SqlDbIT;
import codelinguists.codelingo.utils.TestUtils;

public class SessionManagerIT extends SqlDbIT {

    ISessionManager sessionManager;

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

    @Test
    public void testGuestLoginOverload() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException {
        String user = "SA";
        sessionManager.guestLogin(user, false);
        assertNotNull(sessionManager.getActiveAccount());
    }

    @Test
    public void testNoAutoLogin() throws AccountPermissionException, DataInaccessibleException, CourseNotFoundException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.getActiveAccount());
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
    public void testStartQuizWithoutCourse() {
        sessionManager.startQuiz();
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

    //@Test
    //public void testSetAndGetActiveChapter() {
    //    int chapterId = 1;
    //    sessionManager.setActiveChapter(chapterId);
    //    assertEquals(chapterId, sessionManager);
    //}

    @Test
    public void testGetActiveCourseChapters() throws CourseNotFoundException, SQLException, DataInaccessibleException, AccountPermissionException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.getActiveCourseChapters());
    }

    @Test
    public void testCalculateProgressPercentage() throws CourseNotFoundException, SQLException, AccountPermissionException, DataInaccessibleException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.calculateProgressPercentage(sessionManager.getActiveCourse()));
    }
}
