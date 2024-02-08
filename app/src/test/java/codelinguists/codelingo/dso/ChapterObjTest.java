package codelinguists.codelingo.dso;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CodeLinguists.codelingo.dso.ChapterObj;

public class ChapterObjTest {
    @Test
    public void allValues() {
        ChapterObj chapterObj = new ChapterObj(1, "test", 1, "descript", true, true);
        assertEquals(chapterObj.getId(), 1);
        assertEquals(chapterObj.getName(), "test");
        assertEquals(chapterObj.getCourseId(), 1);
        assertEquals(chapterObj.getDescription(), "descript");
        assertTrue(chapterObj.isUnlocked());
        assertTrue(chapterObj.isCompleted());
    }

    @Test
    public void nullValues() {
        ChapterObj chapterObj = new ChapterObj(1, null, 1, null, true, true);
        assertEquals(chapterObj.getId(), 1);
        assertNull(chapterObj.getName());
        assertEquals(chapterObj.getCourseId(), 1);
        assertNull(chapterObj.getDescription());
        assertTrue(chapterObj.isUnlocked());
        assertTrue(chapterObj.isCompleted());
    }
}
