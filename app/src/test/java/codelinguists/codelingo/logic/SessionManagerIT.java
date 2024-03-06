package codelinguists.codelingo.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.QuizObj;
import CodeLinguists.codelingo.exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.exceptions.NoItemSelectedException;
import CodeLinguists.codelingo.logic.AccountHandler;
import CodeLinguists.codelingo.logic.CourseHandler;
import CodeLinguists.codelingo.logic.IAccountHandler;
import CodeLinguists.codelingo.logic.ICourseHandler;
import CodeLinguists.codelingo.logic.IQuizHandler;
import CodeLinguists.codelingo.logic.IQuizIterator;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.QuizHandler;
import CodeLinguists.codelingo.logic.SessionManager;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.persistence.IQuizData;
import codelinguists.codelingo.utils.TestUtils;

public class SessionManagerIT {
    private File tempDB;
    private SessionManager sessionManager;
    private IQuizHandler quizHandler;
    private IAccountHandler accountHandler;
    private ICourseHandler courseHandler;
    @Before
    public void setup() throws IOException {
        this.tempDB = TestUtils.copyDB();
//        quizHandler = Services.getQuizHandler();
//        accountHandler = Services.getAccountHandler();
//        courseHandler = Services.getCourseHandler();
//        sessionManager = new SessionManager(quizHandler, accountHandler, courseHandler);

    }

    @After
    public void reset() {
        this.tempDB.delete();
        Services.resetObjects();
    }

    @Test
    public void testGuestLogin() throws SQLException, CourseNotFoundException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.getActiveCourse());
    }

    @Test(expected = NoItemSelectedException.class)
    public void testStartQuizWithoutCourse() {
        sessionManager.startQuiz();
    }

    @Test
    public void testSetActiveCourse() throws CourseNotFoundException, SQLException {
        String user = "SA";
        sessionManager.guestLogin(user);
        List<CourseObj> courses = sessionManager.getCourseList();
        if (!courses.isEmpty()) {
            sessionManager.setActiveCourse(0);
            assertNotNull(sessionManager.getActiveCourse());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testGetActiveCourseWithoutLogin() throws CourseNotFoundException {
        sessionManager.getActiveCourse();
    }

    //@Test
    //public void testSetAndGetActiveChapter() {
    //    int chapterId = 1;
    //    sessionManager.setActiveChapter(chapterId);
    //    assertEquals(chapterId, sessionManager);
    //}

    @Test
    public void testGetActiveCourseChapters() throws CourseNotFoundException, SQLException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.getActiveCourseChapters());
    }

    @Test
    public void testCalculateProgressPercentage() throws CourseNotFoundException, SQLException {
        String user = "SA";
        sessionManager.guestLogin(user);
        assertNotNull(sessionManager.calculateProgressPercentage(sessionManager.getActiveCourse()));
    }
}
