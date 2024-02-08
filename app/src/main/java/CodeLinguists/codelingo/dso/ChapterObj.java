package CodeLinguists.codelingo.dso;

public class ChapterObj {

    private final int id;
    private final String name;
    private final int courseId;
    private final String description;
    private final boolean isUnlocked;
    private final boolean isCompleted;

    public ChapterObj(int id, String name, int courseId, String description, boolean isUnlocked, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.description = description;
        this.isUnlocked = isUnlocked;
        this.isCompleted = isCompleted;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public int getCourseId() {
        return courseId;
    }

    public String getDescription() {
        return description;
    }
}
