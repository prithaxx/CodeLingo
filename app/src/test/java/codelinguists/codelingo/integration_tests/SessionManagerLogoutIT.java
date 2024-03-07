package codelinguists.codelingo.integration_tests;

import org.junit.Test;

import java.io.IOException;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.CourseObjFactory;
import CodeLinguists.codelingo.exceptions.AccountPermissionException;
import CodeLinguists.codelingo.exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.logic.ISessionManager;
import codelinguists.codelingo.utils.SqlDbIT;

public class SessionManagerLogoutIT extends SqlDbIT {
    ISessionManager sessionManager;

    @Override
    public void setup() throws IOException {
        super.setup();
        sessionManager = Services.getSessionManager();
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutGetActiveCourse() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.getActiveCourse();
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutSetActiveCourse() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.setActiveCourse(1);
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutGetCourseList() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.getCourseList();
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutSetActiveChapter() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.setActiveChapter(1);
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutGetActiveCourseChapters() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.getActiveCourseChapters();
    }

    @Test(expected = AccountPermissionException.class)
    public void logoutCalculateProgressPercentage() throws CourseNotFoundException, AccountPermissionException {
        sessionManager.calculateProgressPercentage(CourseObjFactory.getNoneCourse());
    }
}
