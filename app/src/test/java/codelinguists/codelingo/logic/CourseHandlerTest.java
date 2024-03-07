package codelinguists.codelingo.logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
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
    private AccountObj testAccountObj;
    private CourseHandler courseHandler;
    private CourseDataStub courseDataStub;
    private ChapterDataStub chapterDataStub;

    @Before
    public void setUp() {
        courseDataStub = new CourseDataStub();
        accountDataStub = new AccountDataStub();
        chapterDataStub = new ChapterDataStub();
        courseHandler = new CourseHandler(courseDataStub, chapterDataStub);
    }

    @Test
    public void testGetActiveCourse() throws CourseNotFoundException, DataInaccessibleException {
        AccountObj account = accountDataStub.createGuestAccount("testUser");
        account.setActiveCourseId(1); // Assuming an ID that exists in CourseDataStub

        CourseObj course = courseHandler.getActiveCourse(account);

        Assert.assertNotNull(course);
        Assert.assertEquals(1, course.id());
    }

    @Test
    public void testGetCourseList() throws DataInaccessibleException {
        AccountObj account = accountDataStub.createGuestAccount("testUser");

        List<CourseObj> courseList = courseHandler.getCourseList(account);
        Assert.assertNotNull("Course list should not be null", courseList);
        Assert.assertFalse("Course list should not be empty", courseList.isEmpty());
    }

    @Test
    public void testGetActiveCourseChapters() throws DataInaccessibleException, CourseNotFoundException {
        AccountObj account = accountDataStub.createGuestAccount("testUser");
        //account.setActiveCourseId(1); // Assuming an ID that exists in CourseDataStub

        accountDataStub.setActiveCourse(account.getId(), 1);
        List<ChapterObj> chapters = courseHandler.getActiveCourseChapters(account);
        Assert.assertNotNull(chapters);
        Assert.assertFalse(chapters.isEmpty());
        Assert.assertEquals(2, chapters.size());


    }


}




