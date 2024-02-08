package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;

public interface IChapterHandler {
    List<ChapterObj> getChapters();
    List<ChapterObj> getChaptersByCourse(CourseObj course);
    List<ChapterObj> getChaptersByCourseId(int courseId);
    ChapterObj getChapterById(int courseId, int chapterId);

}
