package CodeLinguists.codelingo.persistence;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.QuizObj;

public interface IChapterData {
    List<ChapterObj> getChapterByCourseId(int courseId, int accountId);

    ChapterObj getChapterById(int chapterId, int courseId, int accountId);
}
