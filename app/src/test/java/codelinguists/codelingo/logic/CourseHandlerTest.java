package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.logic.CourseHandler;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.stubs.CourseDataStub;

public class CourseHandlerTest {
    private CourseHandler courseHandler;
    private ICourseData mockedCourseData;

    @Before
    public void setUp(){
        Services.resetObjects();
        this.mockedCourseData = new MockCourseData();
        this.courseHandler = new CourseHandler(new CourseDataStub());
    }
    //Stub tests
    @Test
    public void emptyConstructorTest(){
        CourseHandler course = new CourseHandler();
        CourseObj newCourse = course.getCourseById(1);
        assertEquals(newCourse.getId(), 1);
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

    //mockedCourseData test
    @Test
    public void getExistingCourseTest(){
        CourseObj newCourse = mockedCourseData.getCourseById(3);
        assertEquals(newCourse.getDescription(), "test");
    }

    @Test
    public void getNoTExistTest(){
        CourseObj newCourse = mockedCourseData.getCourseById(999);
        assertNull(newCourse);
    }

    private class MockCourseData implements ICourseData {
        private List<CourseObj> courses;
    
        public MockCourseData() {
            courses = new ArrayList<>();
            courses.add(new CourseObj(1, "test", "test", true, true));
            courses.add(new CourseObj(2, "test", "test", false, false));
            courses.add(new CourseObj(3, "test", "test", false, false));
            courses.add(new CourseObj(4, "test", "test", false, false));
        }
    
        @Override
        public List<CourseObj> getCourses() {
            return new ArrayList<>(courses);
        }
    
        @Override
        public CourseObj getCourseById(int id) {
            return courses.stream().filter(course -> course.getId() == id).findFirst().orElse(null);
        }
    }
}
