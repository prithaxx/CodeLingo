package CodeLinguists.codelingo.persistence;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.QuizObj;

public interface IChapterData {
    List<ChapterObj> getChapterByCourseId(int id);

    ChapterObj getChapterById(int chapterId, int courseId);
}
