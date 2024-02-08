package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CodeLinguists.codelingo.logic.SessionManager;

public class SessionManagerTest {
    @Before
    public void setup() {
        SessionManager.clearSessionData();
    }

    @Test
    public void guestLoginTest() {
        SessionManager.newInstance().guestLogin("test");
    }

    @Test
    public void startQuiz() {
        assertNotNull(SessionManager.newInstance().startQuiz());
    }

    @Test
    public void getActiveCourse() {
        assertNotNull(SessionManager.newInstance().getActiveCourse());
    }

    @Test
    public void setActiveChapter() {
        SessionManager.newInstance().setActiveChapter(1);
    }



}
