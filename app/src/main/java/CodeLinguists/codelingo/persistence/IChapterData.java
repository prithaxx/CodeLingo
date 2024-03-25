package CodeLinguists.codelingo.persistence;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.QuizObj;

public interface IChapterData {
    List<ChapterObj> getChapterByCourseId(int courseId, int accountId);

    void setChapterCompletionById(int accountId, int chapterId);

    void setChapterUnlockedById(int accountId, int chapterId, boolean unlocked);

    boolean isRemainChaptersInCourse(int courseId, int chapterId);

    List<ChapterObj> getFirstChaptersForAllCourse();
}
