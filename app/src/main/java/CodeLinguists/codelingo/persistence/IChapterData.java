package CodeLinguists.codelingo.persistence;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.persistence.persistence_exceptions.CourseNotFoundException;

public interface IChapterData {
    List<ChapterObj> getChapterByCourseId(int courseId, int accountId);

    void setChapterCompletionById(int accountId, int chapterId);

    boolean isChapterComplete(int accountId, int chapterId);

    void setChapterUnlockedById(int accountId, int chapterId, boolean unlocked);

    boolean isChapterUnlocked(int accountId, int chapterId) throws CourseNotFoundException;

    boolean hasNextChapter(int courseId, int chapterId);

    List<ChapterObj> getFirstChaptersForAllCourse();
}
