package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.IChapterData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.application.Services;
import java.util.List;


public class ChapterHandler implements IChapterHandler {

    private IChapterData chapterData;

    public ChapterHandler(){
        this.chapterData = Services.getChapterData();
    }

    @Override
    public List<ChapterObj> getChapters() {
        return chapterData.getChapters();
    }

    @Override
    public List<ChapterObj> getChaptersByCourse(CourseObj course) {
        return chapterData.getChaptersByCourse(course);
    }

    @Override
    public List<ChapterObj> getChaptersByCourseId(int id) {
        return chapterData.getChaptersByCourseId(id);
    }

    @Override
    public ChapterObj getChapterById(int courseId, int chapterId) {
        return chapterData.getChapterById(courseId, chapterId);
    }
}
