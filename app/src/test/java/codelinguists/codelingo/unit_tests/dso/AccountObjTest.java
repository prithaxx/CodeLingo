package codelinguists.codelingo.unit_tests.dso;

import static org.junit.Assert.*;

import org.junit.Test;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;

public class AccountObjTest {
    @Test
    public void allValues() {
        CourseObj courseObj = new CourseObj(1,null, null, false, false);
        AccountObj accObj = new AccountObj(1, "test", true, courseObj.id(), "un", "pw");
        assertEquals(accObj.getId(), 1);
        assertEquals(accObj.getName(), "test");
        assertTrue(accObj.isGuestAccount());
        assertSame(accObj.getActiveCourseId(), courseObj.id());
        assertEquals(accObj.getUsername(), "un");
        assertEquals(accObj.getPassword(), "pw");
    }

    @Test
    public void nullValues() {
        AccountObj accObj = new AccountObj(1, null, false, -1, null, null);
        assertEquals(accObj.getId(), 1);
        assertNull(accObj.getName());
        assertFalse(accObj.isGuestAccount());
        assertEquals(accObj.getActiveCourseId(), -1);
        assertNull(accObj.getUsername());
        assertNull(accObj.getPassword());
    }

    @Test
    public void setActiveTests() {
        CourseObj courseObj = new CourseObj(1,null, null, false, false);
        AccountObj accObj = new AccountObj(1, null, false, -1, null, null);

        assertEquals(accObj.getActiveCourseId(), -1);
        accObj.setActiveCourseId(courseObj.id());
        assertSame(accObj.getActiveCourseId(), courseObj.id());
        accObj.setActiveCourseId(0);
        assertEquals(accObj.getActiveCourseId(), 0);
    }
}
