package CodeLinguists.codelingo.persistence.stubs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
        ChapterObj chapter = chapterList.get(chapterId - 1);
        if (chapter != null) {
            chapter.setCompleted(true);
        } else {
            System.out.println("Chapter not found for ID: " + chapterId);
        }
    }

    @Override
    public boolean isChapterComplete(int accountId, int chapterId) {
        return chapterList.get(chapterId - 1).isCompleted();
    }

    @Override
    public void setChapterUnlockedById(int accountId, int chapterId, boolean unlocked) {
        ChapterObj chapter = chapterList.get(chapterId - 1);
        if (chapter != null) {
            chapter.setUnlocked(unlocked);
        } else {
            System.out.println("Chapter not found for ID: " + chapterId);
        }
    }

    @Override
    public boolean isChapterUnlocked(int accountId, int chapterId) {
        return chapterList.get(chapterId - 1).isUnlocked();
    }

    @Override
    public boolean hasNextChapter(int courseId, int chapterId) {
        boolean result = false;
        for (int i = 0; i < chapterList.size(); i++) {
            if (courseId == chapterList.get(i).getCourseId() && chapterId < chapterList.get(i).getId()) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public List<ChapterObj> getFirstChaptersForAllCourse() {
        Map<Integer, List<ChapterObj>> chaptersByCourse = chapterList.stream()
                .collect(Collectors.groupingBy(ChapterObj::getCourseId));

        List<ChapterObj> firstChapters = new ArrayList<>();

        chaptersByCourse.forEach((courseId, chapters) -> {
            ChapterObj firstChapter = chapters.stream()
                    .sorted(Comparator.comparingInt(ChapterObj::getId))
                    .findFirst()
                    .orElse(null);

            if (firstChapter != null) {
                firstChapters.add(firstChapter);
            }
        });

        return firstChapters;
    }

}
