package codelinguists.codelingo.unit_tests.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CodeLinguists.codelingo.logic.SessionManager;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.logic_exceptions.NoItemSelectedException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;
import codelinguists.codelingo.unit_tests.logic.test_doubles.AccountHandlerTestDouble;
import codelinguists.codelingo.unit_tests.logic.test_doubles.CourseHandlerTestDouble;
import codelinguists.codelingo.unit_tests.logic.test_doubles.QuizHandlerTestDouble;

public class SessionManagerTest {
    QuizHandlerTestDouble quizHandler;
    AccountHandlerTestDouble accountHandler;
    CourseHandlerTestDouble courseHandler;
    SessionManager sessionManager;

    @Before
    public void setup() {
        quizHandler = new QuizHandlerTestDouble();
        accountHandler = new AccountHandlerTestDouble();
        courseHandler = new CourseHandlerTestDouble();
        sessionManager = new SessionManager(quizHandler, accountHandler, courseHandler);
    }

    @Test
    public void testGuestLogin() throws DataInaccessibleException, AccountPermissionException, InputValidationException {
        sessionManager.guestLogin("tmp", false);
        assertSame(accountHandler.guestLogin(""), sessionManager.getActiveAccount());
    }

    @Test (expected = DataInaccessibleException.class)
    public void testGuestLoginNoAccount() throws DataInaccessibleException, InputValidationException {
        accountHandler.exceptionThrowIndex = 0;
        sessionManager.guestLogin("tmp", false);
    }

    @Test
    public void testGuestLoginNoActiveCourse() throws DataInaccessibleException, AccountPermissionException, InputValidationException {
        courseHandler.doException = true;
        sessionManager.guestLogin("tmp", false);
        assertEquals(accountHandler.guestLoginResponse, sessionManager.getActiveAccount());
    }

    @Test
    public void testAutoLogin() throws AccountPermissionException {
        sessionManager.autoLogin();
        assertSame(accountHandler.autoLoginResponse, sessionManager.getActiveAccount());
    }

    @Test
    public void testAutoLoginFalse() {
        accountHandler.autoLoginResponse = null;
        assertFalse (sessionManager.autoLogin());
    }

    @Test (expected = AccountPermissionException.class)
    public void testLogout() throws AccountPermissionException {
        sessionManager.autoLogin();
        sessionManager.logout();
        sessionManager.getActiveAccount();
    }

    @Test
    public void testStartQuiz() throws AccountPermissionException, InputValidationException, CourseNotFoundException, NoItemSelectedException {
        sessionManager.autoLogin();
        sessionManager.setActiveChapter(1);
        sessionManager.setActiveCourse(1);
        assertSame(quizHandler.getQuizResponse, sessionManager.startQuiz());
    }

    @Test (expected = NoItemSelectedException.class)
    public void testStartQuizNoCourse() throws NoItemSelectedException {
        courseHandler.getActiveCourseResponse = null;
        sessionManager.autoLogin();
        sessionManager.startQuiz();
    }

    @Test (expected = NoItemSelectedException.class)
    public void testStartQuizNoChapter() throws NoItemSelectedException {
        sessionManager.autoLogin();
        sessionManager.startQuiz();
    }

    @Test (expected = AccountPermissionException.class)
    public void testGetActiveCourseNoAccount() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.getActiveCourse();
    }

    @Test
    public void testGetActiveCourse() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.autoLogin();
        assertSame(courseHandler.getActiveCourseResponse ,sessionManager.getActiveCourse());
    }

    @Test
    public void testGetActiveCourseNotFound() throws AccountPermissionException {
        Exception except = null;
        sessionManager.autoLogin();
        courseHandler.doException = true;
        try {
            sessionManager.getActiveCourse();
        } catch (CourseNotFoundException e) {
            except = e;
        }
        assertNotNull(except);
    }

    @Test (expected = AccountPermissionException.class)
    public void testSetActiveCourseNotSignedIn() throws CourseNotFoundException, AccountPermissionException, InputValidationException {
        sessionManager.setActiveCourse(1);
    }

    @Test
    public void testSetActiveCourse() throws CourseNotFoundException, AccountPermissionException, InputValidationException {
        sessionManager.autoLogin();
        sessionManager.setActiveCourse(1);
        assertEquals(1, accountHandler.setActiveCourseOutput);
    }

    @Test (expected = AccountPermissionException.class)
    public void testGetCourseListSignedOut() throws AccountPermissionException {
        sessionManager.getCourseList();
    }

    @Test
    public void testGetCourseList() throws AccountPermissionException {
        sessionManager.autoLogin();
        assertSame(courseHandler.getCourseListResponse, sessionManager.getCourseList());
    }

    @Test (expected = AccountPermissionException.class)
    public void testSetActiveChapterSignedOut() throws AccountPermissionException, InputValidationException {
        sessionManager.setActiveChapter(1);
    }

    @Test (expected = InputValidationException.class)
    public void testSetActiveChapterInvalid() throws AccountPermissionException, InputValidationException {
        sessionManager.autoLogin();
        sessionManager.setActiveChapter(-1);
    }

    @Test
    public void testSetActiveChapter() throws AccountPermissionException, InputValidationException, NoItemSelectedException {
        sessionManager.autoLogin();
        sessionManager.setActiveChapter(12);
        sessionManager.startQuiz();
        assertEquals(12, quizHandler.startQuizChapterOutput);
    }

    @Test (expected = AccountPermissionException.class)
    public void testGetActiveCourseChaptersSignedOut() throws AccountPermissionException, CourseNotFoundException {
        sessionManager.getActiveCourseChapters();
    }

    @Test
    public void testGetActiveCourseChapters() throws AccountPermissionException, CourseNotFoundException {
        sessionManager.autoLogin();
        assertSame(courseHandler.getActiveCourseChaptersResponse, sessionManager.getActiveCourseChapters());
    }

    @Test (expected = AccountPermissionException.class)
    public void testCalculateProgressPercentageSignedOut() throws AccountPermissionException, CourseNotFoundException {
        sessionManager.calculateProgressPercentage();
    }

    @Test
    public void testCalculateProgressPercentageChapters() throws AccountPermissionException, CourseNotFoundException {
        sessionManager.autoLogin();
        assertSame(courseHandler.calculateProgressPercentageResponse, sessionManager.calculateProgressPercentage());
    }
}
