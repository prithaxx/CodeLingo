package codelinguists.codelingo.logic;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.logic.CourseHandler;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.persistence.stubs.AccountDataStub;
import CodeLinguists.codelingo.persistence.stubs.ChapterDataStub;
import CodeLinguists.codelingo.persistence.stubs.CourseDataStub;

public class CourseHandlerTest {
    private IAccountData accountDataStub;
    private CourseHandler courseHandler;

    @Before
    public void setUp() {
        CourseDataStub courseDataStub = new CourseDataStub();
        accountDataStub = new AccountDataStub();
        ChapterDataStub chapterDataStub = new ChapterDataStub();
        courseHandler = new CourseHandler(courseDataStub, chapterDataStub);
    }

    @Test
    public void testGetActiveCourse() throws CourseNotFoundException, DataInaccessibleException {
        AccountObj account = accountDataStub.createGuestAccount("testUser");
        account.setActiveCourseId(1); // Assuming an ID that exists in CourseDataStub

        CourseObj course = courseHandler.getActiveCourse(account);

        assertNotNull(course);
        assertEquals(1, course.id());
    }

    @Test
    public void testGetCourseList() throws DataInaccessibleException {
        AccountObj account = accountDataStub.createGuestAccount("testUser");

        List<CourseObj> courseList = courseHandler.getCourseList(account);
        assertNotNull("Course list should not be null", courseList);
        assertFalse("Course list should not be empty", courseList.isEmpty());
    }

    @Test
    public void testGetActiveCourseChapters() throws DataInaccessibleException, CourseNotFoundException {
        AccountObj account = accountDataStub.createGuestAccount("testUser");
        accountDataStub.setActiveCourse(account.getId(), 1);
        List<ChapterObj> chapters = courseHandler.getActiveCourseChapters(account);
        assertNotNull(chapters);
        assertFalse(chapters.isEmpty());
        assertEquals(2, chapters.size());
    }

    @Test
    public void testCalculateProgressPercentage() throws DataInaccessibleException {
        AccountObj account = accountDataStub.createGuestAccount("testUser");
        accountDataStub.setActiveCourse(account.getId(), 1);

        int progressPercentage;
        try {
            progressPercentage = courseHandler.calculateProgressPercentage(account);
        } catch (CourseNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Assert expected progress percentage
        int expectedPercentage = 50;
        assertEquals(expectedPercentage, progressPercentage);
    }
}




