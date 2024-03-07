package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.logic.logicExceptions.AccountPermissionException;
import CodeLinguists.codelingo.persistence.persistenceExceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.persistenceExceptions.DataInaccessibleException;

public class SessionManagerTest {
    @Before
    public void setup() {
        Services.resetObjects();
    }

    @Test
    public void guestLoginTest() throws DataInaccessibleException, CourseNotFoundException, AccountPermissionException {
        Services.getSessionManager().guestLogin("test");
    }

    @Test
    public void startQuiz() {
        assertNotNull(Services.getSessionManager().startQuiz());
    }

    @Test
    public void getActiveCourse() throws CourseNotFoundException, AccountPermissionException {
        assertNotNull(Services.getSessionManager().getActiveCourse());
    }

    @Test
    public void setActiveChapter() {
        Services.getSessionManager().setActiveChapter(1);
    }



}
