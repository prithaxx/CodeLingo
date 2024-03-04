package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.logic.SessionManager;

public class SessionManagerTest {
    @Before
    public void setup() {
        Services.resetObjects();
    }

    @Test
    public void guestLoginTest() throws SQLException {
        Services.getSessionManager().guestLogin("test");
    }

    @Test
    public void startQuiz() {
        assertNotNull(Services.getSessionManager().startQuiz());
    }

    @Test
    public void getActiveCourse() throws CourseNotFoundException {
        assertNotNull(Services.getSessionManager().getActiveCourse());
    }

    @Test
    public void setActiveChapter() {
        Services.getSessionManager().setActiveChapter(1);
    }



}
