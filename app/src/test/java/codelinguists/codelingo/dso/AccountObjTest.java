package codelinguists.codelingo.dso;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;

public class AccountObjTest {
    @Test
    public void allValues() {
        CourseObj courseObj = new CourseObj(1,null, null, false, false);
        AccountObj accObj = new AccountObj(1, "test", true, courseObj, "un", "pw");
        assertEquals(accObj.getId(), 1);
        assertEquals(accObj.getName(), "test");
        assertTrue(accObj.isGuestAccount());
        assertSame(accObj.getActiveCourse(), courseObj);
        assertEquals(accObj.getUsername(), "un");
        assertEquals(accObj.getPassword(), "pw");
    }

    @Test
    public void nullValues() {
        AccountObj accObj = new AccountObj(1, null, false, null, null, null);
        assertEquals(accObj.getId(), 1);
        assertNull(accObj.getName());
        assertFalse(accObj.isGuestAccount());
        assertNull(accObj.getActiveCourse());
        assertNull(accObj.getUsername());
        assertNull(accObj.getPassword());
    }

    @Test
    public void setActiveTests() {
        CourseObj courseObj = new CourseObj(1,null, null, false, false);
        AccountObj accObj = new AccountObj(1, null, false, null, null, null);

        assertNull(accObj.getActiveCourse());
        accObj.setActiveCourse(courseObj);
        assertSame(accObj.getActiveCourse(), courseObj);
        accObj.setActiveCourse(null);
        assertNull(accObj.getActiveCourse());
    }
}
