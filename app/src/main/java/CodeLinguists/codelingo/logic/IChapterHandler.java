package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;

public interface IChapterHandler {
    public List<ChapterObj> getChaptersByCourse(CourseObj course);
    public List<ChapterObj> getChaptersByCourseId(int courseId);
    public ChapterObj getChapterById(int chapterId);
}
