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
    public List<ChapterObj> getChapterByCourseId(int courseId) {
        return chapterList.stream()
                .filter(chapterObj -> chapterObj.courseId()==courseId)
                .collect(Collectors.toList());
    }

    @Override
    public ChapterObj getChapterById(int chapterId, int courseId) {
        return chapterList.stream()
                .filter(chapterObj -> chapterObj.courseId()==courseId && chapterObj.id()==chapterId)
                .findFirst()
                .orElse(null);
    }
}
