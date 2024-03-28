package CodeLinguists.codelingo.persistence;

import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;

public interface IChapterData {
    List<ChapterObj> getChapterByCourseId(int courseId, int accountId);

    void setChapterCompletionById(int accountId, int chapterId);


    void setChapterUnlockedById(int accountId, int chapterId, boolean unlocked);


    boolean hasNextChapter(int courseId, int chapterId);

    List<ChapterObj> getFirstChaptersForAllCourse();
}
