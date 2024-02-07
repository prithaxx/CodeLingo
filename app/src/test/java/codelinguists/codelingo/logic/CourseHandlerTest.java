package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.logic.CourseHandler;
import CodeLinguists.codelingo.persistence.stubs.CourseDataStub;

public class CourseHandlerTest {
    private CourseHandler courseHandler;

    @Before
    public void setUp(){
        Services.resetObjects();
        this.courseHandler = new CourseHandler(new CourseDataStub());
    }

    @Test
    public void getCourses() {
        List<CourseObj> courses = courseHandler.getCourses();
        assertNotNull(courses);
        assertTrue(courses.size()>0);
    }
    @Test
    public void getCourseByIdSuccess() {
        int courseId = 1;
        CourseObj course = courseHandler.getCourseById(courseId);
        assertNotNull(course);
        assertEquals(courseId, course.getId());
    }

    @Test(expected = CourseNotFoundException.class)
    public void getCourseByIdNotFound() {
        int invalidCourseId = 999; // Assuming 999 is an invalid course ID
        courseHandler.getCourseById(invalidCourseId);
    }
}
