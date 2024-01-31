package CodeLinguists.codelingo.persistence.stubs;

import java.util.ArrayList;
import java.util.List;

import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.IChapterData;

public class ChapterDataStub implements IChapterData {
    List<ChapterObj> placeholders;

    public ChapterDataStub() {
        placeholders = new ArrayList<ChapterObj>() {{
            new ChapterObj(1,"ex1", 1, "This is a placeholder 1", false, false);
            new ChapterObj(2,"ex2", 1, "This is a placeholder 2", false, false);
            new ChapterObj(3,"ex3", 1, "This is a placeholder 3", false, false);
            new ChapterObj(4,"ex4", 1, "This is a placeholder 4", false, false);
            new ChapterObj(5,"ex5", 1, "This is a placeholder 5", false, false);
            new ChapterObj(6,"ex6", 1, "This is a placeholder 6", false, false);
            new ChapterObj(1,"ex1", 2, "This is a placeholder 1", false, false);
            new ChapterObj(2,"ex2", 2, "This is a placeholder 2", false, false);
            new ChapterObj(3,"ex3", 2, "This is a placeholder 3", false, false);
            new ChapterObj(4,"ex4", 2, "This is a placeholder 4", false, false);
            new ChapterObj(5,"ex5", 2, "This is a placeholder 5", false, false);
            new ChapterObj(6,"ex6", 2, "This is a placeholder 6", false, false);
        }};
    }

    public ChapterDataStub(List<ChapterObj> placeholders) {
        this.placeholders = placeholders;
    }

    @Override
    public List<ChapterObj> getChaptersByCourse(CourseObj course) {
        return placeholders;
    }
}
