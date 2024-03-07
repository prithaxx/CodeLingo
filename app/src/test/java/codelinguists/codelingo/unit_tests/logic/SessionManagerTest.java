package codelinguists.codelingo.unit_tests.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.logic_exceptions.NoItemSelectedException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;

public class SessionManagerTest {
    @Before
    public void setup() {
        Services.resetObjects();
    }

    @Test
    public void guestLoginTest() throws DataInaccessibleException, CourseNotFoundException, AccountPermissionException, InputValidationException {
        Services.getSessionManager().guestLogin("test");
    }

    @Test
    public void startQuiz() throws NoItemSelectedException {
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
