package CodeLinguists.codelingo.dso;

public class ChapterObj {
    private final int id;
    private final String name;
    private final int courseId;
    private final String description;
    private boolean isUnlocked;
    private boolean isCompleted;

    // Constructor
    public ChapterObj(int id, String name, int courseId, String description, boolean isUnlocked, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.description = description;
        this.isUnlocked = isUnlocked;
        this.isCompleted = isCompleted;
    }

    // Getter methods
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

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Setter method for isCompleted
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public void setUnlocked(boolean isunlocked) {
        this.isUnlocked = isunlocked;
    }
}

