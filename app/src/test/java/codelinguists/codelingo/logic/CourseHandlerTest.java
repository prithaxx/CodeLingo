package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.logic.CourseHandler;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.ISessionData;

public class CourseHandlerTest {
    private CourseHandler courseHandler;

    @Before
    public void setUp() throws Exception {
        Services.resetObjects();
        ICourseData courseData = Services.getCourseData();
        ISessionData sessionData = Services.getSessionData();

        this.courseHandler = new CourseHandler(courseData, sessionData);
    }

    @Test
    public void getCourses() {
        List<CourseObj> courses = courseHandler.getCourses();
        assertNotNull(courses);
        assertEquals(courses.size(), 0);
    }
    @Test
    public void getCourseByIdSuccess() {
        int courseId = 1; 
        CourseObj newCourse = new CourseObj(courseId,"test","test", false,false);
        CourseObj course = courseHandler.getCourseById(courseId);
        assertNotNull(course);
        assertEquals(courseId, course.getId());
    }

    @Test(expected = CourseNotFoundException.class)
    public void getCourseByIdNotFound() {
        int invalidCourseId = 999; // Assuming 999 is an invalid course ID
        courseHandler.getCourseById(invalidCourseId);
    }

    @Test
    public void getActiveCourse() {
        CourseObj courseObj = courseHandler.getActiveCourse();
        assertNull(courseObj);
    }
}
