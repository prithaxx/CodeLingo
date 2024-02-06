package CodeLinguists.codelingo.persistence;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;

public interface IChapterData {
    List<ChapterObj> getChaptersByCourse(CourseObj course);
    List<ChapterObj> getChaptersByCourseId(int id);
    List<ChapterObj> getChapters();
    ChapterObj getChapterById(int courseId, int chapterId);
}
