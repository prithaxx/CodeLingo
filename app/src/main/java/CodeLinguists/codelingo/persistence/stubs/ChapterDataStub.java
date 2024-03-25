package CodeLinguists.codelingo.persistence.stubs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.persistence.IChapterData;

public class ChapterDataStub implements IChapterData {

    private final List<ChapterObj> chapterList;

    public ChapterDataStub() {
        chapterList = new ArrayList<>();
        chapterList.add(new ChapterObj(1, "Introduction to Java", 1, null, true, true));
        chapterList.add(new ChapterObj(3, "Data Structures", 1, null, false, false));
        chapterList.add(new ChapterObj(4, "Advanced Java Features", 2, null, true, false));
        chapterList.add(new ChapterObj(5, "Concurrency in Java", 2, null, false, false));
        chapterList.add(new ChapterObj(6, "Java Networking", 3, null, true, false));
    }
    @Override
    public List<ChapterObj> getChapterByCourseId(int courseId, int accountId) {
        return chapterList.stream()
                .filter(chapterObj -> chapterObj.getCourseId()==courseId)
                .collect(Collectors.toList());
    }

    @Override
    public void setChapterCompletionById(int accountId, int chapterId) {
        ChapterObj chapter = chapterList.get(chapterId);
        if (chapter != null) {
            chapter.setCompleted(true);
        } else {
            System.out.println("Chapter not found for ID: " + chapterId);
        }
    }

    @Override
    public void setChapterUnlockedById(int accountId, int chapterId, boolean unlocked) {
        ChapterObj chapter = chapterList.get(chapterId);
        if (chapter != null) {
            chapter.setUnlocked(unlocked);
        } else {
            System.out.println("Chapter not found for ID: " + chapterId);
        }
    }
}
