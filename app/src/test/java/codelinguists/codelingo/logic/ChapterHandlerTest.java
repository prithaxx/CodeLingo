package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.logic.ChapterHandler;
import CodeLinguists.codelingo.persistence.IChapterData;

public class ChapterHandlerTest {
    private ChapterHandler chapterHandler;

    @Before
    public void setUp() {
        Services.resetObjects();
        IChapterData chapterData = Services.getChapterData();

        this.chapterHandler = new ChapterHandler(chapterData);
    }

    @Test
    public void getChapters() {
        List<ChapterObj> chapters = chapterHandler.getChapters();
        assertNotNull(chapters);
        assertEquals(chapters.size() , 0);
    }

    @Test
    public void getChaptersByCourse() {
        CourseObj newCourse = new CourseObj(1, "Test Course", "Description", true, true);
        List<ChapterObj> chapters = chapterHandler.getChaptersByCourse(newCourse);
        assertNotNull(chapters);
        assertFalse(chapters.isEmpty());
    }

    @Test
    public void getChaptersByCourseId() {
        int courseId = 1; 
        CourseObj newCourse = new CourseObj(courseId, "Test Course", "Description", true, true);
        List<ChapterObj> chapters = chapterHandler.getChaptersByCourseId(courseId);
        assertNotNull(chapters);
        assertFalse(chapters.isEmpty());
    }

    @Test
    public void testGetChapterById() {
        CourseObj newCourse = new CourseObj(1, "Test Course", "Description", true, true);
        int chapterId = 1; 
        ChapterObj newChapter = new ChapterObj(chapterId, "test", newCourse,"test", true, true);
        ChapterObj chapter = chapterHandler.getChapterById(chapterId);
        assertNotNull(chapter);
        assertEquals(chapterId, chapter.getId());
    }
}
