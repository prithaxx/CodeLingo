package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.ChapterNotFoundException;

public class ChapterHandlerTest {
    private ChapterHandler chapterHandler;

    @Before
    public void setUp(){
        Services.resetObjects();
        chapterHandler = new ChapterHandler(new ChapterDataStub());
    }

    @Test
    public void getChapters() {
        List<ChapterObj> chapters = chapterHandler.getChapters();
        assertNotNull(chapters);
        assertTrue(chapters.size() > 0);
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
        List<ChapterObj> chapters = chapterHandler.getChaptersByCourseId(courseId);
        assertNotNull(chapters);
        assertFalse(chapters.isEmpty());
        chapters.forEach(chapter -> assertEquals(courseId, chapter.getCourseId()));
    }

    @Test
    public void getChaptersByCourseIdFail() {
        int courseId = 999;
        List<ChapterObj> chapters = chapterHandler.getChaptersByCourseId(courseId);
        assertNotNull(chapters);
        assertTrue(chapters.isEmpty());
    }

    @Test
    public void getChapterById() {
        int courseId = 1;
        int chapterId = 1;
        ChapterObj chapter = chapterHandler.getChapterById(courseId, chapterId);
        assertNotNull(chapter);
        assertEquals(chapterId, chapter.getId());
    }

    @Test(expected = ChapterNotFoundException.class)
    public void getChapterByIdFail() {
        int courseId = 1;
        int chapterId = 999;
        ChapterObj chapter = chapterHandler.getChapterById(courseId, chapterId);
    }
}
