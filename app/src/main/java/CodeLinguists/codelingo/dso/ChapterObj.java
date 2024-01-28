package CodeLinguists.codelingo.dso;

public class ChapterObj {

    private final int id;
    private final String name;
    private final CourseObj course;
    private final String description;
    private final boolean isUnlocked;
    private final boolean isCompleted;

    public ChapterObj(int id, String name, CourseObj course, String description, boolean isUnlocked, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.course = course;
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
    public CourseObj getCourse() {
        return course;
    }

    public String getDescription() {
        return description;
    }
}
