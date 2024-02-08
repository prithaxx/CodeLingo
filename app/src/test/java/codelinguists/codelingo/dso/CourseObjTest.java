package codelinguists.codelingo.dso;

import static org.junit.Assert.*;

import org.junit.Test;

import CodeLinguists.codelingo.dso.CourseObj;

public class CourseObjTest {
    @Test
    public void allValues() {
        CourseObj courseObj = new CourseObj(1, "name", "desc", false, false);
        assertEquals(courseObj.id(), 1);
        assertEquals(courseObj.name(), "name");
        assertEquals(courseObj.description(), "desc");
        assertFalse(courseObj.isStarted());
        assertFalse(courseObj.isCompleted());
    }

    @Test
    public void nullValues() {
        CourseObj courseObj = new CourseObj(1, null, null, false, false);
        assertEquals(courseObj.id(), 1);
        assertNull(courseObj.name());
        assertNull(courseObj.description());
        assertFalse(courseObj.isStarted());
        assertFalse(courseObj.isCompleted());
    }
}
