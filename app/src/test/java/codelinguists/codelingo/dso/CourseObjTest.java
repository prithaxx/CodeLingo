package codelinguists.codelingo.dso;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CodeLinguists.codelingo.dso.CourseObj;

public class CourseObjTest {
    @Test
    public void allValues() {
        CourseObj courseObj = new CourseObj(1, "name", "desc", false, false);
        assertEquals(courseObj.getId(), 1);
        assertEquals(courseObj.getName(), "name");
        assertEquals(courseObj.getDescription(), "desc");
        assertFalse(courseObj.isStarted());
        assertFalse(courseObj.isCompleted());
    }

    @Test
    public void nullValues() {
        CourseObj courseObj = new CourseObj(1, null, null, false, false);
        assertEquals(courseObj.getId(), 1);
        assertNull(courseObj.getName());
        assertNull(courseObj.getDescription());
        assertFalse(courseObj.isStarted());
        assertFalse(courseObj.isCompleted());
    }
}
