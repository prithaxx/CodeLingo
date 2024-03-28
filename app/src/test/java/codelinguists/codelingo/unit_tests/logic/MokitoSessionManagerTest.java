package codelinguists.codelingo.unit_tests.logic;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.logic.IAccountHandler;
import CodeLinguists.codelingo.logic.ICourseHandler;
import CodeLinguists.codelingo.logic.IQuizHandler;
import CodeLinguists.codelingo.logic.SessionManager;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;

public class MokitoSessionManagerTest {
    private SessionManager sessionManager;
    private ICourseHandler courseHandler;
    private IAccountHandler accountHandler;
    private IQuizHandler quizHandler;
    private AccountObj defaultAcc = new AccountObj(1, "name", true, 1, "username", "");
    private CourseObj defaultCourse = new CourseObj(1, "name", "desciption", false, false);
    @Before
    public void setup () {
        this.courseHandler = mock(ICourseHandler.class);
        this.accountHandler = mock(IAccountHandler.class);
        this.quizHandler = mock(IQuizHandler.class);
        this.sessionManager = new SessionManager(quizHandler, accountHandler, courseHandler);
    }

    @Test
    public void testGuestLogin() throws InputValidationException, DataInaccessibleException, CourseNotFoundException, AccountPermissionException {
        when(accountHandler.guestLogin("username")).thenReturn(defaultAcc);
        when(courseHandler.getActiveCourse(defaultAcc)).thenReturn(defaultCourse);
        sessionManager.guestLogin("username");
        assertSame(sessionManager.getActiveAccount(), defaultAcc);
    }

    @Test
    public void testSetChapterComplete() throws InputValidationException, DataInaccessibleException, CourseNotFoundException, AccountPermissionException {
        when(accountHandler.guestLogin("username")).thenReturn(defaultAcc);
        when(courseHandler.getActiveCourse(defaultAcc)).thenReturn(defaultCourse);
        sessionManager.guestLogin("username");
        sessionManager.setActiveChapter(1);
        sessionManager.setChapterComplete();
        verify(courseHandler, times(1)).setChapterComplete(1, defaultAcc);
    }

    @Test (expected = AccountPermissionException.class)
    public void testSetChapterCompleteNotSignedIn() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.setChapterComplete();
    }
}
