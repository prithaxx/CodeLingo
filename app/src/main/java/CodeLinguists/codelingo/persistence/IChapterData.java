package CodeLinguists.codelingo.persistence;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;

public interface IChapterData {
    public List<ChapterObj> getChaptersByCourse(CourseObj course);

}
