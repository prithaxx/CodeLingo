package codelinguists.codelingo.unit_tests.dso;

import static org.junit.Assert.*;

import org.junit.Test;

import CodeLinguists.codelingo.dso.ChapterObj;

public class ChapterObjTest {
    @Test
    public void allValues() {
        ChapterObj chapterObj = new ChapterObj(1, "test", 1, "descript", true, true);
        assertEquals(chapterObj.id(), 1);
        assertEquals(chapterObj.name(), "test");
        assertEquals(chapterObj.courseId(), 1);
        assertEquals(chapterObj.description(), "descript");
        assertTrue(chapterObj.isUnlocked());
        assertTrue(chapterObj.isCompleted());
    }

    @Test
    public void nullValues() {
        ChapterObj chapterObj = new ChapterObj(1, null, 1, null, true, true);
        assertEquals(chapterObj.id(), 1);
        assertNull(chapterObj.name());
        assertEquals(chapterObj.courseId(), 1);
        assertNull(chapterObj.description());
        assertTrue(chapterObj.isUnlocked());
        assertTrue(chapterObj.isCompleted());
    }
}
