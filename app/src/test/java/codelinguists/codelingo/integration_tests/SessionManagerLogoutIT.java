package codelinguists.codelingo.integration_tests;

import org.junit.Test;

import java.io.IOException;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.logic_exceptions.NoItemSelectedException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import codelinguists.codelingo.utils.SqlDbIT;

public class SessionManagerLogoutIT extends SqlDbIT {
    ISessionManager sessionManager;

    @Override
    public void setup() throws IOException {
        super.setup();
        sessionManager = Services.getSessionManager();
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutSetChapterComplete() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.setChapterComplete();
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutGetActiveAccount() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.getActiveAccount();
    }

    @Test(expected = NoItemSelectedException.class)
    public void logoutStartQuiz() throws CourseNotFoundException, AccountPermissionException, NoItemSelectedException {
        sessionManager.startQuiz();
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutGetActiveCourse() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.getActiveCourse();
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutSetActiveCourse() throws CourseNotFoundException, AccountPermissionException, InputValidationException {
        sessionManager.setActiveCourse(1);
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutGetCourseList() throws AccountPermissionException {
        sessionManager.getCourseList();
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutSetActiveChapter() throws AccountPermissionException, InputValidationException {
        sessionManager.setActiveChapter(1);
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutGetActiveCourseChapters() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.getActiveCourseChapters();
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutCalculateProgressPercentage() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.calculateProgressPercentage();
    }
}
