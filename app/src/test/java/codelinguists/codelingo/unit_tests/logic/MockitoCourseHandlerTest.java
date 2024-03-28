package codelinguists.codelingo.unit_tests.logic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.logic.CourseHandler;
import CodeLinguists.codelingo.persistence.IChapterData;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;


public class MockitoCourseHandlerTest {

    private ICourseData courseData;
    private  IChapterData chapterData;
    private CourseHandler courseHandler;
    private final AccountObj defaultAcc = new AccountObj(1,"name", true, 1, "username", "");
    private final CourseObj defaultCourse = new CourseObj(1,"name","description",false,false);
    private final ChapterObj defaultChapter = new ChapterObj(1,"name", 1, "desc", true, false);
    @Before
    public void setup() {
        courseData = mock(ICourseData.class);
        chapterData = mock(IChapterData.class);
        courseHandler = new CourseHandler(courseData, chapterData);
    }

    @Test
    public void testSetChapterCompleteNoNext() throws CourseNotFoundException {
        when(chapterData.hasNextChapter(1,1)).thenReturn(false);
        when(courseData.getCourseById(1, 1)).thenReturn(defaultCourse);
        courseHandler.setChapterComplete(1, defaultAcc);
        verify(chapterData, times(0)).setChapterUnlockedById(1,2, true);
    }

    @Test
    public void testSetChapterCompleteWithNext() throws CourseNotFoundException {
        when(chapterData.hasNextChapter(1,1)).thenReturn(true);
        when(courseData.getCourseById(1, 1)).thenReturn(defaultCourse);
        courseHandler.setChapterComplete(1, defaultAcc);
        verify(chapterData, times(1)).setChapterUnlockedById(1,2, true);
    }

    @Test
    public void testUnlockDefaultChaptersNoChapters() throws CourseNotFoundException {
        ArrayList<ChapterObj> chapters = new ArrayList<>();
        when(chapterData.getFirstChaptersForAllCourse()).thenReturn(chapters);
        when(courseData.getCourseById(1, 1)).thenReturn(defaultCourse);
        courseHandler.setChapterComplete(1, defaultAcc);
        verify(chapterData, times(0)).setChapterUnlockedById(1,1, true);
    }

    @Test
    public void testUnlockDefaultChaptersOneChapter() throws CourseNotFoundException {
        ArrayList<ChapterObj> chapters = new ArrayList<>();
        chapters.add(defaultChapter);
        when(chapterData.getFirstChaptersForAllCourse()).thenReturn(chapters);
        when(courseData.getCourseById(1, 1)).thenReturn(defaultCourse);
        courseHandler.unlockDefaultChapters(defaultAcc);
        verify(chapterData, times(1)).setChapterUnlockedById(1,1, true);
    }

    @Test
    public void testUnlockDefaultChaptersManyChapters() throws CourseNotFoundException {
        ArrayList<ChapterObj> chapters = new ArrayList<>();
        chapters.add(defaultChapter);
        chapters.add(defaultChapter);
        chapters.add(defaultChapter);
        when(chapterData.getFirstChaptersForAllCourse()).thenReturn(chapters);
        when(courseData.getCourseById(1, 1)).thenReturn(defaultCourse);
        courseHandler.unlockDefaultChapters(defaultAcc);
        verify(chapterData, times(3)).setChapterUnlockedById(1,1, true);
    }
}
